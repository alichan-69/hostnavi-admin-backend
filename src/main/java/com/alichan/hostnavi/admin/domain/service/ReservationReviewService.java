package com.alichan.hostnavi.admin.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.ReservationReservationLogic;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationReviewRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReviewResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.ReservationReviewCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationReviewMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationReviewCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReview;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class ReservationReviewService {
  @Autowired
  private ReservationReviewMapper reservationReviewMapper;
  @Autowired
  private ReservationReservationLogic reservationReservationLogic;
  @Autowired
  private ReservationReviewCustomMapper reservationReviewCustomMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();


  public List<ReservationReviewResponseData> getAllReview(Long ownerId, Long reviewerId) {
    List<ReservationReviewCustom> gottenReviews = reservationReviewCustomMapper.selectAll();

    List<ReservationReviewResponseData> reviewResponseDatas =
        convertListCustomModelToListResponseData(gottenReviews);

    reviewResponseDatas = filterReview(ownerId, reviewerId, reviewResponseDatas);

    return reviewResponseDatas;
  }

  public List<ReservationReviewResponseData> getReview(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return getAllReview(null, null);
  }

  public ReservationReviewResponseData createReview(
      ReservationReviewRequestParam reservationReviewRequestParam) {

    ReservationReview insertedReview = convertRequestParamToInsertedModel(
        reservationReviewRequestParam, new Date(), new Date(), false);

    reservationReviewMapper.insertSelective(insertedReview);

    ReservationReviewResponseData reviewResponseData = getReview(insertedReview.getId()).get();

    return reviewResponseData;
  }

  public ReservationReviewResponseData updateReview(long id,
      ReservationReviewRequestParam reservationReviewRequestParam) {
    Optional<ReservationReviewResponseData> reviewOptional = getReview(id);

    reviewOptional.ifPresentOrElse(review -> {
      ReservationReview updatedReview =
          convertRequestParamToUpdatedModel(reservationReviewRequestParam, id, new Date());
      reservationReviewMapper.updateByPrimaryKeySelective(updatedReview);
    }, () -> {
      Assert.failedValidation("レビューのidが存在しません。");
    });

    return getReview(id).get();
  }

  public ReservationReviewResponseData deleteReview(long id) {
    Optional<ReservationReviewResponseData> reviewOptional = getReview(id);
    reviewOptional.ifPresentOrElse(review -> {
      ReservationReview deletedReview = createDeletedModel(id);
      reservationReviewMapper.updateByPrimaryKeySelective(deletedReview);
    }, () -> {
      Assert.failedValidation("レビューのidが存在しませんでした。");
    });

    return reviewOptional.get();
  }

  public List<ReservationReviewResponseData> deleteReviews(List<Long> ids) {
    return ids.stream().map(id -> deleteReview(id)).collect(Collectors.toList());
  }

  public Optional<ReservationReviewResponseData> getReview(long id) {
    ReservationReview review = reservationReviewMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(review == null ? null : convertModelToResponseData(review));
  }

  private ReservationReview convertRequestParamToInsertedModel(
      ReservationReviewRequestParam reservationReviewRequestParam, Date createTime, Date updateTime,
      Boolean deleteFlag) {
    ReservationReview reservationReview =
        modelMapper.map(reservationReviewRequestParam, ReservationReview.class);

    reservationReview.setCreateTime(createTime);
    reservationReview.setUpdateTime(updateTime);
    reservationReview.setDeleteFlag(deleteFlag);

    return reservationReview;
  }

  private ReservationReview convertRequestParamToUpdatedModel(
      ReservationReviewRequestParam reservationReviewRequestParam, long id, Date updateTime) {
    ReservationReview reservationReview =
        modelMapper.map(reservationReviewRequestParam, ReservationReview.class);

    reservationReview.setId(id);
    reservationReview.setUpdateTime(updateTime);

    return reservationReview;
  }

  public ReservationReview createDeletedModel(long id) {
    ReservationReview reservationReview = new ReservationReview();

    reservationReview.setId(id);
    reservationReview.setDeleteFlag(true);

    return reservationReview;
  }

  public ReservationReviewResponseData convertModelToResponseData(
      ReservationReview reservationReview) {
    ReservationReviewResponseData reservationReviewResponseData =
        modelMapper.map(reservationReview, ReservationReviewResponseData.class);

    reservationReviewResponseData.setReservation(
        reservationReservationLogic.getReservation(reservationReview.getReservationId()).get());

    return reservationReviewResponseData;
  }

  public List<ReservationReviewResponseData> convertListModelToListResponseData(
      List<ReservationReview> reservationReviews) {
    List<ReservationReviewResponseData> reservationReviewResponseDatas = reservationReviews.stream()
        .map(review -> convertModelToResponseData(review)).collect(Collectors.toList());
    return reservationReviewResponseDatas;
  }

  public ReservationReviewResponseData convertCustomModelToResponseData(
      ReservationReviewCustom reservationReview) {
    ReservationReviewResponseData reservationReviewResponseData =
        modelMapper.map(reservationReview, ReservationReviewResponseData.class);

    return reservationReviewResponseData;
  }

  public List<ReservationReviewResponseData> convertListCustomModelToListResponseData(
      List<ReservationReviewCustom> gottenReviews) {
    List<ReservationReviewResponseData> reservationReviewResponseDatas = gottenReviews.stream()
        .map(review -> convertCustomModelToResponseData(review)).collect(Collectors.toList());
    return reservationReviewResponseDatas;
  }

  private List<ReservationReviewResponseData> filterReview(Long ownerId, Long reviewerId,
      List<ReservationReviewResponseData> reviewResponseDatas) {
    // ownerIdがnullでない時レビューされた宿泊施設の所有者が指定idのレビューのみ取得する処理
    if (ownerId != null) {
      reviewResponseDatas = reviewResponseDatas.stream().filter(review -> {
        return review.getReservation().getInn().getUser().getId() == ownerId;
      }).collect(Collectors.toList());
    }

    // reservationIdがnullでない時reservationIdが指定idのレビューのみ取得する処理
    if (reviewerId != null) {
      reviewResponseDatas = reviewResponseDatas.stream().filter(review -> {
        return review.getReservation().getReserver().getId() == reviewerId;
      }).collect(Collectors.toList());
    }
    return reviewResponseDatas;
  }
}

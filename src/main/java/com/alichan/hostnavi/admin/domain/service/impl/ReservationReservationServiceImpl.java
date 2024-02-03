package com.alichan.hostnavi.admin.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.ReservationReservationLogic;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationReservationRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReservationResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.ReservationReservationCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationReservationMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationReservationCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReservation;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class ReservationReservationService {
  @Autowired
  private ReservationReservationMapper reservationReservationMapper;
  @Autowired
  private ReservationReservationCustomMapper reservationReservationCustomMapper;
  @Autowired
  private ReservationReservationLogic reservationReservationLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<ReservationReservationResponseData> getAllReservation(Long ownerId,
      Date checkInTimeAfter, Date checkInTimeBefore, List<Integer> list) {
    List<ReservationReservationCustom> gottenReservations =
        reservationReservationCustomMapper.selectAll();

    List<ReservationReservationResponseData> reservationResponseDatas =
        reservationReservationLogic.convertListCustomModelToListResponseData(gottenReservations);

    reservationResponseDatas = filterReservation(ownerId, checkInTimeAfter, checkInTimeBefore, list,
        reservationResponseDatas);

    return reservationResponseDatas;
  }

  public ReservationReservationResponseData createReservation(
      ReservationReservationRequestParam reservationReservationRequestParam) {

    ReservationReservation insertedReservation = convertRequestParamToInsertedModel(
        reservationReservationRequestParam, new Date(), new Date(), false);

    reservationReservationMapper.insertSelective(insertedReservation);

    ReservationReservationResponseData reservationResponseData =
        getReservation(insertedReservation.getId()).get();

    return reservationResponseData;
  }

  public ReservationReservationResponseData updateReservation(long id,
      ReservationReservationRequestParam reservationReservationRequestParam) {
    Optional<ReservationReservationResponseData> reservationOptional = getReservation(id);

    reservationOptional.ifPresentOrElse(reservation -> {
      ReservationReservation updatedReservation =
          convertRequestParamToUpdatedModel(reservationReservationRequestParam, id, new Date());
      reservationReservationMapper.updateByPrimaryKeySelective(updatedReservation);
    }, () -> {
      Assert.failedValidation("予約のidが存在しません。");
    });

    return getReservation(id).get();
  }

  public ReservationReservationResponseData deleteReservation(long id) {
    return reservationReservationLogic.deleteReservation(id);
  }

  public List<ReservationReservationResponseData> getReservation(int pageNumber, int pageSize,
      Date checkInTimeAfter, Date checkInTimeBefore, List<Integer> statusIds) {
    List<ReservationReservationResponseData> reservationResponseDatas =
        getAllReservation(null, checkInTimeAfter, checkInTimeBefore, statusIds);

    reservationResponseDatas = filterReservation(null, checkInTimeAfter, checkInTimeBefore,
        statusIds, reservationResponseDatas);

    PageHelper.startPage(pageNumber, pageSize);
    return reservationResponseDatas;
  }

  public Optional<ReservationReservationResponseData> getReservation(long id) {
    return reservationReservationLogic.getReservation(id);
  }

  private ReservationReservation convertRequestParamToInsertedModel(
      ReservationReservationRequestParam reservationReservationRequestParam, Date createTime,
      Date updateTime, Boolean deleteFlag) {
    ReservationReservation reservationReservation =
        modelMapper.map(reservationReservationRequestParam, ReservationReservation.class);

    reservationReservation.setCreateTime(createTime);
    reservationReservation.setUpdateTime(updateTime);
    reservationReservation.setDeleteFlag(deleteFlag);

    return reservationReservation;
  }

  private ReservationReservation convertRequestParamToUpdatedModel(
      ReservationReservationRequestParam reservationReservationRequestParam, long id,
      Date updateTime) {
    ReservationReservation reservationReservation =
        modelMapper.map(reservationReservationRequestParam, ReservationReservation.class);

    reservationReservation.setId(id);
    reservationReservation.setUpdateTime(updateTime);

    return reservationReservation;
  }

  private List<ReservationReservationResponseData> filterReservation(Long ownerId,
      Date checkInTimeAfter, Date checkInTimeBefore, List<Integer> statusIds,
      List<ReservationReservationResponseData> reservationResponseDatas) {
    // ownerIdがnullでない時予約された宿泊施設の所有者のidが指定されたownerIdの予約のみ取得する処理
    if (ownerId != null) {
      reservationResponseDatas = reservationResponseDatas.stream().filter(reservation -> {
        Long id = reservation.getInn().getUser().getId();
        return id == ownerId;
      }).collect(Collectors.toList());
    }

    // checkInTimeAfterがnullでない時チェックイン時間が指定時間以降の予約のみ取得する処理
    if (checkInTimeAfter != null) {
      reservationResponseDatas = reservationResponseDatas.stream().filter(reservation -> {
        Date checkInTime = reservation.getCheckInTime();
        return checkInTime.after(checkInTimeAfter) || checkInTime.equals(checkInTimeAfter);
      }).collect(Collectors.toList());
    }

    // checkInTimeBeforeがnullでない時チェックイン時間が指定時間以前の予約のみ取得する処理
    if (checkInTimeBefore != null) {
      reservationResponseDatas = reservationResponseDatas.stream().filter(reservation -> {
        Date checkInTime = reservation.getCheckInTime();
        return checkInTime.before(checkInTimeBefore) || checkInTime.equals(checkInTimeBefore);
      }).collect(Collectors.toList());
    }

    // statusIdsがnullでない時statusIdが指定のidの予約のみ取得する処理
    if (statusIds != null) {
      reservationResponseDatas = reservationResponseDatas.stream().filter(reservation -> {
        Integer statusId = reservation.getStatus().getId();
        return statusIds.stream().anyMatch(el -> el.equals(statusId));
      }).collect(Collectors.toList());
    }

    return reservationResponseDatas;
  }
}

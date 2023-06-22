package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReservationResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.InnInnCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationReservationMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnInnCustom;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationReservationCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReservation;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReservationExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class ReservationReservationLogic {
  @Autowired
  ReservationReservationMapper reservationReservationMapper;
  @Autowired
  InnInnCustomMapper innInnCustomMapper;
  @Autowired
  private UserUserLogic userUserLogic;
  @Autowired
  private MultipleTablesLogic multipleTablesLogic;
  @Autowired
  private InnImageLogic innImageLogic;
  @Autowired
  private ReservationStatusLogic reservationStatusLogic;
  @Autowired
  private ReservationMessageLogic reservationMessageLogic;
  @Autowired
  private ReservationReviewLogic reservationReviewLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public Optional<ReservationReservationResponseData> getReservation(long id) {
    ReservationReservationExample reservationReservationExample =
        new ReservationReservationExample();
    reservationReservationExample.createCriteria().andIdEqualTo(id).andDeleteFlagEqualTo(false);
    List<ReservationReservation> reservations =
        reservationReservationMapper.selectByExample(reservationReservationExample);

    return Optional.ofNullable(
        reservations.isEmpty() ? null : convertModelToResponseData(reservations.get(0)));
  }

  public List<ReservationReservation> getReservationByInnId(long innId) {
    ReservationReservationExample reservationReservationExample =
        new ReservationReservationExample();
    reservationReservationExample.createCriteria().andInnIdEqualTo(innId)
        .andDeleteFlagEqualTo(false);
    return reservationReservationMapper.selectByExample(reservationReservationExample);
  }

  public List<ReservationReservation> getReservationByReserverId(long reseverId) {
    ReservationReservationExample reservationReservationExample =
        new ReservationReservationExample();
    reservationReservationExample.createCriteria().andReserverIdEqualTo(reseverId)
        .andDeleteFlagEqualTo(false);
    return reservationReservationMapper.selectByExample(reservationReservationExample);
  }

  public ReservationReservationResponseData deleteReservation(long id) {
    Optional<ReservationReservationResponseData> reservationOptional = getReservation(id);
    reservationOptional.ifPresentOrElse(reservation -> {
      ReservationReservation deletedReservation = createDeletedModel(id);
      reservationReservationMapper.updateByPrimaryKeySelective(deletedReservation);

      // 予約のメッセージを削除
      reservationMessageLogic.deleteMessagesByReservationId(deletedReservation.getId());

      // 予約のレビューを削除
      reservationReviewLogic.deleteReviewsByReservationId(deletedReservation.getId());
    }, () -> {
      Assert.failedValidation("予約のidが存在しませんでした。");
    });

    return reservationOptional.get();
  }

  public void deleteReservationWithoutResponseData(long id) {
    ReservationReservation deletedReservation = createDeletedModel(id);
    reservationReservationMapper.updateByPrimaryKeySelective(deletedReservation);

    // 予約のメッセージを削除
    reservationMessageLogic.deleteMessagesByReservationId(deletedReservation.getId());

    // 予約のレビューを削除
    reservationReviewLogic.deleteReviewsByReservationId(deletedReservation.getId());
  }

  public void deleteReservationsByInnId(long innId) {
    List<ReservationReservation> reservations = getReservationByInnId(innId);

    reservations.forEach(reservation -> {
      deleteReservationWithoutResponseData(reservation.getId());
    });
  }

  public void deleteReservationsByReserverId(long reserverId) {
    List<ReservationReservation> reservations = getReservationByReserverId(reserverId);
    reservations.forEach(reservation -> {
      deleteReservationWithoutResponseData(reservation.getId());
    });
  }

  public ReservationReservationResponseData convertCustomModelToResponseData(
      ReservationReservationCustom reservationReservation) {
    ReservationReservationResponseData reservationReservationResponseData =
        modelMapper.map(reservationReservation, ReservationReservationResponseData.class);

    return reservationReservationResponseData;
  }

  public List<ReservationReservationResponseData> convertListCustomModelToListResponseData(
      List<ReservationReservationCustom> reservationReservationList) {
    List<ReservationReservationResponseData> reservationReservationResponseDataList =
        reservationReservationList.stream()
            .map(reservation -> convertCustomModelToResponseData(reservation))
            .collect(Collectors.toList());
    return reservationReservationResponseDataList;
  }

  public ReservationReservationResponseData convertModelToResponseData(
      ReservationReservation reservationReservation) {
    ReservationReservationResponseData reservationReservationResponseData =
        modelMapper.map(reservationReservation, ReservationReservationResponseData.class);

    reservationReservationResponseData
        .setInn(getInn(reservationReservation.getInnId()).orElse(null));
    reservationReservationResponseData
        .setReserver(userUserLogic.getUser(reservationReservation.getReserverId()).orElse(null));
    reservationReservationResponseData.setStatus(reservationStatusLogic
        .getReservationStatus(reservationReservation.getStatusId()).orElse(null));

    return reservationReservationResponseData;
  }

  public List<ReservationReservationResponseData> convertListModelToListResponseData(
      List<ReservationReservation> reservationReservations) {
    List<ReservationReservationResponseData> reservationReservationResponseDatas =
        reservationReservations.stream().map(reservation -> convertModelToResponseData(reservation))
            .collect(Collectors.toList());
    return reservationReservationResponseDatas;
  }

  public ReservationReservation createDeletedModel(long id) {
    ReservationReservation reservationReservation = new ReservationReservation();

    reservationReservation.setId(id);
    reservationReservation.setDeleteFlag(true);

    return reservationReservation;
  }

  // 循環参照を避けるためInnInnLogicクラスではなく直接ReservationReservationLogicにgetInn、convertModelToResponseDataを定義
  public Optional<InnInnResponseData> getInn(long id) {
    InnInnCustom inn = innInnCustomMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(inn == null ? null : convertModelToResponseData(inn));
  }

  private InnInnResponseData convertModelToResponseData(InnInnCustom innInn) {
    InnInnResponseData innInnResponseData = modelMapper.map(innInn, InnInnResponseData.class);

    innInnResponseData.setAmenityList(multipleTablesLogic.getAmenityByInnId(innInn.getId()));
    innInnResponseData.setFacilityList(multipleTablesLogic.getFacilityByInnId(innInn.getId()));
    innInnResponseData.setUser(userUserLogic.getUser(innInn.getUserId()).orElse(null));

    return innInnResponseData;
  }
}

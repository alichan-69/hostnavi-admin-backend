package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationStatusResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationStatusMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationStatus;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class ReservationStatusLogic {
  @Autowired
  private ReservationStatusMapper reservationStatusMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public Optional<ReservationStatusResponseData> getReservationStatus(int id) {
    ReservationStatus reservationStatus = reservationStatusMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(
        reservationStatus == null ? null : convertModelToResponseData(reservationStatus));
  }

  public ReservationStatusResponseData convertModelToResponseData(ReservationStatus status) {
    ReservationStatusResponseData reservationStatusResponseData =
        modelMapper.map(status, ReservationStatusResponseData.class);

    return reservationStatusResponseData;
  }

  public List<ReservationStatusResponseData> convertListModelToListResponseData(
      List<ReservationStatus> statuses) {
    List<ReservationStatusResponseData> reservationStatusResponseDatas = statuses.stream()
        .map(status -> convertModelToResponseData(status)).collect(Collectors.toList());
    return reservationStatusResponseDatas;
  }

}

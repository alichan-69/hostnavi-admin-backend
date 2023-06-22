package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.ReservationStatusLogic;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationStatusResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationStatusMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationStatus;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationStatusExample;
import com.github.pagehelper.PageHelper;

@Service
public class ReservationStatusService {
  @Autowired
  private ReservationStatusMapper reservationStatusMapper;
  @Autowired
  private ReservationStatusLogic reservationStatusLogic;

  public List<ReservationStatusResponseData> getAllStatus() {
    List<ReservationStatus> gottenStatuses =
        reservationStatusMapper.selectByExample(new ReservationStatusExample());

    List<ReservationStatusResponseData> statusResponseDatas =
        reservationStatusLogic.convertListModelToListResponseData(gottenStatuses);

    return statusResponseDatas;
  }

  public List<ReservationStatusResponseData> getStatus(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return reservationStatusLogic.convertListModelToListResponseData(
        reservationStatusMapper.selectByExample(new ReservationStatusExample()));
  }

  public Optional<ReservationStatusResponseData> getStatus(int id) {
    return reservationStatusLogic.getReservationStatus(id);
  }
}

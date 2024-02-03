package com.alichan.hostnavi.admin.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationReservationRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReservationResponseData;

public interface ReservationReservationService {
  public List<ReservationReservationResponseData> getAllReservation(Long ownerId,
      Date checkInTimeAfter, Date checkInTimeBefore, List<Integer> list);

  public ReservationReservationResponseData createReservation(
      ReservationReservationRequestParam reservationReservationRequestParam);

  public ReservationReservationResponseData updateReservation(long id,
      ReservationReservationRequestParam reservationReservationRequestParam);

  public ReservationReservationResponseData deleteReservation(long id);

  public List<ReservationReservationResponseData> getReservation(int pageNumber, int pageSize,
      Date checkInTimeAfter, Date checkInTimeBefore, List<Integer> statusIds);

  public Optional<ReservationReservationResponseData> getReservation(long id);
}

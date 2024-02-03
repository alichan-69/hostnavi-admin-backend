package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationStatusResponseData;

public interface ReservationStatusService {
  public List<ReservationStatusResponseData> getAllStatus();

  public List<ReservationStatusResponseData> getStatus(int pageNumber, int pageSize);

  public Optional<ReservationStatusResponseData> getStatus(int id);
}

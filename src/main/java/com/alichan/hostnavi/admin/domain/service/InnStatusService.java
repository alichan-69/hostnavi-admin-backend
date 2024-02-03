package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.responsedata.InnStatusResponseData;

public interface InnStatusService {
  public List<InnStatusResponseData> getAllStatus();

  public List<InnStatusResponseData> getStatus(int pageNumber, int pageSize);

  public Optional<InnStatusResponseData> getStatus(int id);
}

package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.responsedata.InnFacilityResponseData;

public interface InnFacilityService {
  public List<InnFacilityResponseData> getAllFacility();

  public List<InnFacilityResponseData> getFacility(int pageNumber, int pageSize);

  public Optional<InnFacilityResponseData> getFacility(int id);
}

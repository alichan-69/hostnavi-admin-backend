package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.responsedata.InnAmenityResponseData;

public interface InnAmenityService {
  public List<InnAmenityResponseData> getAllAmenity();

  public List<InnAmenityResponseData> getAmenity(int pageNumber, int pageSize);

  public Optional<InnAmenityResponseData> getAmenity(int id);
}

package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.responsedata.InnTypeResponseData;

public interface InnTypeService {
  public List<InnTypeResponseData> getAllType();

  public List<InnTypeResponseData> getType(int pageNumber, int pageSize);

  public Optional<InnTypeResponseData> getType(int id);
}

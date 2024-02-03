package com.alichan.hostnavi.admin.domain.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.InnFacilityLogic;
import com.alichan.hostnavi.admin.domain.service.InnFacilityService;
import com.alichan.hostnavi.admin.dto.responsedata.InnFacilityResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnFacilityMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnFacility;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnFacilityExample;
import com.github.pagehelper.PageHelper;

@Service
public class InnFacilityServiceImpl implements InnFacilityService {
  @Autowired
  private InnFacilityMapper innFacilityMapper;
  @Autowired
  private InnFacilityLogic innFacilityLogic;

  public List<InnFacilityResponseData> getAllFacility() {
    return innFacilityLogic.convertListModelToListResponseData(
        innFacilityMapper.selectByExample(new InnFacilityExample()));
  }

  public List<InnFacilityResponseData> getFacility(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return innFacilityLogic.convertListModelToListResponseData(
        innFacilityMapper.selectByExample(new InnFacilityExample()));
  }

  public Optional<InnFacilityResponseData> getFacility(int id) {
    InnFacility facility = innFacilityMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(
        facility == null ? null : innFacilityLogic.convertModelToResponseData(facility));
  }
}

package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.InnAmenityLogic;
import com.alichan.hostnavi.admin.dto.responsedata.InnAmenityResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnAmenityMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnAmenity;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnAmenityExample;
import com.github.pagehelper.PageHelper;

@Service
public class InnAmenityService {
  @Autowired
  private InnAmenityMapper innAmenityMapper;
  @Autowired
  private InnAmenityLogic innAmenityLogic;

  public List<InnAmenityResponseData> getAllAmenity() {
    return innAmenityLogic.convertListModelToListResponseData(
        innAmenityMapper.selectByExample(new InnAmenityExample()));
  }

  public List<InnAmenityResponseData> getAmenity(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return innAmenityLogic.convertListModelToListResponseData(
        innAmenityMapper.selectByExample(new InnAmenityExample()));
  }

  public Optional<InnAmenityResponseData> getAmenity(int id) {
    InnAmenity amenity = innAmenityMapper.selectByPrimaryKey(id);
    return Optional
        .ofNullable(amenity == null ? null : innAmenityLogic.convertModelToResponseData(amenity));
  }
}

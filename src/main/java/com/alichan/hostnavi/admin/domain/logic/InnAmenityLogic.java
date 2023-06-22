package com.alichan.hostnavi.admin.domain.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.InnAmenityResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnAmenityMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnAmenity;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnAmenityExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class InnAmenityLogic {
  @Autowired
  private InnAmenityMapper innAmenityMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnAmenityResponseData> getAmenity(List<Integer> ids) {
    List<InnAmenity> innAmenity = new ArrayList<InnAmenity>();

    if (!ids.isEmpty()) {
      InnAmenityExample innAmenityExample = new InnAmenityExample();
      innAmenityExample.createCriteria().andIdIn(ids);
      innAmenity = innAmenityMapper.selectByExample(innAmenityExample);
    }

    return convertListModelToListResponseData(innAmenity);
  }

  public InnAmenityResponseData convertModelToResponseData(InnAmenity amenity) {
    InnAmenityResponseData innAmenityResponseData =
        modelMapper.map(amenity, InnAmenityResponseData.class);

    return innAmenityResponseData;
  }

  public List<InnAmenityResponseData> convertListModelToListResponseData(
      List<InnAmenity> amenities) {
    List<InnAmenityResponseData> innAmenityResponseDatas = amenities.stream()
        .map(amenity -> convertModelToResponseData(amenity)).collect(Collectors.toList());
    return innAmenityResponseDatas;
  }
}

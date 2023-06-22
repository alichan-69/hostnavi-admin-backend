package com.alichan.hostnavi.admin.domain.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.InnFacilityResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnFacilityMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnFacility;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnFacilityExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class InnFacilityLogic {
  @Autowired
  private InnFacilityMapper innFacilityMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnFacilityResponseData> getFacility(List<Integer> ids) {
    List<InnFacility> innFacility = new ArrayList<InnFacility>();

    if (!ids.isEmpty()) {
      InnFacilityExample innFacilityExample = new InnFacilityExample();
      innFacilityExample.createCriteria().andIdIn(ids);
      innFacility = innFacilityMapper.selectByExample(innFacilityExample);
    }

    return convertListModelToListResponseData(innFacility);
  }

  public InnFacilityResponseData convertModelToResponseData(InnFacility facility) {
    InnFacilityResponseData innFacilityResponseData =
        modelMapper.map(facility, InnFacilityResponseData.class);

    return innFacilityResponseData;
  }

  public List<InnFacilityResponseData> convertListModelToListResponseData(
      List<InnFacility> facilities) {
    List<InnFacilityResponseData> innFacilityResponseDatas = facilities.stream()
        .map(facility -> convertModelToResponseData(facility)).collect(Collectors.toList());
    return innFacilityResponseDatas;
  }
}

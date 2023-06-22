package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.InnAmenityResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnFacilityResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnAmenityRelationResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnFacilityRelationResponseData;

@Component
public class MultipleTablesLogic {
  @Autowired
  private InnAmenityLogic innAmenityLogic;
  @Autowired
  private InnInnAmenityRelationLogic innInnAmenityRelationLogic;
  @Autowired
  InnFacilityLogic innFacilityLogic;
  @Autowired
  InnInnFacilityRelationLogic innFacilityRelationLogic;

  public List<InnAmenityResponseData> getAmenityByInnId(long innId) {
    List<InnInnAmenityRelationResponseData> InnAmenityRelationResponseDatas =
        innInnAmenityRelationLogic.getInnAmenityRelationByInnId(innId);
    List<Integer> amenityIds = InnAmenityRelationResponseDatas.stream()
        .map(innAmenityRelation -> innAmenityRelation.getAmenityId()).collect(Collectors.toList());
    return innAmenityLogic.getAmenity(amenityIds);
  }

  public List<InnFacilityResponseData> getFacilityByInnId(long innId) {
    List<InnInnFacilityRelationResponseData> InnFacilityRelationResponseDatas =
        innFacilityRelationLogic.getInnFacilityRelationByInnId(innId);
    List<Integer> facilityIds = InnFacilityRelationResponseDatas.stream()
        .map(innFacilityRelation -> innFacilityRelation.getFacility().getId())
        .collect(Collectors.toList());
    return innFacilityLogic.getFacility(facilityIds);
  }
}

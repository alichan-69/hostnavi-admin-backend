package com.alichan.hostnavi.admin.dto.requestparam;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnInnFacilityRelationRequestParam {
  private Long innId;
  private List<Integer> facilityIds;
}

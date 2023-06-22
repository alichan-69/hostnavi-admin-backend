package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnFacility;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InnInnFacilityRelationResponseData {
  private Long id;
  private InnInnResponseData inn;
  private InnFacility facility;
  private Date createTime;
  private Date updateTime;
}

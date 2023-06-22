package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InnInnAmenityRelationResponseData {
  private Long id;
  private Integer innId;
  private Integer amenityId;
  private Date createTime;
  private Date updateTime;
}

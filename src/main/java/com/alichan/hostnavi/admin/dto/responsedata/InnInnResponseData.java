package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnInnResponseData {
  private Long id;
  private UserUserResponseData user;
  private String name;
  private String description;
  private Integer fee;
  private InnStatusResponseData status;
  private InnTypeResponseData type;
  private String address;
  private Integer guestNumber;
  private Integer bedroomNumber;
  private Integer bedNumber;
  private Integer bathroomNumber;
  private List<InnAmenityResponseData> amenityList;
  private List<InnFacilityResponseData> facilityList;
  private List<InnImageResponseData> imageList;
  private Date createTime;
  private Date updateTime;
}

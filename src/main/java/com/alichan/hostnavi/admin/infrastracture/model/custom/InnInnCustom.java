package com.alichan.hostnavi.admin.infrastracture.model.custom;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnInnCustom {
  private Long id;
  private Long userId;
  private String userImageUrl;
  private String userName;
  private String userDescription;
  private String userAddress;
  private String userOccupation;
  private String userPhoneNumber;
  private String userMail;
  private Long creditCardId;
  private String creditCardCardNumber;
  private Date creditCardExpirationDate;
  private String creditCardCvv;
  private Date creditCardCreateTime;
  private Date creditCardUpdateTime;
  private String userFacebookUrl;
  private String userInstagramUrl;
  private String userTwitterUrl;
  private Date userCreateTime;
  private Date userUpdateTime;
  private String name;
  private String description;
  private Integer statusId;
  private String statusName;
  private Integer typeId;
  private String typeName;
  private Integer fee;
  private String address;
  private Integer guestNumber;
  private Integer bedroomNumber;
  private Integer bedNumber;
  private Integer bathroomNumber;
  private Date createTime;
  private Date updateTime;
}

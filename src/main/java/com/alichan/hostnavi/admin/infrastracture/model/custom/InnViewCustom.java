package com.alichan.hostnavi.admin.infrastracture.model.custom;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnViewCustom {
  private Long id;
  private Long innId;
  private Long innUserId;
  private String innUserImageUrl;
  private String innUserName;
  private String innUserDescription;
  private String innUserAddress;
  private String innUserOccupation;
  private String innUserPhoneNumber;
  private String innUserMail;
  private Long innUserCreditCardId;
  private String innUserCreditCardCardNumber;
  private Date innUserCreditCardExpirationDate;
  private String innUserCreditCardCvv;
  private Date innUserCreditCardCreateTime;
  private Date innUserCreditCardUpdateTime;
  private String innUserFacebookUrl;
  private String innUserInstagramUrl;
  private String innUserTwitterUrl;
  private Date innUserCreateTime;
  private Date innUserUpdateTime;
  private String innName;
  private String innDescription;
  private Integer innStatusId;
  private String innStatusName;
  private Integer innTypeId;
  private String innTypeName;
  private Integer innFee;
  private String innAddress;
  private Integer innGuestNumber;
  private Integer innBedroomNumber;
  private Integer innBedNumber;
  private Integer innBathroomNumber;
  private Date innCreateTime;
  private Date innUpdateTime;
  private Long viewerId;
  private String viewerImageUrl;
  private String viewerName;
  private String viewerDescription;
  private String viewerAddress;
  private String viewerOccupation;
  private String viewerPhoneNumber;
  private String viewerMail;
  private Long viewerCreditCardId;
  private String viewerCreditCardCardNumber;
  private Date viewerCreditCardExpirationDate;
  private String viewerCreditCardCvv;
  private Date viewerCreditCardCreateTime;
  private Date viewerCreditCardUpdateTime;
  private String viewerFacebookUrl;
  private String viewerInstagramUrl;
  private String viewerTwitterUrl;
  private Date viewerCreateTime;
  private Date viewerUpdateTime;
  private Integer pageId;
  private String pageName;
  private Date createTime;
}

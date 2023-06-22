package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUserResponseData {
  private Long id;
  private String imageUrl;
  private String name;
  private String description;
  private String address;
  private String occupation;
  private String phoneNumber;
  private String mail;
  private String facebookUrl;
  private String instagramUrl;
  private String twitterUrl;
  private UserCreditCardResponseData creditCard;
  private Date createTime;
  private Date updateTime;
}

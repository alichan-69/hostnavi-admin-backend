package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreditCardResponseData {
  private Long id;
  private String cardNumber;
  private Date expirationDate;
  private String cvv;
  private Date createTime;
  private Date updateTime;
}

package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnImageResponseData {
  private Long id;
  private Long innId;
  private String name;
  private String imageUrl;
  private Date createTime;
  private Date updateTime;
}

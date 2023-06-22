package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnViewResponseData {
  private Long id;
  private InnInnResponseData inn;
  private UserUserResponseData viewer;
  private InnPageResponseData page;
  private Date createTime;
}

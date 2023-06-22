package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationReservationResponseData {
  private Long id;
  private InnInnResponseData inn;
  private ReservationStatusResponseData status;
  private UserUserResponseData reserver;
  private Date checkInTime;
  private Date checkOutTime;
  private Integer guestNumber;
  private Integer fee;
  private Date createTime;
  private Date updateTime;
}

package com.alichan.hostnavi.admin.dto.requestparam;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationMessageRequestParam {
  private String event;
  private Long reservationId;
  private Long senderId;
  private Long receiverId;
  private String message;
  private Date sendTime;
}

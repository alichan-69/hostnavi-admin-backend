package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSocketReservationMessageResponseData {
  private String event;
  private Long id;
  private Long reservationId;
  private Long senderId;
  private Long receiverId;
  private String message;
  private Date sendTime;
}

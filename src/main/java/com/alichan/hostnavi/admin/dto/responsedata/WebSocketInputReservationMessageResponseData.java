package com.alichan.hostnavi.admin.dto.responsedata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSocketInputReservationMessageResponseData {
  private String event;
  private Long reservationId;
  private Long senderId;
}

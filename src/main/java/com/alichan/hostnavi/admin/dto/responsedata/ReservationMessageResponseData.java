package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationMessageResponseData {
  private Long id;
  private ReservationReservationResponseData reservation;
  private UserUserResponseData sender;
  private UserUserResponseData receiver;
  private String message;
  private Date sendTime;
}

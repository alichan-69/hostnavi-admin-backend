package com.alichan.hostnavi.admin.dto.responsedata;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationReviewResponseData {
  private Long id;
  private ReservationReservationResponseData reservation;
  private String review;
  private Integer cleanScore;
  private Integer serviceScore;
  private Integer facilityScore;
  private Integer locationScore;
  private Integer feeScore;
  private Date createTime;
  private Date updateTime;
}

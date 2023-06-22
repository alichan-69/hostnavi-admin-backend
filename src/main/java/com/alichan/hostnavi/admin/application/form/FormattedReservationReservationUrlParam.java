package com.alichan.hostnavi.admin.application.form;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormattedReservationReservationUrlParam {
  Date checkInTImeAfterDate;
  Date checkInTimeBeforeDate;
  List<Integer> statusIds;
}

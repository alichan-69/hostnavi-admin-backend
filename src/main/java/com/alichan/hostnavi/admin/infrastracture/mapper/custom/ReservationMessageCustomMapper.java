package com.alichan.hostnavi.admin.infrastracture.mapper.custom;

import java.util.List;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationMessageCustom;

public interface ReservationMessageCustomMapper {
  List<ReservationMessageCustom> selectSameReservationsLastMessages();
}

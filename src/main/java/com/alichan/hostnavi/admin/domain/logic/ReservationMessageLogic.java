package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationMessageMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessage;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessageExample;

@Component
public class ReservationMessageLogic {
  @Autowired
  ReservationMessageMapper reservationMessageMapper;

  public void deleteMessagesByReservationId(long reservationId) {
    List<ReservationMessage> messages = getMessageByReservationId(reservationId);

    messages.forEach(message -> {
      deleteMessageWithoutResponseData(message.getId());
    });
  }

  public List<ReservationMessage> getMessageByReservationId(long reservationId) {
    ReservationMessageExample reservationMessageExample = new ReservationMessageExample();
    reservationMessageExample.createCriteria().andReservationIdEqualTo(reservationId)
        .andDeleteFlagEqualTo(false);
    return reservationMessageMapper.selectByExample(reservationMessageExample);
  }

  public void deleteMessageWithoutResponseData(long id) {
    ReservationMessage deletedMessage = createDeletedModel(id);
    reservationMessageMapper.updateByPrimaryKeySelective(deletedMessage);
  }

  public ReservationMessage createDeletedModel(long id) {
    ReservationMessage reservationMessage = new ReservationMessage();

    reservationMessage.setId(id);
    reservationMessage.setDeleteFlag(true);

    return reservationMessage;
  }
}

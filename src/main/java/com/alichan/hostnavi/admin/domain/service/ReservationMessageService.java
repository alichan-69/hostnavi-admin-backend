package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationMessageRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationMessageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.WebSocketInputReservationMessageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.WebSocketReservationMessageResponseData;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationMessageCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessage;

public interface ReservationMessageService {
  public List<WebSocketReservationMessageResponseData> getAllMessage(Long reservationId);

  public List<ReservationMessageResponseData> getSameReservationsLastMessages(Long ownerId);

  public Optional<WebSocketReservationMessageResponseData> getMessage(long id);

  public WebSocketReservationMessageResponseData createMessage(
      ReservationMessageRequestParam reservationMessageRequestParam);

  public WebSocketInputReservationMessageResponseData inputMessage(
      ReservationMessageRequestParam reservationMessageRequestParam);

  public ReservationMessageResponseData convertCustomModelToResponseData(
      ReservationMessageCustom reservationMessage);

  public WebSocketReservationMessageResponseData convertModelToWebSocketResponseData(
      ReservationMessage reservationMessage);

  public WebSocketInputReservationMessageResponseData convertRequestParamToInputWebSocketResponseData(
      ReservationMessageRequestParam reservationMessage);
}

package com.alichan.hostnavi.admin.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.ReservationReservationLogic;
import com.alichan.hostnavi.admin.domain.logic.UserUserLogic;
import com.alichan.hostnavi.admin.domain.service.ReservationMessageService;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationMessageRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationMessageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.WebSocketInputReservationMessageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.WebSocketReservationMessageResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.ReservationMessageCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationMessageMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationMessageCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessage;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessageExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Service
public class ReservationMessageServiceImpl implements ReservationMessageService {
  @Autowired
  private ReservationMessageMapper reservationMessageMapper;
  @Autowired
  private ReservationMessageCustomMapper reservationMessageCustomMapper;
  @Autowired
  private UserUserLogic userUserLogic;
  @Autowired
  private ReservationReservationLogic reservationReservationLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<WebSocketReservationMessageResponseData> getAllMessage(Long reservationId) {
    ReservationMessageExample reservationMessageExample = new ReservationMessageExample();
    reservationMessageExample.createCriteria().andDeleteFlagEqualTo(false);

    List<ReservationMessage> gottenMessages =
        reservationMessageMapper.selectByExample(reservationMessageExample);

    List<WebSocketReservationMessageResponseData> messageResponseDatas =
        convertListModelToListWebSocketResponseData(gottenMessages);

    return filterMessage(reservationId, messageResponseDatas);
  }

  public List<ReservationMessageResponseData> getSameReservationsLastMessages(Long ownerId) {
    List<ReservationMessageCustom> gottenMessages =
        reservationMessageCustomMapper.selectSameReservationsLastMessages();

    List<ReservationMessageResponseData> messageResponseDatas =
        convertListCustomModelToListResponseData(gottenMessages);

    return filterReservationMessage(ownerId, messageResponseDatas);
  }

  public Optional<WebSocketReservationMessageResponseData> getMessage(long id) {
    ReservationMessageExample reservationMessageExample = new ReservationMessageExample();
    reservationMessageExample.createCriteria().andIdEqualTo(id).andDeleteFlagEqualTo(false);
    List<ReservationMessage> reservations =
        reservationMessageMapper.selectByExample(reservationMessageExample);

    return Optional.ofNullable(
        reservations.isEmpty() ? null : convertModelToWebSocketResponseData(reservations.get(0)));
  }

  public WebSocketReservationMessageResponseData createMessage(
      ReservationMessageRequestParam reservationMessageRequestParam) {
    ReservationMessage insertedMessage =
        convertRequestParamToInsertedModel(reservationMessageRequestParam, new Date(), false);

    reservationMessageMapper.insertSelective(insertedMessage);

    WebSocketReservationMessageResponseData messageWebSocketResponseData =
        getMessage(insertedMessage.getId()).orElse(null);

    messageWebSocketResponseData.setEvent("send");

    return messageWebSocketResponseData;
  }

  public WebSocketInputReservationMessageResponseData inputMessage(
      ReservationMessageRequestParam reservationMessageRequestParam) {
    return convertRequestParamToInputWebSocketResponseData(reservationMessageRequestParam);
  }

  private ReservationMessage convertRequestParamToInsertedModel(
      ReservationMessageRequestParam reservationMessageRequestParam, Date sendTime,
      Boolean deleteFlag) {
    ReservationMessage reservationMessage =
        modelMapper.map(reservationMessageRequestParam, ReservationMessage.class);

    reservationMessage.setSendTime(sendTime);
    reservationMessage.setDeleteFlag(deleteFlag);

    return reservationMessage;
  }

  public ReservationMessageResponseData convertCustomModelToResponseData(
      ReservationMessageCustom reservationMessage) {
    ReservationMessageResponseData reservationMessageResponseData =
        modelMapper.map(reservationMessage, ReservationMessageResponseData.class);
    // reservationMessageResponseData
    // .setSender(userUserLogic.getUser(reservationMessage.getSenderId()).get());
    // reservationMessageResponseData
    // .setReceiver(userUserLogic.getUser(reservationMessage.getReceiverId()).get());
    // reservationMessageResponseData.setReservation(
    // reservationReservationLogic.getReservation(reservationMessage.getReservationId()).get());

    return reservationMessageResponseData;
  }

  private List<ReservationMessageResponseData> convertListCustomModelToListResponseData(
      List<ReservationMessageCustom> messages) {
    List<ReservationMessageResponseData> reservationMessageResponseDatas = messages.stream()
        .map(message -> convertCustomModelToResponseData(message)).collect(Collectors.toList());
    return reservationMessageResponseDatas;
  }

  public WebSocketReservationMessageResponseData convertModelToWebSocketResponseData(
      ReservationMessage reservationMessage) {
    WebSocketReservationMessageResponseData webSocketReservationMessageResponseData =
        modelMapper.map(reservationMessage, WebSocketReservationMessageResponseData.class);

    return webSocketReservationMessageResponseData;
  }

  private List<WebSocketReservationMessageResponseData> convertListModelToListWebSocketResponseData(
      List<ReservationMessage> messages) {
    List<WebSocketReservationMessageResponseData> webSocketReservationMessageResponseDatas =
        messages.stream().map(message -> convertModelToWebSocketResponseData(message))
            .collect(Collectors.toList());
    return webSocketReservationMessageResponseDatas;
  }

  public WebSocketInputReservationMessageResponseData convertRequestParamToInputWebSocketResponseData(
      ReservationMessageRequestParam reservationMessage) {
    WebSocketInputReservationMessageResponseData webSocketInputReservationMessageResponseData =
        modelMapper.map(reservationMessage, WebSocketInputReservationMessageResponseData.class);

    webSocketInputReservationMessageResponseData.setEvent("input");

    return webSocketInputReservationMessageResponseData;
  }

  private List<WebSocketReservationMessageResponseData> filterMessage(Long reservationId,
      List<WebSocketReservationMessageResponseData> webSocketReservationMessageResponseDatas) {
    // reservationIdがnullでない時reservationIdが指定idのメッセージのみ取得する処理
    if (reservationId != null) {
      webSocketReservationMessageResponseDatas =
          webSocketReservationMessageResponseDatas.stream().filter(message -> {
            return message.getReservationId() == reservationId;
          }).collect(Collectors.toList());
    }
    return webSocketReservationMessageResponseDatas;
  }

  private List<ReservationMessageResponseData> filterReservationMessage(Long ownerId,
      List<ReservationMessageResponseData> reservationMessageResponseDatas) {
    // ownerIdがnullでない時メッセージに紐づく予約の宿泊施設の所有者のidがownerIdと一致するメッセージのみ取得する処理
    if (ownerId != null) {
      reservationMessageResponseDatas = reservationMessageResponseDatas.stream().filter(message -> {
        return message.getReservation().getInn().getUser().getId() == ownerId;
      }).collect(Collectors.toList());
    }
    return reservationMessageResponseDatas;
  }
}

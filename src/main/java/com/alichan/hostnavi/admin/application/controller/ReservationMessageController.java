package com.alichan.hostnavi.admin.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.domain.service.ReservationMessageService;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationMessageRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationMessageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.WebSocketReservationMessageResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "予約メッセージAPI", description = "予約メッセージのCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
public class ReservationMessageController {
  @Autowired
  private SimpMessagingTemplate template;
  @Autowired
  private ReservationMessageService reservationMessageService;

  @GetMapping(path = "/messages")
  @ApiOperation(value = "同じ予約idを持つメッセージのリストを取得")
  @ResponseBody
  public Response<List<WebSocketReservationMessageResponseData>> getMessages(
      @ApiParam(value = "予約id", example = "1") @RequestParam(value = "reservation-id",
          required = false) long reservationId) {
    return Response.success(reservationMessageService.getAllMessage(reservationId));
  }

  @GetMapping(path = "/messages/same-reservations-lasts")
  @ApiOperation(value = "同じ予約idを持つメッセージの中で最新のメッセージのリストを取得")
  @ResponseBody
  public Response<List<ReservationMessageResponseData>> getSameReservationsLastMessages(
      @ApiParam(value = "宿泊施設のオーナーid", example = "1") @RequestParam(value = "owner-id",
          required = false) long ownerId) {
    return Response.success(reservationMessageService.getSameReservationsLastMessages(ownerId));
  }

  @MessageMapping("/hostnavi-websocket")
  public void crudMessageWithWebSocket(
      @Validated ReservationMessageRequestParam reservationMessageRequestParam) {
    if (reservationMessageRequestParam.getEvent().equals("send")) {
      template.convertAndSend("/messages/" + reservationMessageRequestParam.getReservationId(),
          reservationMessageService.createMessage(reservationMessageRequestParam));
    } else if (reservationMessageRequestParam.getEvent().equals("input")) {
      template.convertAndSend("/messages/" + reservationMessageRequestParam.getReservationId(),
          reservationMessageService.inputMessage(reservationMessageRequestParam));
    }
  }
}

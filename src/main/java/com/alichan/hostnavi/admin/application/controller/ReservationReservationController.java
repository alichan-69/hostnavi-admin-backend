package com.alichan.hostnavi.admin.application.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.form.FormattedReservationReservationUrlParam;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.application.util.Page;
import com.alichan.hostnavi.admin.domain.service.impl.ReservationReservationService;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationReservationRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReservationResponseData;
import com.alichan.hostnavi.admin.util.FormatDataUitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "予約API", description = "予約のCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/reservations")
public class ReservationReservationController {
  @Autowired
  private ReservationReservationService reservationReservationService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全ての予約のリストを取得")
  @ResponseBody
  public Response<List<ReservationReservationResponseData>> getAllReservation(
      @ApiParam(value = "宿泊施設のオーナーのid", example = "1") @RequestParam(value = "owner-id",
          required = false) Long ownerId,
      @ApiParam(value = "これ以降にチェックインされた予約のみ取得するための指定時間",
          example = "2023-01-01T00:00:00") @RequestParam(value = "check-in-time-after",
              required = false) String checkInTimeAfter,
      @ApiParam(value = "これ以前にチェックインされた予約のみ取得するための指定時間",
          example = "2023-01-01T00:00:00") @RequestParam(value = "check-in-time-before",
              required = false) String checkInTimeBefore,
      @ApiParam(value = "予約のstatusId一覧", example = "1,2") @RequestParam(value = "status-ids",
          required = false) String statusIds) {
    FormattedReservationReservationUrlParam formattedReservationReservationUrlParam =
        convertUrlParamToFormattedParam(checkInTimeAfter, checkInTimeBefore, statusIds);
    return Response.success(reservationReservationService.getAllReservation(ownerId,
        formattedReservationReservationUrlParam.getCheckInTImeAfterDate(),
        formattedReservationReservationUrlParam.getCheckInTimeBeforeDate(),
        formattedReservationReservationUrlParam.getStatusIds()));
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされた予約のリストを取得")
  public Response<Page<ReservationReservationResponseData>> getReservation(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize,
      @ApiParam(value = "これ以降にチェックインされた予約のみ取得するための指定時間",
          example = "2023-01-01T00:00:00") @RequestParam(value = "check-in-time-after",
              required = false) String checkInTimeAfter,
      @ApiParam(value = "これ以前にチェックインされた予約のみ取得するための指定時間",
          example = "2023-01-01T00:00:00") @RequestParam(value = "check-in-time-before",
              required = false) String checkInTimeBefore,
      @ApiParam(value = "予約のstatusId一覧", example = "1,2") @RequestParam(value = "status-ids",
          required = false) String statusIds) {
    FormattedReservationReservationUrlParam formattedReservationReservationUrlParam =
        convertUrlParamToFormattedParam(checkInTimeAfter, checkInTimeBefore, statusIds);

    return Response.success(
        new Page<ReservationReservationResponseData>(reservationReservationService.getReservation(
            pageNumber, pageSize, formattedReservationReservationUrlParam.getCheckInTImeAfterDate(),
            formattedReservationReservationUrlParam.getCheckInTimeBeforeDate(),
            formattedReservationReservationUrlParam.getStatusIds())));
  }

  @PostMapping
  @ResponseBody
  @ApiOperation(value = "予約を作成")
  public Response<ReservationReservationResponseData> createReservation(
      @Validated @RequestBody ReservationReservationRequestParam ReservationReservation) {
    return Response
        .success(reservationReservationService.createReservation(ReservationReservation));
  }

  @PutMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの予約を更新")
  public Response<ReservationReservationResponseData> updateReservation(
      @PathVariable("id") @ApiParam(value = "予約のid", example = "1") long id,
      @Validated @RequestBody ReservationReservationRequestParam reservationReservation) {
    return Response
        .success(reservationReservationService.updateReservation(id, reservationReservation));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの予約を削除")
  public Response<ReservationReservationResponseData> deleteReservation(
      @PathVariable("id") @ApiParam(value = "予約のid", example = "1") long id) {
    return Response.success(reservationReservationService.deleteReservation(id));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの予約を取得")
  public Response<ReservationReservationResponseData> getReservation(
      @PathVariable("id") @ApiParam(value = "予約のid", example = "1") long id) {
    return Response.success(reservationReservationService.getReservation(id).orElse(null));
  }

  private FormattedReservationReservationUrlParam convertUrlParamToFormattedParam(
      String checkInTimeAfter, String checkInTimeBefore, String statusIds) {
    Date formattedCheckInTimeAfter = null;
    Date formattedCheckInTimeBefore = null;
    List<Integer> formattedStatusIds = null;

    if (checkInTimeAfter != null)
      formattedCheckInTimeAfter = FormatDataUitl.convertStringDateToDate(checkInTimeAfter);
    if (checkInTimeBefore != null)
      formattedCheckInTimeBefore = FormatDataUitl.convertStringDateToDate(checkInTimeBefore);
    if (statusIds != null)
      formattedStatusIds = FormatDataUitl.convertStringIdsToIntegerArray(statusIds);

    FormattedReservationReservationUrlParam formattedReservationReservationUrlParam =
        new FormattedReservationReservationUrlParam();

    formattedReservationReservationUrlParam.setCheckInTImeAfterDate(formattedCheckInTimeAfter);
    formattedReservationReservationUrlParam.setCheckInTimeBeforeDate(formattedCheckInTimeBefore);
    formattedReservationReservationUrlParam.setStatusIds(formattedStatusIds);

    return formattedReservationReservationUrlParam;
  }
}

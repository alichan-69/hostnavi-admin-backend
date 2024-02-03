package com.alichan.hostnavi.admin.application.controller;

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
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.application.util.Page;
import com.alichan.hostnavi.admin.domain.service.impl.ReservationReviewService;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationReviewRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReviewResponseData;
import com.alichan.hostnavi.admin.util.FormatDataUitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "予約のレビューのAPI", description = "予約のレビューのCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/reviews")
public class ReservationReviewController {
  @Autowired
  private ReservationReviewService reservationReviewService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全ての予約のレビューのリストを取得")
  @ResponseBody
  public Response<List<ReservationReviewResponseData>> getAllReview(
      @ApiParam(value = "宿泊施設のオーナーのid", example = "1") @RequestParam(value = "owner-id",
          required = false) Long ownerId,
      @ApiParam(value = "予約者のid", example = "1") @RequestParam(value = "reviewer-id",
          required = false) Long reviewerId) {
    return Response.success(reservationReviewService.getAllReview(ownerId, reviewerId));
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされた予約のレビューのリストを取得")
  public Response<Page<ReservationReviewResponseData>> getReview(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize) {
    return Response.success(new Page<ReservationReviewResponseData>(
        reservationReviewService.getReview(pageNumber, pageSize)));
  }

  @PostMapping
  @ResponseBody
  @ApiOperation(value = "予約のレビューを作成")
  public Response<ReservationReviewResponseData> createReview(
      @Validated @RequestBody ReservationReviewRequestParam reservationReview) {
    return Response.success(reservationReviewService.createReview(reservationReview));
  }

  @PutMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの予約のレビューを更新")
  public Response<ReservationReviewResponseData> updateReview(
      @PathVariable("id") @ApiParam(value = "予約のレビューのid", example = "1") long id,
      @Validated @RequestBody ReservationReviewRequestParam reservationReview) {
    return Response.success(reservationReviewService.updateReview(id, reservationReview));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの予約のレビューを削除")
  public Response<ReservationReviewResponseData> deleteReview(
      @PathVariable("id") @ApiParam(value = "予約のレビューのid", example = "1") long id) {
    return Response.success(reservationReviewService.deleteReview(id));
  }

  @DeleteMapping(value = "/bulk/{ids}")
  @ResponseBody
  public Response<List<ReservationReviewResponseData>> deleteReviews(
      @PathVariable("ids") @ApiParam(value = "予約のレビューのidの一覧", example = "1,2") String ids) {
    return Response.success(
        reservationReviewService.deleteReviews(FormatDataUitl.convertStringIdsToLongArray(ids)));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの予約のレビューを取得")
  public Response<ReservationReviewResponseData> getReview(
      @PathVariable("id") @ApiParam(value = "予約のレビューのid", example = "1") long id) {
    return Response.success(reservationReviewService.getReview(id).orElse(null));
  }
}

package com.alichan.hostnavi.admin.application.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.domain.service.UserCreditCardService;
import com.alichan.hostnavi.admin.dto.requestparam.UserCreditCardRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.UserCreditCardResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "クレジットカードAPI", description = "クレジットカードのCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/credit-cards")
public class UserCreditCardController {
  @Autowired
  private UserCreditCardService userCreditCardService;

  // @GetMapping(path = "/all")
  // @ApiOperation(value = "全てのクレジットカードのリストを取得")
  // @ResponseBody
  // public Response<List<UserCreditCardResponseData>> getAllCreditCard() {
  // return Response.success(userCreditCardService.getAllCreditCard());
  // }

  // @GetMapping
  // @ResponseBody
  // @ApiOperation(value = "指定したページのページングされたクレジットカードのリストを取得")
  // public Response<Page<UserCreditCardResponseData>> getCreditCard(
  // @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
  // required = true) int pageNumber,
  // @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
  // defaultValue = "5", required = false) int pageSize) {
  // return Response.success(new Page<UserCreditCardResponseData>(
  // userCreditCardService.getCreditCard(pageNumber, pageSize)));
  // }

  @PostMapping
  @ResponseBody
  @ApiOperation(value = "クレジットカードを作成")
  public Response<UserCreditCardResponseData> createCreditCard(
      @Validated @RequestBody UserCreditCardRequestParam UserCreditCard) {
    return Response.success(userCreditCardService.createCreditCard(UserCreditCard));
  }

  @PutMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのクレジットカードを更新")
  public Response<UserCreditCardResponseData> updateCreditCard(
      @PathVariable("id") @ApiParam(value = "クレジットカードのid", example = "1") long id,
      @Validated @RequestBody UserCreditCardRequestParam userCreditCard) {
    return Response.success(userCreditCardService.updateCreditCard(id, userCreditCard));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのクレジットカードを削除")
  public Response<UserCreditCardResponseData> deleteCreditCard(
      @PathVariable("id") @ApiParam(value = "クレジットカードのid", example = "1") long id) {
    return Response.success(userCreditCardService.deleteCreditCard(id));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのクレジットカードを取得")
  public Response<UserCreditCardResponseData> getCreditCard(
      @PathVariable("id") @ApiParam(value = "クレジットカードのid", example = "1") long id) {
    return Response.success(userCreditCardService.getCreditCard(id).orElse(null));
  }
}

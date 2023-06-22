package com.alichan.hostnavi.admin.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.application.util.Page;
import com.alichan.hostnavi.admin.domain.service.UserUserService;
import com.alichan.hostnavi.admin.dto.requestparam.ImageRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UpdatedUserUserRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UserUserRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.UserUserResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "ユーザーAPI", description = "ユーザーのCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/users")
public class UserUserController {
  @Autowired
  private UserUserService userUserService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全てのユーザーのリストを取得")
  @ResponseBody
  public Response<List<UserUserResponseData>> getAllUser() {
    return Response.success(userUserService.getAllUser());
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされたユーザーのリストを取得")
  public Response<Page<UserUserResponseData>> getUser(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize) {
    return Response
        .success(new Page<UserUserResponseData>(userUserService.getUser(pageNumber, pageSize)));
  }

  @PostMapping
  @ResponseBody
  @ApiOperation(value = "ユーザーを作成")
  public Response<UserUserResponseData> createUser(
      @Validated @RequestBody UserUserRequestParam UserUser) {
    return Response.success(userUserService.createUser(UserUser));
  }

  @PutMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのユーザーを更新")
  public Response<UserUserResponseData> updateUser(
      @PathVariable("id") @ApiParam(value = "ユーザーのid", example = "1") long id,
      @Validated @RequestBody UpdatedUserUserRequestParam userUser) {
    return Response.success(userUserService.updateUser(id, userUser));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのユーザーを削除")
  public Response<UserUserResponseData> deleteUser(
      @PathVariable("id") @ApiParam(value = "ユーザーのid", example = "1") long id) {
    return Response.success(userUserService.deleteUser(id));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのユーザーを取得")
  public Response<UserUserResponseData> getUser(
      @PathVariable("id") @ApiParam(value = "ユーザーのid", example = "1") long id) {
    return Response.success(userUserService.getUser(id).orElse(null));
  }

  @PostMapping(value = "/image/{id}")
  @ResponseBody
  @ApiOperation(value = "ユーザーの画像を作成")
  public Response<ImageResponseData> createUserImage(
      @PathVariable("id") @ApiParam(value = "ユーザーのid", example = "1") long id,
      @Validated @ModelAttribute ImageRequestParam image) {
    return Response.success(userUserService.createUserImage(id, image));
  }
}

package com.alichan.hostnavi.admin.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.application.util.Page;
import com.alichan.hostnavi.admin.domain.service.impl.InnAmenityService;
import com.alichan.hostnavi.admin.dto.responsedata.InnAmenityResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "宿泊施設のアメニティAPI", description = "宿泊施設のアメニティが取得できるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/amenities")
public class InnAmenityController {
  @Autowired
  private InnAmenityService innAmenityService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全てのアメニティのリストを取得")
  @ResponseBody
  public Response<List<InnAmenityResponseData>> getAllAmenity() {
    return Response.success(innAmenityService.getAllAmenity());
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされたアメニティのリストを取得")
  public Response<Page<InnAmenityResponseData>> getInnAmenityResponseData(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize) {
    return Response.success(
        new Page<InnAmenityResponseData>(innAmenityService.getAmenity(pageNumber, pageSize)));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidのアメニティを取得")
  public Response<InnAmenityResponseData> getAmenity(
      @PathVariable("id") @ApiParam(value = "アメニティのid", example = "1") int id) {
    return Response.success(innAmenityService.getAmenity(id).orElse(null));
  }
}

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
import com.alichan.hostnavi.admin.domain.service.impl.InnFacilityService;
import com.alichan.hostnavi.admin.dto.responsedata.InnFacilityResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "宿泊施設に設置されている施設のAPI", description = "宿泊施設に設置されている施設が取得できるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/facilities")
public class InnFacilityController {
  @Autowired
  private InnFacilityService innFacilityService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全ての施設のリストを取得")
  @ResponseBody
  public Response<List<InnFacilityResponseData>> getAllFacility() {
    return Response.success(innFacilityService.getAllFacility());
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされた施設のリストを取得")
  public Response<Page<InnFacilityResponseData>> getInnFacility(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize) {
    return Response.success(
        new Page<InnFacilityResponseData>(innFacilityService.getFacility(pageNumber, pageSize)));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの施設を取得")
  public Response<InnFacilityResponseData> getFacility(
      @PathVariable("id") @ApiParam(value = "施設のid", example = "1") int id) {
    return Response.success(innFacilityService.getFacility(id).orElse(null));
  }
}

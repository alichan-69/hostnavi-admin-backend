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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.form.FormattedInnViewUrlParam;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.application.util.Page;
import com.alichan.hostnavi.admin.domain.service.impl.InnViewService;
import com.alichan.hostnavi.admin.dto.requestparam.InnViewRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.InnViewResponseData;
import com.alichan.hostnavi.admin.util.FormatDataUitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "宿泊施設のビューのAPI", description = "宿泊施設のビューのCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/views")
public class InnViewController {
  @Autowired
  private InnViewService innViewService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全ての宿泊施設のビューのリストを取得")
  @ResponseBody
  public Response<List<InnViewResponseData>> getAllView(
      @ApiParam(value = "宿泊施設の所有者のid", example = "1") @RequestParam(value = "owner-id",
          required = false) Long ownerId,
      @ApiParam(value = "これ以降にビューされたビューのみ取得するための指定時間",
          example = "2023-01-01T00:00:00") @RequestParam(value = "create-time-after",
              required = false) String createTimeAfter,
      @ApiParam(value = "これ以前にビューされたビューのみ取得するための指定時間",
          example = "2023-01-01T00:00:00") @RequestParam(value = "create-time-before",
              required = false) String createTimeBefore) {
    FormattedInnViewUrlParam formattedInnViewUrlParam =
        convertUrlParamToFormattedParam(createTimeAfter, createTimeBefore);

    return Response.success(
        innViewService.getAllView(ownerId, formattedInnViewUrlParam.getCreateTimeAfterDate(),
            formattedInnViewUrlParam.getCreateTimeBeforeDate()));
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされた宿泊施設のビューのリストを取得")
  public Response<Page<InnViewResponseData>> getView(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize) {
    return Response
        .success(new Page<InnViewResponseData>(innViewService.getView(pageNumber, pageSize)));
  }

  @PostMapping
  @ResponseBody
  @ApiOperation(value = "宿泊施設のビューを作成")
  public Response<InnViewResponseData> createView(
      @Validated @RequestBody InnViewRequestParam innView) {
    return Response.success(innViewService.createView(innView));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの宿泊施設のビューを削除")
  public Response<InnViewResponseData> deleteView(
      @PathVariable("id") @ApiParam(value = "宿泊施設のビューのid", example = "1") long id) {
    return Response.success(innViewService.deleteView(id));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの宿泊施設のビューを取得")
  public Response<InnViewResponseData> getView(
      @PathVariable("id") @ApiParam(value = "宿泊施設のビューのid", example = "1") long id) {
    return Response.success(innViewService.getView(id).orElse(null));
  }

  private FormattedInnViewUrlParam convertUrlParamToFormattedParam(String createTimeAfter,
      String createTimeBefore) {
    Date formattedCreateTimeAfter = null;
    Date formattedCreateTimeBefore = null;

    if (createTimeAfter != null)
      formattedCreateTimeAfter = FormatDataUitl.convertStringDateToDate(createTimeAfter);
    if (createTimeBefore != null)
      formattedCreateTimeBefore = FormatDataUitl.convertStringDateToDate(createTimeBefore);

    FormattedInnViewUrlParam formattedInnViewUrlParam = new FormattedInnViewUrlParam();

    formattedInnViewUrlParam.setCreateTimeAfterDate(formattedCreateTimeAfter);
    formattedInnViewUrlParam.setCreateTimeBeforeDate(formattedCreateTimeBefore);

    return formattedInnViewUrlParam;
  }
}

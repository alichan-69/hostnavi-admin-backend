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
import com.alichan.hostnavi.admin.domain.service.InnInnService;
import com.alichan.hostnavi.admin.dto.requestparam.ImagesRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnResponseData;
import com.alichan.hostnavi.admin.util.FormatDataUitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "宿泊施設API", description = "宿泊施設のCRUD処理ができるAPI")
@Controller
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/inns")
public class InnInnController {
  @Autowired
  private InnInnService innInnService;

  @GetMapping(path = "/all")
  @ApiOperation(value = "全ての宿泊施設のリストを取得")
  @ResponseBody
  public Response<List<InnInnResponseData>> getAllInn(@ApiParam(value = "宿泊施設の所有者のid",
      example = "1") @RequestParam(value = "owner-id", required = false) Long ownerId) {
    return Response.success(innInnService.getAllInn(ownerId));
  }

  @GetMapping
  @ResponseBody
  @ApiOperation(value = "指定したページのページングされた宿泊施設のリストを取得")
  public Response<Page<InnInnResponseData>> getInn(
      @ApiParam(value = "指定ページ", example = "1") @RequestParam(value = "page-number",
          required = true) int pageNumber,
      @ApiParam(value = "一ページに表示するデータ件数", example = "5") @RequestParam(value = "page-size",
          defaultValue = "5", required = false) int pageSize) {
    return Response
        .success(new Page<InnInnResponseData>(innInnService.getInn(pageNumber, pageSize)));
  }

  @PostMapping
  @ResponseBody
  @ApiOperation(value = "宿泊施設を作成")
  public Response<InnInnResponseData> createInn(@Validated @RequestBody InnInnRequestParam InnInn) {
    return Response.success(innInnService.createInn(InnInn));
  }

  @PutMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの宿泊施設を更新")
  public Response<InnInnResponseData> updateInn(
      @PathVariable("id") @ApiParam(value = "宿泊施設のid", example = "1") long id,
      @Validated @RequestBody InnInnRequestParam innInn) {

    return Response.success(innInnService.updateInn(id, innInn));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの宿泊施設を削除")
  public Response<InnInnResponseData> deleteInn(
      @PathVariable("id") @ApiParam(value = "宿泊施設のid", example = "1") long id) {
    return Response.success(innInnService.deleteInn(id));
  }

  @DeleteMapping(value = "/bulk/{ids}")
  @ResponseBody
  @ApiOperation(value = "指定したidの宿泊施設をバルク削除")
  public Response<List<InnInnResponseData>> deleteInns(
      @PathVariable("ids") @ApiParam(value = "宿泊施設のids", example = "1") String ids) {
    return Response
        .success(innInnService.deleteInns(FormatDataUitl.convertStringIdsToLongArray(ids)));
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  @ApiOperation(value = "指定したidの宿泊施設を取得")
  public Response<InnInnResponseData> getInn(
      @PathVariable("id") @ApiParam(value = "宿泊施設のid", example = "1") long id) {
    return Response.success(innInnService.getInn(id).orElse(null));
  }

  @PostMapping(value = "/image/{id}")
  @ResponseBody
  @ApiOperation(value = "宿泊施設の画像を作成")
  public Response<List<ImageResponseData>> createInnImage(
      @PathVariable("id") @ApiParam(value = "宿泊施設のid", example = "1") long id,
      @Validated @ModelAttribute ImagesRequestParam images) {
    return Response.success(innInnService.createInnImage(id, images));
  }
}

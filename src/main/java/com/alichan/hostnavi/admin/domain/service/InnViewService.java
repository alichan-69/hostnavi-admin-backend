package com.alichan.hostnavi.admin.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.InnInnLogic;
import com.alichan.hostnavi.admin.domain.logic.InnPageLogic;
import com.alichan.hostnavi.admin.domain.logic.InnViewLogic;
import com.alichan.hostnavi.admin.domain.logic.UserUserLogic;
import com.alichan.hostnavi.admin.dto.requestparam.InnViewRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.InnViewResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.InnViewCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnViewMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnViewCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnView;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class InnViewService {
  @Autowired
  private InnViewMapper innViewMapper;
  @Autowired
  private InnViewCustomMapper innViewCustomMapper;
  @Autowired
  private InnInnLogic innInnLogic;
  @Autowired
  private UserUserLogic userUserLogic;
  @Autowired
  private InnPageLogic innPageLogic;
  @Autowired
  private InnViewLogic innViewLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();


  public List<InnViewResponseData> getAllView(Long ownerId, Date createTimeAfter,
      Date createTimeBefore) {

    List<InnViewCustom> gottenViews =
        innViewCustomMapper.selectAll(ownerId, createTimeAfter, createTimeBefore);

    List<InnViewResponseData> viewResponseDatas =
        convertLisCustomModelToListResponseData(gottenViews);

    return viewResponseDatas;
  }

  public List<InnViewResponseData> getView(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return getAllView(null, null, null);
  }

  public InnViewResponseData createView(InnViewRequestParam innViewRequestParam) {

    InnView insertedView =
        convertRequestParamToInsertedModel(innViewRequestParam, new Date(), false);

    innViewMapper.insertSelective(insertedView);

    InnViewResponseData viewResponseData = getView(insertedView.getId()).get();

    return viewResponseData;
  }

  public InnViewResponseData deleteView(long id) {
    Optional<InnViewResponseData> viewOptional = getView(id);
    viewOptional.ifPresentOrElse(view -> {
      InnView deletedView = innViewLogic.createDeletedModel(id);
      innViewMapper.updateByPrimaryKeySelective(deletedView);
    }, () -> {
      Assert.failedValidation("ビューのidが存在しませんでした。");
    });

    return viewOptional.get();
  }

  public Optional<InnViewResponseData> getView(long id) {
    InnView view = innViewMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(view == null ? null : convertModelToResponseData(view));
  }

  private InnView convertRequestParamToInsertedModel(InnViewRequestParam innViewRequestParam,
      Date createTime, Boolean deleteFlag) {
    InnView innView = modelMapper.map(innViewRequestParam, InnView.class);

    innView.setCreateTime(createTime);
    innView.setDeleteFlag(deleteFlag);

    return innView;
  }

  public InnViewResponseData convertModelToResponseData(InnView innView) {
    InnViewResponseData innViewResponseData = modelMapper.map(innView, InnViewResponseData.class);

    innViewResponseData.setInn(innInnLogic.getInn(innView.getInnId()).get());
    innViewResponseData.setViewer(userUserLogic.getUser(innView.getViewerId()).get());
    innViewResponseData.setPage(innPageLogic.getPage(innView.getPageId()).get());

    return innViewResponseData;
  }

  public List<InnViewResponseData> convertListModelToListResponseData(List<InnView> innViews) {
    List<InnViewResponseData> innViewResponseDatas = innViews.stream()
        .map(view -> convertModelToResponseData(view)).collect(Collectors.toList());
    return innViewResponseDatas;
  }

  public InnViewResponseData convertCustomModelToResponseData(InnViewCustom innView) {
    InnViewResponseData innViewResponseData = modelMapper.map(innView, InnViewResponseData.class);

    return innViewResponseData;
  }

  public List<InnViewResponseData> convertLisCustomModelToListResponseData(
      List<InnViewCustom> innViews) {
    List<InnViewResponseData> innViewResponseDatas = innViews.stream()
        .map(view -> convertCustomModelToResponseData(view)).collect(Collectors.toList());
    return innViewResponseDatas;
  }
}

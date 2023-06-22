package com.alichan.hostnavi.admin.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnAmenityRelationRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnAmenityRelationResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnInnAmenityRelationMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnAmenityRelation;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnAmenityRelationExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class InnInnAmenityRelationLogic {
  @Autowired
  private InnInnAmenityRelationMapper innInnAmenityRelationMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnInnAmenityRelationResponseData> getInnAmenityRelation(List<Long> ids) {
    List<InnInnAmenityRelation> innAmenityRelation = new ArrayList<InnInnAmenityRelation>();
    if (!ids.isEmpty()) {
      InnInnAmenityRelationExample innAmenityRelationExample = new InnInnAmenityRelationExample();
      innAmenityRelationExample.createCriteria().andIdIn(ids).andDeleteFlagEqualTo(false);
      innAmenityRelation = innInnAmenityRelationMapper.selectByExample(innAmenityRelationExample);
    }
    return convertListModelToListResponseData(innAmenityRelation);
  }

  public List<InnInnAmenityRelationResponseData> getInnAmenityRelationByInnId(long innId) {
    InnInnAmenityRelationExample innAmenityRelationExample = new InnInnAmenityRelationExample();
    innAmenityRelationExample.createCriteria().andInnIdEqualTo(innId).andDeleteFlagEqualTo(false);
    return convertListModelToListResponseData(
        innInnAmenityRelationMapper.selectByExample(innAmenityRelationExample));
  }

  public List<InnInnAmenityRelationResponseData> createInnInnAmenityRelation(
      InnInnAmenityRelationRequestParam innInnAmenityRelationRequestParam) {
    List<InnInnAmenityRelation> insertedInnAmenityRelations = convertRequestParamToInsertedModel(
        innInnAmenityRelationRequestParam, new Date(), new Date(), false);

    List<Long> ids = insertedInnAmenityRelations.stream().map(insertedInnAmenityRelation -> {
      innInnAmenityRelationMapper.insertSelective(insertedInnAmenityRelation);
      return insertedInnAmenityRelation.getId();
    }).collect(Collectors.toList());

    List<InnInnAmenityRelationResponseData> innAmenityRelationResponseDatas =
        getInnAmenityRelation(ids);

    return innAmenityRelationResponseDatas;
  }

  public List<InnInnAmenityRelationResponseData> updateInnInnAmenityRelation(
      InnInnAmenityRelationRequestParam innInnAmenityRelationRequestParam) {
    // 更新対象の宿泊施設idを持つ宿泊施設とアメニティの関係のデータの削除フラグを全てtrueにする
    List<InnInnAmenityRelationResponseData> innAmenityRelationResponseDatas =
        getInnAmenityRelationByInnId(innInnAmenityRelationRequestParam.getInnId());
    innAmenityRelationResponseDatas.stream().forEach(innAmenityRelationResponseData -> {
      deleteInnAmenityRelation(innAmenityRelationResponseData.getId());
    });

    // 更新対象の宿泊施設idとアメニティidを持つ宿泊施設とアメニティの関係のデータが存在すれば削除フラグをfalseに、存在しなければ作成
    innInnAmenityRelationRequestParam.getAmenityIds().forEach(amenityId -> {
      InnInnAmenityRelationExample innAmenityRelationExample = new InnInnAmenityRelationExample();
      innAmenityRelationExample.createCriteria()
          .andInnIdEqualTo(innInnAmenityRelationRequestParam.getInnId())
          .andAmenityIdEqualTo(amenityId);
      List<InnInnAmenityRelation> innAmenityRelations =
          innInnAmenityRelationMapper.selectByExample(innAmenityRelationExample);

      if (innAmenityRelations.isEmpty()) {
        innInnAmenityRelationMapper.insertSelective(
            convertRequestParamToInsertedModel(innInnAmenityRelationRequestParam.getInnId(),
                amenityId, new Date(), new Date(), false));
      } else {
        innInnAmenityRelationMapper.updateByPrimaryKeySelective(
            createUpdatedModel(innAmenityRelations.get(0).getId(), new Date(), false));
      }
    });

    return getInnAmenityRelationByInnId(innInnAmenityRelationRequestParam.getInnId());
  }

  public void deleteInnAmenityRelation(long id) {
    InnInnAmenityRelation deletedInn = createDeletedModel(id);
    innInnAmenityRelationMapper.updateByPrimaryKeySelective(deletedInn);
  }

  public List<InnInnAmenityRelationResponseData> deleteInnAmenityRelationsByInnId(long innId) {
    List<InnInnAmenityRelationResponseData> innInnAmenityRelationResponseDatas =
        getInnAmenityRelationByInnId(innId);

    innInnAmenityRelationResponseDatas.forEach(innInnAmenityRelationResponseData -> {
      InnInnAmenityRelation deletedInn =
          createDeletedModel(innInnAmenityRelationResponseData.getId());
      innInnAmenityRelationMapper.updateByPrimaryKeySelective(deletedInn);
    });

    return innInnAmenityRelationResponseDatas;
  }

  private List<InnInnAmenityRelation> convertRequestParamToInsertedModel(
      InnInnAmenityRelationRequestParam innInnAmenityRelationRequestParam, Date createTime,
      Date updateTime, Boolean deleteFlag) {

    return innInnAmenityRelationRequestParam.getAmenityIds().stream().map((amenityId) -> {
      InnInnAmenityRelation innInnAmenityRelation = new InnInnAmenityRelation();
      innInnAmenityRelation.setInnId(innInnAmenityRelationRequestParam.getInnId());
      innInnAmenityRelation.setAmenityId(amenityId);
      innInnAmenityRelation.setCreateTime(createTime);
      innInnAmenityRelation.setUpdateTime(updateTime);
      innInnAmenityRelation.setDeleteFlag(deleteFlag);

      return innInnAmenityRelation;
    }).collect(Collectors.toList());
  }

  private InnInnAmenityRelation convertRequestParamToInsertedModel(Long innId, Integer amenityId,
      Date createTime, Date updateTime, Boolean deleteFlag) {
    InnInnAmenityRelation innInnAmenityRelation = new InnInnAmenityRelation();
    innInnAmenityRelation.setInnId(innId);
    innInnAmenityRelation.setAmenityId(amenityId);
    innInnAmenityRelation.setCreateTime(createTime);
    innInnAmenityRelation.setUpdateTime(updateTime);
    innInnAmenityRelation.setDeleteFlag(deleteFlag);

    return innInnAmenityRelation;
  }

  private InnInnAmenityRelation createUpdatedModel(long id, Date updateTime, boolean deleteFlag) {
    InnInnAmenityRelation innInnAmenityRelation = new InnInnAmenityRelation();

    innInnAmenityRelation.setId(id);
    innInnAmenityRelation.setUpdateTime(updateTime);
    innInnAmenityRelation.setDeleteFlag(deleteFlag);

    return innInnAmenityRelation;
  }

  private InnInnAmenityRelationResponseData convertModelToResponseData(
      InnInnAmenityRelation innInnAmenityRelation) {
    InnInnAmenityRelationResponseData innInnAmenityRelationResponseData =
        modelMapper.map(innInnAmenityRelation, InnInnAmenityRelationResponseData.class);

    return innInnAmenityRelationResponseData;
  }

  private List<InnInnAmenityRelationResponseData> convertListModelToListResponseData(
      List<InnInnAmenityRelation> innInnAmenityRelations) {
    List<InnInnAmenityRelationResponseData> innInnAmenityRelationResponseDatas =
        innInnAmenityRelations.stream().map(inn -> convertModelToResponseData(inn))
            .collect(Collectors.toList());
    return innInnAmenityRelationResponseDatas;
  }

  private InnInnAmenityRelation createDeletedModel(long id) {
    InnInnAmenityRelation innInnAmenityRelation = new InnInnAmenityRelation();

    innInnAmenityRelation.setId(id);
    innInnAmenityRelation.setDeleteFlag(true);

    return innInnAmenityRelation;
  }
}

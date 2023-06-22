package com.alichan.hostnavi.admin.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnFacilityRelationRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnFacilityRelationResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnInnFacilityRelationMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnFacilityRelation;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnFacilityRelationExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class InnInnFacilityRelationLogic {
  @Autowired
  private InnInnFacilityRelationMapper innInnFacilityRelationMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnInnFacilityRelationResponseData> getInnFacilityRelation(List<Long> ids) {
    List<InnInnFacilityRelation> innFacilityRelation = new ArrayList<InnInnFacilityRelation>();
    if (!ids.isEmpty()) {
      InnInnFacilityRelationExample innFacilityRelationExample =
          new InnInnFacilityRelationExample();
      innFacilityRelationExample.createCriteria().andIdIn(ids).andDeleteFlagEqualTo(false);
      innFacilityRelation =
          innInnFacilityRelationMapper.selectByExample(innFacilityRelationExample);
    }
    return convertListModelToListResponseData(innFacilityRelation);
  }

  public List<InnInnFacilityRelationResponseData> getInnFacilityRelationByInnId(long innId) {
    InnInnFacilityRelationExample innFacilityRelationExample = new InnInnFacilityRelationExample();
    innFacilityRelationExample.createCriteria().andInnIdEqualTo(innId).andDeleteFlagEqualTo(false);
    return convertListModelToListResponseData(
        innInnFacilityRelationMapper.selectByExample(innFacilityRelationExample));
  }

  public List<InnInnFacilityRelationResponseData> createInnInnFacilityRelation(
      InnInnFacilityRelationRequestParam innInnFacilityRelationRequestParam) {
    List<InnInnFacilityRelation> insertedInnFacilityRelations = convertRequestParamToInsertedModel(
        innInnFacilityRelationRequestParam, new Date(), new Date(), false);

    List<Long> ids = insertedInnFacilityRelations.stream().map(insertedInnFacilityRelation -> {
      innInnFacilityRelationMapper.insertSelective(insertedInnFacilityRelation);
      return insertedInnFacilityRelation.getId();
    }).collect(Collectors.toList());

    List<InnInnFacilityRelationResponseData> innFacilityRelationResponseDatas =
        getInnFacilityRelation(ids);

    return innFacilityRelationResponseDatas;
  }

  public List<InnInnFacilityRelationResponseData> updateInnInnFacilityRelation(
      InnInnFacilityRelationRequestParam innInnFacilityRelationRequestParam) {
    // 更新対象の宿泊施設idを持つ宿泊施設と施設の関係のデータの削除フラグを全てtrueにする
    List<InnInnFacilityRelationResponseData> innFacilityRelationResponseDatas =
        getInnFacilityRelationByInnId(innInnFacilityRelationRequestParam.getInnId());
    innFacilityRelationResponseDatas.stream().forEach(innFacilityRelationResponseData -> {
      deleteInnFacilityRelation(innFacilityRelationResponseData.getId());
    });

    // 更新対象の宿泊施設idと施設idを持つ宿泊施設と施設の関係のデータが存在すれば削除フラグをfalseに、存在しなければ作成
    innInnFacilityRelationRequestParam.getFacilityIds().forEach(facilityId -> {
      InnInnFacilityRelationExample innFacilityRelationExample =
          new InnInnFacilityRelationExample();
      innFacilityRelationExample.createCriteria()
          .andInnIdEqualTo(innInnFacilityRelationRequestParam.getInnId())
          .andFacilityIdEqualTo(facilityId);
      List<InnInnFacilityRelation> innFacilityRelations =
          innInnFacilityRelationMapper.selectByExample(innFacilityRelationExample);

      if (innFacilityRelations.isEmpty()) {
        innInnFacilityRelationMapper.insertSelective(
            convertRequestParamToInsertedModel(innInnFacilityRelationRequestParam.getInnId(),
                facilityId, new Date(), new Date(), false));
      } else {
        innInnFacilityRelationMapper.updateByPrimaryKeySelective(
            createUpdatedModel(innFacilityRelations.get(0).getId(), new Date(), false));
      }
    });

    return getInnFacilityRelationByInnId(innInnFacilityRelationRequestParam.getInnId());
  }

  public void deleteInnFacilityRelation(long id) {
    InnInnFacilityRelation deletedInn = createDeletedModel(id);
    innInnFacilityRelationMapper.updateByPrimaryKeySelective(deletedInn);
  }

  public List<InnInnFacilityRelationResponseData> deleteInnFacilityRelationsByInnId(long innId) {
    List<InnInnFacilityRelationResponseData> innInnFacilityRelationResponseDatas =
        getInnFacilityRelationByInnId(innId);

    innInnFacilityRelationResponseDatas.forEach(innInnFacilityRelationResponseData -> {
      InnInnFacilityRelation deletedInn =
          createDeletedModel(innInnFacilityRelationResponseData.getId());
      innInnFacilityRelationMapper.updateByPrimaryKeySelective(deletedInn);
    });

    return innInnFacilityRelationResponseDatas;
  }

  private List<InnInnFacilityRelation> convertRequestParamToInsertedModel(
      InnInnFacilityRelationRequestParam innInnFacilityRelationRequestParam, Date createTime,
      Date updateTime, Boolean deleteFlag) {

    return innInnFacilityRelationRequestParam.getFacilityIds().stream().map((facilityId) -> {
      InnInnFacilityRelation innInnFacilityRelation = new InnInnFacilityRelation();
      innInnFacilityRelation.setInnId(innInnFacilityRelationRequestParam.getInnId());
      innInnFacilityRelation.setFacilityId(facilityId);
      innInnFacilityRelation.setCreateTime(createTime);
      innInnFacilityRelation.setUpdateTime(updateTime);
      innInnFacilityRelation.setDeleteFlag(deleteFlag);

      return innInnFacilityRelation;
    }).collect(Collectors.toList());
  }

  private InnInnFacilityRelation convertRequestParamToInsertedModel(Long innId, Integer facilityId,
      Date createTime, Date updateTime, Boolean deleteFlag) {
    InnInnFacilityRelation innInnFacilityRelation = new InnInnFacilityRelation();
    innInnFacilityRelation.setInnId(innId);
    innInnFacilityRelation.setFacilityId(facilityId);
    innInnFacilityRelation.setCreateTime(createTime);
    innInnFacilityRelation.setUpdateTime(updateTime);
    innInnFacilityRelation.setDeleteFlag(deleteFlag);

    return innInnFacilityRelation;
  }

  private InnInnFacilityRelation createUpdatedModel(long id, Date updateTime, boolean deleteFlag) {
    InnInnFacilityRelation innInnFacilityRelation = new InnInnFacilityRelation();

    innInnFacilityRelation.setId(id);
    innInnFacilityRelation.setUpdateTime(updateTime);
    innInnFacilityRelation.setDeleteFlag(deleteFlag);

    return innInnFacilityRelation;
  }

  private InnInnFacilityRelationResponseData convertModelToResponseData(
      InnInnFacilityRelation innInnFacilityRelation) {
    InnInnFacilityRelationResponseData innInnFacilityRelationResponseData =
        modelMapper.map(innInnFacilityRelation, InnInnFacilityRelationResponseData.class);

    return innInnFacilityRelationResponseData;
  }

  private List<InnInnFacilityRelationResponseData> convertListModelToListResponseData(
      List<InnInnFacilityRelation> innInnFacilityRelations) {
    List<InnInnFacilityRelationResponseData> innInnFacilityRelationResponseDatas =
        innInnFacilityRelations.stream().map(inn -> convertModelToResponseData(inn))
            .collect(Collectors.toList());
    return innInnFacilityRelationResponseDatas;
  }

  private InnInnFacilityRelation createDeletedModel(long id) {
    InnInnFacilityRelation innInnFacilityRelation = new InnInnFacilityRelation();

    innInnFacilityRelation.setId(id);
    innInnFacilityRelation.setDeleteFlag(true);

    return innInnFacilityRelation;
  }
}

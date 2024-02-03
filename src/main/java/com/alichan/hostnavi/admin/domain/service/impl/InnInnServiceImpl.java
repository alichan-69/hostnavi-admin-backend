package com.alichan.hostnavi.admin.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.InnImageLogic;
import com.alichan.hostnavi.admin.domain.logic.InnInnAmenityRelationLogic;
import com.alichan.hostnavi.admin.domain.logic.InnInnFacilityRelationLogic;
import com.alichan.hostnavi.admin.domain.logic.InnInnLogic;
import com.alichan.hostnavi.admin.domain.service.InnInnSerivce;
import com.alichan.hostnavi.admin.dto.requestparam.ImagesRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnAmenityRelationRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnFacilityRelationRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.InnInnCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnInnMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnInnCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInn;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class InnInnServiceImpl implements InnInnSerivce {
  @Autowired
  private InnInnMapper innInnMapper;
  @Autowired
  private InnInnCustomMapper innInnCustomMapper;
  @Autowired
  private InnInnLogic innInnLogic;
  @Autowired
  private InnImageLogic innImageLogic;
  @Autowired
  private InnInnAmenityRelationLogic innInnAmenityRelationLogic;
  @Autowired
  private InnInnFacilityRelationLogic innInnFacilityRelationLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  /**
   * 宿泊施設のデータを全件取得する処理
   *
   * @return APIのレスポンスデータとして整形された取得した宿泊施設の全件データ
   */
  public List<InnInnResponseData> getAllInn(Long ownerId) {
    List<InnInnCustom> gottenInns = innInnCustomMapper.selectAll();

    List<InnInnResponseData> innResponseDatas =
        innInnLogic.convertListModelToListResponseData(gottenInns);

    innResponseDatas = filterInn(ownerId, innResponseDatas);

    return innResponseDatas;
  }

  /**
   * 宿泊施設のデータを作成する処理
   *
   * @param innInnRequestParam 宿泊施設の作成の際に必要なAPIのリクエストパラメータ
   * @return APIのレスポンスデータとして整形された作成した宿泊施設のデータ
   */
  public InnInnResponseData createInn(InnInnRequestParam innInnRequestParam) {
    InnInn insertedInn =
        convertRequestParamToInsertedModel(innInnRequestParam, new Date(), new Date(), false);
    innInnMapper.insertSelective(insertedInn);

    // 宿泊施設とアメニティの関係を作成する処理
    InnInnAmenityRelationRequestParam innAmenityRelationRequestParam =
        new InnInnAmenityRelationRequestParam();
    innAmenityRelationRequestParam.setInnId(insertedInn.getId());
    innAmenityRelationRequestParam.setAmenityIds(innInnRequestParam.getAmenityIdList());
    innInnAmenityRelationLogic.createInnInnAmenityRelation(innAmenityRelationRequestParam);

    // 宿泊施設と施設の関係を作成する処理
    InnInnFacilityRelationRequestParam innFacilityRelationRequestParam =
        new InnInnFacilityRelationRequestParam();
    innFacilityRelationRequestParam.setInnId(insertedInn.getId());
    innFacilityRelationRequestParam.setFacilityIds(innInnRequestParam.getFacilityIdList());
    innInnFacilityRelationLogic.createInnInnFacilityRelation(innFacilityRelationRequestParam);

    InnInnResponseData innResponseData = getInn(insertedInn.getId()).get();

    return innResponseData;
  }

  /**
   * 宿泊施設のデータを更新する処理
   *
   * @param id 更新する宿泊施設のid
   * @param innInnRequestParam 宿泊施設の更新の際に必要なAPIのリクエストパラメータ
   * @return APIのレスポンスデータとして整形された更新した宿泊施設のデータ
   */
  public InnInnResponseData updateInn(long id, InnInnRequestParam innInnRequestParam) {
    Optional<InnInnResponseData> innOptional = getInn(id);

    innOptional.ifPresentOrElse(inn -> {
      InnInn updatedInn = convertRequestParamToUpdatedModel(innInnRequestParam, id, new Date());


      innInnMapper.updateByPrimaryKeySelective(updatedInn);

      // 宿泊施設とアメニティの関係を更新する処理
      InnInnAmenityRelationRequestParam innAmenityRelationRequestParam =
          new InnInnAmenityRelationRequestParam();
      innAmenityRelationRequestParam.setInnId(updatedInn.getId());
      innAmenityRelationRequestParam.setAmenityIds(innInnRequestParam.getAmenityIdList());
      innInnAmenityRelationLogic.updateInnInnAmenityRelation(innAmenityRelationRequestParam);

      // 宿泊施設と施設の関係を更新する処理
      InnInnFacilityRelationRequestParam innFacilityRelationRequestParam =
          new InnInnFacilityRelationRequestParam();
      innFacilityRelationRequestParam.setInnId(updatedInn.getId());
      innFacilityRelationRequestParam.setFacilityIds(innInnRequestParam.getFacilityIdList());
      innInnFacilityRelationLogic.updateInnInnFacilityRelation(innFacilityRelationRequestParam);
    }, () -> {
      Assert.failedValidation("宿泊施設のidが存在しません。");
    });

    return getInn(id).get();
  }

  /**
   * 宿泊施設のデータを削除する処理
   *
   * @param id 削除する宿泊施設のid
   * @return APIのレスポンスデータとして整形された削除した宿泊施設のデータ
   */
  public InnInnResponseData deleteInn(long id) {
    return innInnLogic.deleteInn(id);
  }

  /**
   * 宿泊施設のデータをバルク削除する処理
   *
   * @param ids 削除する宿泊施設のidのリスト
   * @return APIのレスポンスデータとして整形された削除した宿泊施設のデータ
   */
  public List<InnInnResponseData> deleteInns(List<Long> ids) {
    return ids.stream().map(id -> innInnLogic.deleteInn(id)).collect(Collectors.toList());
  }

  /**
   * 指定されたidの宿泊施設のデータを取得する処理
   *
   * @param id 宿泊施設のid
   * @return APIのレスポンスデータとして整形された取得した宿泊施設のデータ
   */
  public Optional<InnInnResponseData> getInn(long id) {
    return innInnLogic.getInn(id);
  }

  /**
   * 宿泊施設のデータをページングして取得する処理
   *
   * @param pageNumber 取得するページの番号
   * @param pageSize 1ページのデータ表示件数
   * @return APIのレスポンスデータとして整形されたページングされた宿泊施設のデータのリスト
   */
  public List<InnInnResponseData> getInn(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return getAllInn(null);
  }


  /**
   * 宿泊施設の画像を複数作成する処理
   *
   * @param id 宿泊施設のid
   * @param images 宿泊施設の画像のリスト
   * @return APIのレスポンスデータとして整形された宿泊施設の画像のリスト
   */
  public List<ImageResponseData> createInnImage(long id, ImagesRequestParam images) {
    return innImageLogic.createImage(id, images);
  }

  /**
   * APIのリクエストパラメータを新しく登録するORマッパーのモデルに変換する処理
   *
   * @param innInnRequestParam 宿泊施設のリクエストパラメータ
   * @param createTime 宿泊施設を登録した日付
   * @param updateTime 宿泊施設を更新した日付
   * @param deleteFlag 宿泊施設を論理削除するためのフラグ
   * @return 新しく登録する宿泊施設のORマッパーのモデル
   */
  private InnInn convertRequestParamToInsertedModel(InnInnRequestParam innInnRequestParam,
      Date createTime, Date updateTime, Boolean deleteFlag) {
    InnInn innInn = modelMapper.map(innInnRequestParam, InnInn.class);

    innInn.setCreateTime(createTime);
    innInn.setUpdateTime(updateTime);
    innInn.setDeleteFlag(deleteFlag);

    return innInn;
  }

  /**
   * APIのリクエストパラメータを更新するORマッパーのモデルに変換する処理
   *
   * @param innInnRequestParam 宿泊施設のリクエストパラメータ
   * @param id 宿泊施設のid
   * @param updateTime 宿泊施設を更新した日付
   * @return 更新する宿泊施設のORマッパーのモデル
   */
  private InnInn convertRequestParamToUpdatedModel(InnInnRequestParam innInnRequestParam, long id,
      Date updateTime) {
    InnInn innInn = modelMapper.map(innInnRequestParam, InnInn.class);

    innInn.setId(id);
    innInn.setUpdateTime(updateTime);

    return innInn;
  }

  private List<InnInnResponseData> filterInn(Long ownerId,
      List<InnInnResponseData> innResponseDatas) {
    // ownerIdがnullでない時宿泊施設のownerIdが指定idの宿泊施設のみ取得する処理
    if (ownerId != null) {
      innResponseDatas = innResponseDatas.stream().filter(inn -> {
        return inn.getUser().getId() == ownerId;
      }).collect(Collectors.toList());
    }

    return innResponseDatas;
  }
}

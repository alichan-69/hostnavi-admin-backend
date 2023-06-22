package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.InnInnCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnInnMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnInnCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInn;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class InnInnLogic {
  @Autowired
  private InnInnCustomMapper innInnCustomMapper;
  @Autowired
  private InnInnMapper innInnMapper;
  @Autowired
  private MultipleTablesLogic multipleTablesLogic;
  @Autowired
  private InnInnAmenityRelationLogic innInnAmenityRelationLogic;
  @Autowired
  private InnInnFacilityRelationLogic innInnFacilityRelationLogic;
  @Autowired
  private InnImageLogic innImageLogic;
  @Autowired
  private ReservationReservationLogic reservationReservationLogic;
  @Autowired
  private UserUserLogic userUserLogic;
  @Autowired
  private InnViewLogic innViewLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  /**
   * 指定されたidの宿泊施設のデータを取得する処理
   *
   * @param id 宿泊施設のid
   * @return APIのレスポンスデータとして整形された宿泊施設のデータ
   */
  public Optional<InnInnResponseData> getInn(long id) {
    InnInnCustom inn = innInnCustomMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(inn == null ? null : convertModelToResponseData(inn));
  }

  /**
   * 宿泊施設のデータを削除する処理
   *
   * @param id 削除する宿泊施設のid
   * @return APIのレスポンスデータとして整形された削除した宿泊施設のデータ
   */
  public InnInnResponseData deleteInn(long id) {
    Optional<InnInnResponseData> innOptional = getInn(id);

    innOptional.ifPresentOrElse(inn -> {
      InnInn deletedInn = createDeletedModel(id);
      innInnMapper.updateByPrimaryKeySelective(deletedInn);

      // 宿泊施設に関連する他テーブルのデータを削除する
      // 宿泊施設とアメニティの関係を削除
      innInnAmenityRelationLogic.deleteInnAmenityRelationsByInnId(id);

      // 宿泊施設と施設の関係を削除
      innInnFacilityRelationLogic.deleteInnFacilityRelationsByInnId(id);

      // 予約の削除
      reservationReservationLogic.deleteReservationsByInnId(id);

      // 宿泊施設のビュー数の削除
      innViewLogic.deleteViewsByInnId(id);

      // 宿泊施設の画像の削除
      innImageLogic.deleteInnImageByInnId(id);
    }, () -> {
      Assert.failedValidation("宿泊施設のidが存在しません。");
    });

    return innOptional.get();
  }

  /**
   * 宿泊施設のデータを削除する処理(削除したデータを返さない)
   *
   * @param id 削除する宿泊施設のid
   */
  public void deleteInnWithoutResponseData(long id) {
    InnInn deletedInn = createDeletedModel(id);
    innInnMapper.updateByPrimaryKeySelective(deletedInn);

    // 宿泊施設に関連する他テーブルのデータを削除する
    // 宿泊施設とアメニティの関係を削除
    innInnAmenityRelationLogic.deleteInnAmenityRelationsByInnId(id);

    // 宿泊施設と施設の関係を削除
    innInnFacilityRelationLogic.deleteInnFacilityRelationsByInnId(id);

    // 予約の削除
    reservationReservationLogic.deleteReservationsByInnId(id);

    // 宿泊施設のビュー数の削除
    innViewLogic.deleteViewsByInnId(id);

    // 宿泊施設の画像の削除
    innImageLogic.deleteInnImageByInnId(id);
  }

  /**
   * 指定されたuserIdの宿泊施設のデータを取得する処理
   *
   * @param userId ユーザーのid
   */
  public void deleteInnByUserId(long userId) {
    List<Long> innIds = innInnCustomMapper.selectByUserId(userId).stream().map(inn -> inn.getId())
        .collect(Collectors.toList());

    innIds.forEach(innId -> {
      deleteInnWithoutResponseData(innId);
    });
  }

  /**
   * ORマッパーのモデルをAPIのレスポンスデータに変換する処理
   *
   * @param innInn 宿泊施設のモデル
   * @return APIのレスポンスデータとして整形された宿泊施設のデータ
   */
  public InnInnResponseData convertModelToResponseData(InnInnCustom innInn) {
    InnInnResponseData innInnResponseData = modelMapper.map(innInn, InnInnResponseData.class);

    innInnResponseData.setAmenityList(multipleTablesLogic.getAmenityByInnId(innInn.getId()));
    innInnResponseData.setFacilityList(multipleTablesLogic.getFacilityByInnId(innInn.getId()));
    innInnResponseData.setImageList(innImageLogic.getInnImageByInnId(innInn.getId()));
    innInnResponseData.setUser(userUserLogic.getUser(innInn.getUserId()).orElse(null));

    return innInnResponseData;
  }

  /**
   * ORマッパーのモデルのリストをAPIのレスポンスデータのリストに変換する処理
   *
   * @param innInns 宿泊施設のモデルのリスト
   * @return APIのレスポンスデータとして整形された宿泊施設のデータのリスト
   */
  public List<InnInnResponseData> convertListModelToListResponseData(
      List<InnInnCustom> innInnList) {
    List<InnInnResponseData> innInnResponseDataList = innInnList.stream()
        .map(inn -> convertModelToResponseData(inn)).collect(Collectors.toList());
    return innInnResponseDataList;
  }

  /**
   * 削除するORマッパーのモデルを作成する処理
   *
   * @param id 宿泊施設のid
   * @return 削除するORマッパーのモデル
   */
  public InnInn createDeletedModel(long id) {
    InnInn innInn = new InnInn();

    innInn.setId(id);
    innInn.setDeleteFlag(true);

    return innInn;
  }
}

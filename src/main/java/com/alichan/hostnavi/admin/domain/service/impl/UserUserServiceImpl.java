package com.alichan.hostnavi.admin.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.domain.logic.ImageLogic;
import com.alichan.hostnavi.admin.domain.logic.InnInnLogic;
import com.alichan.hostnavi.admin.domain.logic.ReservationReservationLogic;
import com.alichan.hostnavi.admin.domain.logic.UserCreditCardLogic;
import com.alichan.hostnavi.admin.domain.logic.UserUserLogic;
import com.alichan.hostnavi.admin.domain.service.UserUserService;
import com.alichan.hostnavi.admin.dto.requestparam.ImageRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UpdatedUserUserRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UserCreditCardRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UserUserRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.UserCreditCardResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.UserUserResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.UserUserMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUser;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUserExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class UserUserServiceImpl implements UserUserService {
  @Autowired
  private UserUserMapper userUserMapper;
  @Autowired
  private UserUserLogic userUserLogic;
  @Autowired
  private InnInnLogic innInnLogic;
  @Autowired
  private ReservationReservationLogic reservationReservationLogic;
  @Autowired
  private UserCreditCardLogic userCreditCardLogic;
  @Autowired
  private ImageLogic imageLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<UserUserResponseData> getAllUser() {
    UserUserExample userUserExample = new UserUserExample();
    userUserExample.createCriteria().andDeleteFlagEqualTo(false);

    List<UserUser> gottenUsers = userUserMapper.selectByExample(userUserExample);

    List<UserUserResponseData> userResponseDatas =
        userUserLogic.convertListModelToListResponseData(gottenUsers);

    return userResponseDatas;
  }

  public UserUserResponseData createUser(UserUserRequestParam userUserRequestParam) {
    // クレジットカードがnullの時クレジットカードを作成せず、nullでない時作成する処理
    Long creditCardId = null;
    UserCreditCardRequestParam userCreditCardRequestParam = userUserRequestParam.getCreditCard();
    if (userCreditCardRequestParam != null) {
      UserCreditCardResponseData userCreditCardResponseData =
          userCreditCardLogic.createCreditCard(userCreditCardRequestParam);
      creditCardId = userCreditCardResponseData.getId();
    }

    UserUser insertedUser = convertRequestParamToInsertedModel(userUserRequestParam, creditCardId,
        new Date(), new Date(), false);

    userUserMapper.insertSelective(insertedUser);

    return getUser(insertedUser.getId()).get();
  }

  public UserUserResponseData updateUser(long id,
      UpdatedUserUserRequestParam userUserRequestParam) {
    Optional<UserUserResponseData> userOptional = getUser(id);

    userOptional.ifPresentOrElse(user -> {
      UserUser updatedUser =
          convertRequestParamToUpdatedModel(userUserRequestParam, id, new Date());
      userUserMapper.updateByPrimaryKeySelective(updatedUser);

      if (userUserRequestParam.getCreditCard() != null) {
        userCreditCardLogic.updateCreditCard(userUserRequestParam.getCreditCard());
      }
    }, () -> {
      Assert.failedValidation("ユーザーのidが存在しません。");
    });

    return getUser(id).get();
  }

  public UserUserResponseData deleteUser(long id) {
    Optional<UserUserResponseData> userOptional = getUser(id);

    userOptional.ifPresentOrElse(userResponseData -> {
      UserUser deletedUser = createDeletedModel(id);
      userUserMapper.updateByPrimaryKeySelective(deletedUser);

      innInnLogic.deleteInnByUserId(id);
      userCreditCardLogic.deleteCreditCard(userResponseData.getCreditCard().getId());
      reservationReservationLogic.deleteReservationsByReserverId(id);
    }, () -> {
      Assert.failedValidation("ユーザーのidが存在しません。");
    });

    return userOptional.get();
  }

  public List<UserUserResponseData> getUser(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return getAllUser();
  }

  public Optional<UserUserResponseData> getUser(long id) {
    return userUserLogic.getUser(id);
  }

  public ImageResponseData createUserImage(long id, ImageRequestParam image) {
    MultipartFile imageFile = image.getImage();
    String fileName = imageFile.getOriginalFilename();
    String objectName = "user/" + id + "/" + fileName;

    return imageLogic.createImage(imageFile, objectName).get();
  }

  private UserUser convertRequestParamToInsertedModel(UserUserRequestParam userUserRequestParam,
      Long creditCardId, Date createTime, Date updateTime, Boolean deleteFlag) {
    UserUser userUser = modelMapper.map(userUserRequestParam, UserUser.class);

    userUser.setCreditCardId(creditCardId);
    userUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder()
        .encode(userUserRequestParam.getPassword()));
    userUser.setCreateTime(createTime);
    userUser.setUpdateTime(updateTime);
    userUser.setDeleteFlag(deleteFlag);

    return userUser;
  }

  private UserUser convertRequestParamToUpdatedModel(
      UpdatedUserUserRequestParam userUserRequestParam, long id, Date updateTime) {
    UserUser userUser = modelMapper.map(userUserRequestParam, UserUser.class);

    userUser.setId(id);
    userUser.setUpdateTime(updateTime);

    return userUser;
  }

  private UserUser createDeletedModel(long id) {
    UserUser userUser = new UserUser();

    userUser.setId(id);
    userUser.setDeleteFlag(true);

    return userUser;
  }
}

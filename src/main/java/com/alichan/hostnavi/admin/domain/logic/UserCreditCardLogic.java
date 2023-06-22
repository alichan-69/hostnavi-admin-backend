package com.alichan.hostnavi.admin.domain.logic;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.requestparam.UpdatedUserCreditCardRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UserCreditCardRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.UserCreditCardResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.UserCreditCardMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserCreditCard;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserCreditCardExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class UserCreditCardLogic {
  @Autowired
  private UserCreditCardMapper userCreditCardMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public UserCreditCardResponseData createCreditCard(
      UserCreditCardRequestParam userCreditCardRequestParam) {
    if (confirmIsExistsCardNumber(userCreditCardRequestParam.getCardNumber()))
      Assert.failedValidation("cardNumberは既に登録されています。");

    UserCreditCard insertedCreditCard = convertRequestParamToInsertedModel(
        userCreditCardRequestParam, new Date(), new Date(), false);

    userCreditCardMapper.insertSelective(insertedCreditCard);

    UserCreditCardResponseData creditCardResponseData =
        getCreditCard(insertedCreditCard.getId()).orElse(null);

    return creditCardResponseData;
  }

  public UserCreditCardResponseData updateCreditCard(long id,
      UserCreditCardRequestParam userCreditCardRequestParam) {
    Optional<UserCreditCardResponseData> creditCardOptional = getCreditCard(id);

    creditCardOptional.ifPresentOrElse(creditCard -> {
      UserCreditCard updatedCreditCard =
          convertRequestParamToUpdatedModel(userCreditCardRequestParam, id, new Date());

      userCreditCardMapper.updateByPrimaryKeySelective(updatedCreditCard);
    }, () -> {
      Assert.failedValidation("クレジットカードのidが存在しません。");
    });

    return getCreditCard(id).get();
  }

  public UserCreditCardResponseData updateCreditCard(
      UpdatedUserCreditCardRequestParam userCreditCardRequestParam) {
    Optional<UserCreditCardResponseData> creditCardOptional =
        getCreditCard(userCreditCardRequestParam.getId());

    creditCardOptional.ifPresentOrElse(creditCard -> {
      UserCreditCard updatedCreditCard =
          convertUpdatedRequestParamToUpdatedModel(userCreditCardRequestParam, new Date());

      userCreditCardMapper.updateByPrimaryKeySelective(updatedCreditCard);
    }, () -> {
      Assert.failedValidation("クレジットカードのidが存在しません。");
    });

    return getCreditCard(userCreditCardRequestParam.getId()).get();
  }

  public UserCreditCardResponseData deleteCreditCard(long id) {
    Optional<UserCreditCardResponseData> creditCardOptional = getCreditCard(id);

    creditCardOptional.ifPresentOrElse(creditCard -> {
      UserCreditCard deletedCreditCard = createDeletedModel(id);
      userCreditCardMapper.updateByPrimaryKeySelective(deletedCreditCard);
    }, () -> {
      Assert.failedValidation("クレジットカードのidが存在しません。");
    });

    return creditCardOptional.get();
  }

  public Optional<UserCreditCardResponseData> getCreditCard(Long id) {
    UserCreditCardExample userCreditCardExample = new UserCreditCardExample();
    userCreditCardExample.createCriteria().andIdEqualTo(id).andDeleteFlagEqualTo(false);
    List<UserCreditCard> creditCards = userCreditCardMapper.selectByExample(userCreditCardExample);

    return Optional
        .ofNullable(creditCards.isEmpty() ? null : convertModelToResponseData(creditCards.get(0)));
  }

  public boolean confirmIsExistsCardNumber(String cardNumber) {
    UserCreditCardExample userCreditCardExample = new UserCreditCardExample();
    userCreditCardExample.createCriteria().andCardNumberEqualTo(cardNumber)
        .andDeleteFlagEqualTo(false);

    List<UserCreditCard> gottenCreditCards =
        userCreditCardMapper.selectByExample(userCreditCardExample);

    return !gottenCreditCards.isEmpty();
  }

  private UserCreditCardResponseData convertModelToResponseData(UserCreditCard creditCard) {
    UserCreditCardResponseData userCreditCardResponseData =
        modelMapper.map(creditCard, UserCreditCardResponseData.class);

    return userCreditCardResponseData;
  }

  public List<UserCreditCardResponseData> convertListModelToListResponseData(
      List<UserCreditCard> creditCards) {
    List<UserCreditCardResponseData> userCreditCardResponseDatas = creditCards.stream()
        .map(user -> convertModelToResponseData(user)).collect(Collectors.toList());
    return userCreditCardResponseDatas;
  }

  private UserCreditCard convertRequestParamToInsertedModel(
      UserCreditCardRequestParam userCreditCardRequestParam, Date createTime, Date updateTime,
      Boolean deleteFlag) {
    UserCreditCard userCreditCard =
        modelMapper.map(userCreditCardRequestParam, UserCreditCard.class);

    userCreditCard.setCreateTime(createTime);
    userCreditCard.setUpdateTime(updateTime);
    userCreditCard.setDeleteFlag(deleteFlag);

    return userCreditCard;
  }

  private UserCreditCard convertRequestParamToUpdatedModel(
      UserCreditCardRequestParam userCreditCardRequestParam, long id, Date updateTime) {
    UserCreditCard userCreditCard =
        modelMapper.map(userCreditCardRequestParam, UserCreditCard.class);

    userCreditCard.setId(id);
    userCreditCard.setUpdateTime(updateTime);

    return userCreditCard;
  }

  private UserCreditCard convertUpdatedRequestParamToUpdatedModel(
      UpdatedUserCreditCardRequestParam userCreditCardRequestParam, Date updateTime) {
    UserCreditCard userCreditCard =
        modelMapper.map(userCreditCardRequestParam, UserCreditCard.class);

    userCreditCard.setUpdateTime(updateTime);

    return userCreditCard;
  }

  private UserCreditCard createDeletedModel(long id) {
    UserCreditCard userCreditCard = new UserCreditCard();

    userCreditCard.setId(id);
    userCreditCard.setDeleteFlag(true);

    return userCreditCard;
  }
}

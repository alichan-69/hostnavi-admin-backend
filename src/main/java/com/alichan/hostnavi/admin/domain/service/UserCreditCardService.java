package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.UserCreditCardLogic;
import com.alichan.hostnavi.admin.dto.requestparam.UserCreditCardRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.UserCreditCardResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.UserCreditCardMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserCreditCard;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserCreditCardExample;
import com.github.pagehelper.PageHelper;

@Service
public class UserCreditCardService {
  @Autowired
  private UserCreditCardMapper userCreditCardMapper;
  @Autowired
  private UserCreditCardLogic userCreditCardLogic;

  public List<UserCreditCardResponseData> getAllCreditCard() {
    UserCreditCardExample userCreditCardExample = new UserCreditCardExample();
    userCreditCardExample.createCriteria().andDeleteFlagEqualTo(false);

    List<UserCreditCard> gottenCreditCards =
        userCreditCardMapper.selectByExample(userCreditCardExample);

    List<UserCreditCardResponseData> userResponseDatas =
        userCreditCardLogic.convertListModelToListResponseData(gottenCreditCards);

    return userResponseDatas;
  }

  public UserCreditCardResponseData createCreditCard(
      UserCreditCardRequestParam userCreditCardRequestParam) {
    return userCreditCardLogic.createCreditCard(userCreditCardRequestParam);
  }

  public UserCreditCardResponseData updateCreditCard(long id,
      UserCreditCardRequestParam userCreditCardRequestParam) {
    return userCreditCardLogic.updateCreditCard(id, userCreditCardRequestParam);
  }

  public UserCreditCardResponseData deleteCreditCard(long id) {
    return userCreditCardLogic.deleteCreditCard(id);
  }

  public List<UserCreditCardResponseData> getCreditCard(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return getAllCreditCard();
  }

  public Optional<UserCreditCardResponseData> getCreditCard(Long id) {
    return userCreditCardLogic.getCreditCard(id);
  }
}

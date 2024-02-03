package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.UserCreditCardRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.UserCreditCardResponseData;

public interface UserCreditCardService {
  public List<UserCreditCardResponseData> getAllCreditCard();

  public UserCreditCardResponseData createCreditCard(
      UserCreditCardRequestParam userCreditCardRequestParam);

  public UserCreditCardResponseData updateCreditCard(long id,
      UserCreditCardRequestParam userCreditCardRequestParam);

  public UserCreditCardResponseData deleteCreditCard(long id);

  public List<UserCreditCardResponseData> getCreditCard(int pageNumber, int pageSize);

  public Optional<UserCreditCardResponseData> getCreditCard(Long id);
}

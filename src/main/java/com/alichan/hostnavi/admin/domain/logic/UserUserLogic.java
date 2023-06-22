package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.UserUserResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.UserUserMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUser;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUserExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class UserUserLogic {
  @Autowired
  private UserUserMapper userUserMapper;
  @Autowired
  private UserCreditCardLogic userCreditCardLogic;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public Optional<UserUserResponseData> getUser(long id) {
    UserUserExample userUserExample = new UserUserExample();
    userUserExample.createCriteria().andIdEqualTo(id).andDeleteFlagEqualTo(false);
    List<UserUser> users = userUserMapper.selectByExample(userUserExample);

    return Optional.ofNullable(users.isEmpty() ? null : convertModelToResponseData(users.get(0)));
  }

  public UserUserResponseData convertModelToResponseData(UserUser userUser) {
    UserUserResponseData userUserResponseData =
        modelMapper.map(userUser, UserUserResponseData.class);

    Long creditCardId = userUser.getCreditCardId();

    if (creditCardId != null) {
      userUserResponseData
          .setCreditCard(userCreditCardLogic.getCreditCard(creditCardId).orElse(null));
    }

    return userUserResponseData;
  }

  public List<UserUserResponseData> convertListModelToListResponseData(List<UserUser> userUsers) {
    List<UserUserResponseData> userUserResponseDatas = userUsers.stream()
        .map(user -> convertModelToResponseData(user)).collect(Collectors.toList());
    return userUserResponseDatas;
  }

}

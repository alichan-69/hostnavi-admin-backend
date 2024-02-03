package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.ImageRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UpdatedUserUserRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.UserUserRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.UserUserResponseData;

public interface UserUserService {
  public List<UserUserResponseData> getAllUser();

  public UserUserResponseData createUser(UserUserRequestParam userUserRequestParam);

  public UserUserResponseData updateUser(long id, UpdatedUserUserRequestParam userUserRequestParam);

  public UserUserResponseData deleteUser(long id);

  public List<UserUserResponseData> getUser(int pageNumber, int pageSize);

  public Optional<UserUserResponseData> getUser(long id);

  public ImageResponseData createUserImage(long id, ImageRequestParam image);
}

package com.alichan.hostnavi.admin.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.InnViewRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.InnViewResponseData;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnViewCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnView;

public interface InnViewService {
  public List<InnViewResponseData> getAllView(Long ownerId, Date createTimeAfter,
      Date createTimeBefore);

  public List<InnViewResponseData> getView(int pageNumber, int pageSize);

  public InnViewResponseData createView(InnViewRequestParam innViewRequestParam);

  public InnViewResponseData deleteView(long id);

  public Optional<InnViewResponseData> getView(long id);

  public InnViewResponseData convertModelToResponseData(InnView innView);

  public List<InnViewResponseData> convertListModelToListResponseData(List<InnView> innViews);

  public InnViewResponseData convertCustomModelToResponseData(InnViewCustom innView);

  public List<InnViewResponseData> convertLisCustomModelToListResponseData(
      List<InnViewCustom> innViews);
}

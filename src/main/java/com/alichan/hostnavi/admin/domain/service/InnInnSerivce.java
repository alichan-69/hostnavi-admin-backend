package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.ImagesRequestParam;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnResponseData;

public interface InnInnSerivce {
  public List<InnInnResponseData> getAllInn(Long ownerId);

  public InnInnResponseData createInn(InnInnRequestParam innInnRequestParam);

  public InnInnResponseData updateInn(long id, InnInnRequestParam innInnRequestParam);

  public InnInnResponseData deleteInn(long id);

  public List<InnInnResponseData> deleteInns(List<Long> ids);

  public Optional<InnInnResponseData> getInn(long id);

  public List<InnInnResponseData> getInn(int pageNumber, int pageSize);

  public List<ImageResponseData> createInnImage(long id, ImagesRequestParam images);

}

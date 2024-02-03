package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.dto.responsedata.InnStatusResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnStatusMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnStatus;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnStatusExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class InnStatusService {
  @Autowired
  private InnStatusMapper innStatusMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnStatusResponseData> getAllStatus() {
    List<InnStatus> gottenStatuses = innStatusMapper.selectByExample(new InnStatusExample());

    List<InnStatusResponseData> statusResponseDatas =
        convertListModelToListResponseData(gottenStatuses);

    return statusResponseDatas;
  }

  public List<InnStatusResponseData> getStatus(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return convertListModelToListResponseData(
        innStatusMapper.selectByExample(new InnStatusExample()));
  }

  public Optional<InnStatusResponseData> getStatus(int id) {
    InnStatus status = innStatusMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(status == null ? null : convertModelToResponseData(status));
  }

  private InnStatusResponseData convertModelToResponseData(InnStatus status) {
    InnStatusResponseData innStatusResponseData =
        modelMapper.map(status, InnStatusResponseData.class);

    return innStatusResponseData;
  }

  private List<InnStatusResponseData> convertListModelToListResponseData(List<InnStatus> statuses) {
    List<InnStatusResponseData> innStatusResponseDatas = statuses.stream()
        .map(status -> convertModelToResponseData(status)).collect(Collectors.toList());
    return innStatusResponseDatas;
  }
}

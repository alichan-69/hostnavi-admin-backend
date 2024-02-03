package com.alichan.hostnavi.admin.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.service.InnTypeService;
import com.alichan.hostnavi.admin.dto.responsedata.InnTypeResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnTypeMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnType;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnTypeExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;

@Service
public class InnTypeServiceImpl implements InnTypeService {
  @Autowired
  private InnTypeMapper innTypeMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnTypeResponseData> getAllType() {
    List<InnType> gottenTypes = innTypeMapper.selectByExample(new InnTypeExample());

    List<InnTypeResponseData> statusResponseDatas = convertListModelToListResponseData(gottenTypes);

    return statusResponseDatas;
  }

  public List<InnTypeResponseData> getType(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return convertListModelToListResponseData(innTypeMapper.selectByExample(new InnTypeExample()));
  }

  public Optional<InnTypeResponseData> getType(int id) {
    InnType type = innTypeMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(type == null ? null : convertModelToResponseData(type));
  }

  private InnTypeResponseData convertModelToResponseData(InnType type) {
    InnTypeResponseData innTypeResponseData = modelMapper.map(type, InnTypeResponseData.class);

    return innTypeResponseData;
  }

  private List<InnTypeResponseData> convertListModelToListResponseData(List<InnType> types) {
    List<InnTypeResponseData> innTypeResponseDatas =
        types.stream().map(type -> convertModelToResponseData(type)).collect(Collectors.toList());
    return innTypeResponseDatas;
  }
}

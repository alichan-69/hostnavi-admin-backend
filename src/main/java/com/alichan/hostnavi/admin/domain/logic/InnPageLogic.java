package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.dto.responsedata.InnPageResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnPageMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnPage;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnPageExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;
import com.github.pagehelper.PageHelper;


@Component
public class InnPageLogic {
  @Autowired
  private InnPageMapper innPageMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<InnPageResponseData> getAllAmenity() {
    return convertListModelToListResponseData(innPageMapper.selectByExample(new InnPageExample()));
  }

  public List<InnPageResponseData> getPage(int pageNumber, int pageSize) {
    PageHelper.startPage(pageNumber, pageSize);
    return convertListModelToListResponseData(innPageMapper.selectByExample(new InnPageExample()));
  }

  public Optional<InnPageResponseData> getPage(int id) {
    InnPage page = innPageMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(page == null ? null : convertModelToResponseData(page));
  }

  public InnPageResponseData convertModelToResponseData(InnPage page) {
    InnPageResponseData innPageResponseData = modelMapper.map(page, InnPageResponseData.class);

    return innPageResponseData;
  }

  public List<InnPageResponseData> convertListModelToListResponseData(List<InnPage> pages) {
    List<InnPageResponseData> innPageResponseDatas =
        pages.stream().map(page -> convertModelToResponseData(page)).collect(Collectors.toList());
    return innPageResponseDatas;
  }
}

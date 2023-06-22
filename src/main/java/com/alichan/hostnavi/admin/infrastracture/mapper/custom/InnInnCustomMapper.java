package com.alichan.hostnavi.admin.infrastracture.mapper.custom;

import java.util.List;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnInnCustom;

public interface InnInnCustomMapper {
  List<InnInnCustom> selectAll();

  InnInnCustom selectByPrimaryKey(long id);

  List<InnInnCustom> selectByUserId(long id);
}

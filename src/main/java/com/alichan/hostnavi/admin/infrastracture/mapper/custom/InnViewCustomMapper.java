package com.alichan.hostnavi.admin.infrastracture.mapper.custom;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.alichan.hostnavi.admin.infrastracture.model.custom.InnViewCustom;

public interface InnViewCustomMapper {
  List<InnViewCustom> selectAll(@Param("ownerId") Long ownerId,
      @Param("createTimeAfter") Date createTimeAfter,
      @Param("createTimeBefore") Date createTimeBefore);
}

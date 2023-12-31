package com.alichan.hostnavi.admin.infrastracture.mapper.generated;

import com.alichan.hostnavi.admin.infrastracture.model.generated.InnImage;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InnImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    long countByExample(InnImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByExample(InnImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insert(InnImage row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insertSelective(InnImage row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    List<InnImage> selectByExample(InnImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    InnImage selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExampleSelective(@Param("row") InnImage row, @Param("example") InnImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExample(@Param("row") InnImage row, @Param("example") InnImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKeySelective(InnImage row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_image
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKey(InnImage row);
}
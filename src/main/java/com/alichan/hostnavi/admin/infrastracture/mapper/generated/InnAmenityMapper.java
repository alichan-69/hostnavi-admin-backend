package com.alichan.hostnavi.admin.infrastracture.mapper.generated;

import com.alichan.hostnavi.admin.infrastracture.model.generated.InnAmenity;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnAmenityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InnAmenityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    long countByExample(InnAmenityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByExample(InnAmenityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insert(InnAmenity row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insertSelective(InnAmenity row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    List<InnAmenity> selectByExample(InnAmenityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    InnAmenity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExampleSelective(@Param("row") InnAmenity row, @Param("example") InnAmenityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExample(@Param("row") InnAmenity row, @Param("example") InnAmenityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKeySelective(InnAmenity row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_amenity
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKey(InnAmenity row);
}
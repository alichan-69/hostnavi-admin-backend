package com.alichan.hostnavi.admin.infrastracture.mapper.generated;

import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnAmenityRelation;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnAmenityRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InnInnAmenityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    long countByExample(InnInnAmenityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByExample(InnInnAmenityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insert(InnInnAmenityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insertSelective(InnInnAmenityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    List<InnInnAmenityRelation> selectByExample(InnInnAmenityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    InnInnAmenityRelation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExampleSelective(@Param("row") InnInnAmenityRelation row, @Param("example") InnInnAmenityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExample(@Param("row") InnInnAmenityRelation row, @Param("example") InnInnAmenityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKeySelective(InnInnAmenityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_amenity_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKey(InnInnAmenityRelation row);
}
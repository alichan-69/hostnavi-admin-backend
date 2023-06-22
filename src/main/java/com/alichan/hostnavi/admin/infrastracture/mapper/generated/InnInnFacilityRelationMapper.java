package com.alichan.hostnavi.admin.infrastracture.mapper.generated;

import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnFacilityRelation;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnFacilityRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InnInnFacilityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    long countByExample(InnInnFacilityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByExample(InnInnFacilityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insert(InnInnFacilityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insertSelective(InnInnFacilityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    List<InnInnFacilityRelation> selectByExample(InnInnFacilityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    InnInnFacilityRelation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExampleSelective(@Param("row") InnInnFacilityRelation row, @Param("example") InnInnFacilityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExample(@Param("row") InnInnFacilityRelation row, @Param("example") InnInnFacilityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKeySelective(InnInnFacilityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn_facility_relation
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKey(InnInnFacilityRelation row);
}
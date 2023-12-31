package com.alichan.hostnavi.admin.infrastracture.mapper.generated;

import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInn;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnInnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InnInnMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    long countByExample(InnInnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByExample(InnInnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insert(InnInn row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insertSelective(InnInn row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    List<InnInn> selectByExample(InnInnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    InnInn selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExampleSelective(@Param("row") InnInn row, @Param("example") InnInnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExample(@Param("row") InnInn row, @Param("example") InnInnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKeySelective(InnInn row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_inn
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKey(InnInn row);
}
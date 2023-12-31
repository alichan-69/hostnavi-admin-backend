package com.alichan.hostnavi.admin.infrastracture.mapper.generated;

import com.alichan.hostnavi.admin.infrastracture.model.generated.InnView;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InnViewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    long countByExample(InnViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByExample(InnViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insert(InnView row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int insertSelective(InnView row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    List<InnView> selectByExample(InnViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    InnView selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExampleSelective(@Param("row") InnView row, @Param("example") InnViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByExample(@Param("row") InnView row, @Param("example") InnViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKeySelective(InnView row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inn_view
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    int updateByPrimaryKey(InnView row);
}
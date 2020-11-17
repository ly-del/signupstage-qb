package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserCustomize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomizeMapper extends BaseMapper<UserCustomize> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table students_customize
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table students_customize
     *
     * @mbg.generated
     */
    int insert(UserCustomize record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table students_customize
     *
     * @mbg.generated
     */
    int insertSelective(UserCustomize record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table students_customize
     *
     * @mbg.generated
     */
    UserCustomize selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table students_customize
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserCustomize record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table students_customize
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserCustomize record);

    /**
     * 查询自定义列表
     */
    List<UserCustomize> getCustomizeList( UserCustomize customize);


}
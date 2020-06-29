package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.grade;
import com.xiaoshu.entity.gradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface gradeMapper extends BaseMapper<grade> {
    long countByExample(gradeExample example);

    int deleteByExample(gradeExample example);

    List<grade> selectByExample(gradeExample example);

    int updateByExampleSelective(@Param("record") grade record, @Param("example") gradeExample example);

    int updateByExample(@Param("record") grade record, @Param("example") gradeExample example);

	int selectId(String grade);
}
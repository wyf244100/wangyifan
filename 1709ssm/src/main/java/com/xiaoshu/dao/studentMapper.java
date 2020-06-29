package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.student;
import com.xiaoshu.entity.studentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface studentMapper extends BaseMapper<student> {
    long countByExample(studentExample example);

    int deleteByExample(studentExample example);

    List<student> selectByExample(studentExample example);

    int updateByExampleSelective(@Param("record") student record, @Param("example") studentExample example);

    int updateByExample(@Param("record") student record, @Param("example") studentExample example);


	List<student> selectStudent(student student);

	student existStuWithName(String name);

	
}
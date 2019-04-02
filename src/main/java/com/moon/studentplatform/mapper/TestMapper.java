package com.moon.studentplatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Moon
 */
@Repository
@Mapper
public interface TestMapper {

    @Select("select name from goods")
    List<String> getNames();
}

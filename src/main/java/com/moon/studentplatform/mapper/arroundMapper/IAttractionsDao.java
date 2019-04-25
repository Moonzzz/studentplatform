package com.moon.studentplatform.mapper.arroundMapper;

import com.moon.studentplatform.dto.arround.Attractions;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IAttractionsDao {
    @Select("select * from attractions")
    List<Attractions> showAttractions();

    @Insert("Insert into attractions(name,position,description,photos) VALUE(#{name},#{position},#{description},#{photos})")
    boolean addAttraction(Attractions attractions);

    @Delete("delete from attractions where id=#{id}")
    boolean isAttractionDelete(int id);

    @Select("select * from attractions LIMIT #{offset},#{limit}")
    List<Attractions> getLimitAttractions(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from attractions")
    int getAttractionCount();

    @Update("update attractions set name=#{name},position=#{position},description=#{description},\n" +
            " where id=#{id}")
    boolean modifyAttraction(Attractions attraction);


}

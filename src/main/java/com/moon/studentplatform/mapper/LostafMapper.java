package com.moon.studentplatform.mapper;

import com.moon.studentplatform.dto.Found;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface LostafMapper {
    @Select("select * from found LIMIT #{offset},#{limit}")
    List<Found> querry(@Param("offset") int offset, @Param("limit") int limit);

    @Delete("delete from found where id=#{id}")
    int showdelete( int id);

    @Select("select count(*) from found")
    int showcount();

    @Insert("INSERT INTO  found(title,type,adress,description,number,publishtime) VALUES(#{title},#{type},#{adress},#{description},#{number},#{publishtime})")
    boolean toadd(Found found);

    @Select("select * from found where type=#{code}")
    List<Found> toselect(String code);

}

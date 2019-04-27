package com.moon.studentplatform.mapper.arroundMapper;

import com.moon.studentplatform.dto.arround.Food;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IFoodDao {
    @Select("select * from food")
    List<Food> showFoods();
    @Insert("Insert into food(name,position,description,photos) VALUE(#{name},#{position},#{description},#{phitos})")
    boolean addFood(Food food);

    @Delete("delete from food where id=#{id}")
    boolean isFoodDelete(int id);

    @Select("select * from food LIMIT #{offset},#{limit}")
    List<Food> getLimitFoods(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from food")
    int getFoodCount();

    @Update("update food set name=#{name},position=#{position},description=#{description},\n" +
            " where id=#{id}")
    boolean modifyFood(Food food);
}

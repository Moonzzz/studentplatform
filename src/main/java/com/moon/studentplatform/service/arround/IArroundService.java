package com.moon.studentplatform.service.arround;

import com.moon.studentplatform.dto.arround.Attractions;
import com.moon.studentplatform.dto.arround.Food;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IArroundService {

    int getFoodCount();

    int getAttractionCount();

    List<Food> showFoods();

    List<Attractions> showAttractions();

    boolean addFood(MultipartFile picFile,Food food);

    boolean addAttraction(MultipartFile picFile, Attractions attractions);

    boolean isFoodDelete(int id);

    boolean isAttractionDelete(int id);

    List<Food> getLimitFoods(int offset,int limit);

    List<Attractions> getLimitAttractions(int offset,int limit);

    boolean modifyFood(Food food);

    boolean modifyAttraction(Attractions attraction);

}

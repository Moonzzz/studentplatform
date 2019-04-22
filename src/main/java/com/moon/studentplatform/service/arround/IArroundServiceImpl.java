package com.moon.studentplatform.service.arround;

import com.moon.studentplatform.dao.society.IFileDao;
import com.moon.studentplatform.dto.arround.Attractions;
import com.moon.studentplatform.dto.arround.Food;
import com.moon.studentplatform.mapper.arroundMapper.IAttractionsDao;
import com.moon.studentplatform.mapper.arroundMapper.IFoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Service("arroundService")
public class IArroundServiceImpl implements IArroundService {
    @Autowired
    IAttractionsDao iAttractionsDao;
    @Autowired
    IFoodDao foodDao;
    @Autowired
    IFileDao fileDao;
    @Override
    public List<Attractions> showAttractions() {
        return iAttractionsDao.showAttractions();
    }

    @Override
    public List<Food> showFoods() {
        return foodDao.showFoods();
    }

    @Override
    public boolean addAttraction(MultipartFile picFile, Attractions attractions) {
        System.out.println("pic size:  " + picFile.getSize());
        long pSize = picFile.getSize();
        int randomNum = fileDao.getSecondTimestamp(new Date());
        String pSuffix = fileDao.getSuffix(picFile.getOriginalFilename());
        String pRealPath = "D:/ideaWorkspace/studentplatform/src/main/resources/static/arround_files" + randomNum + pSuffix;
        //String icon = randomNum + pSuffix;
        String photo = pSuffix;
        System.out.println("photo:  " + photo);
        //club.setIcon(icon);
        attractions.setPhotos(photo);
        if (iAttractionsDao.addAttraction(attractions)) {
            boolean flag = fileDao.upLoadPhoto(picFile, pRealPath);
            if (flag) return true;
        }
        return false;
    }

    @Override
    public boolean addFood(MultipartFile picFile,Food food) {
        System.out.println("pic size:  " + picFile.getSize());
        long pSize = picFile.getSize();
        int randomNum = fileDao.getSecondTimestamp(new Date());
        String pSuffix = fileDao.getSuffix(picFile.getOriginalFilename());
        String pRealPath = "D:/ideaWorkspace/studentplatform/src/main/resources/static/arround_files" + randomNum + pSuffix;
        //String icon = randomNum + pSuffix;
        String photo = pSuffix;
        System.out.println("photo:  " + photo);
        //club.setIcon(icon);
        food.setPhotos(photo);
        if (foodDao.addFood(food)) {
            boolean flag = fileDao.upLoadPhoto(picFile, pRealPath);
            if (flag) return true;
        }
        return false;
    }

    @Override
    public boolean isAttractionDelete(int id) {
        return iAttractionsDao.isAttractionDelete(id);
    }

    @Override
    public boolean isFoodDelete(int id) {
        return foodDao.isFoodDelete(id);
    }

    @Override
    public List<Attractions> getLimitAttractions(int offset, int limit) {
        return iAttractionsDao.getLimitAttractions(offset, limit);
    }

    @Override
    public boolean modifyFood(Food food) {
        return foodDao.modifyFood(food);
    }

    @Override
    public boolean modifyAttraction(Attractions attraction) {
        return iAttractionsDao.modifyAttraction(attraction);
    }

    @Override
    public List<Food> getLimitFoods(int offset, int limit) {
        return foodDao.getLimitFoods(offset, limit);
    }

    @Override
    public int getFoodCount() {
        return foodDao.getFoodCount();
    }

    @Override
    public int getAttractionCount() {
        return iAttractionsDao.getAttractionCount();
    }
}

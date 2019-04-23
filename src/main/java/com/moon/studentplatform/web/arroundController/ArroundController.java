package com.moon.studentplatform.web.arroundController;

import com.moon.studentplatform.dto.arround.Attractions;
import com.moon.studentplatform.dto.arround.Food;
import com.moon.studentplatform.service.arround.IArroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class ArroundController {
    @Autowired
    IArroundService arroundService;

    List<Attractions> attractions = null;
    List<Food> foods = null;

    @RequestMapping("/allAttraction")
    public String allAttraction(ModelMap map){
        attractions = arroundService.showAttractions();
        if (attractions.size() > 0) {
            map.addAttribute("attractions", attractions);
        }
        return "arroundPage/allattractions";
    }

   /* @RequestMapping("/showallAttraction")
    public JSON showallAttraction(ModelMap map){
        attractions = arroundService.showAttractions();
        if (attractions.size() > 0) {
            map.addAttribute("attractions", attractions);
        }
        return "arroundPage/allattractions";
    }*/

    @RequestMapping("/allFood")
    public String allFood(ModelMap map){
        foods = arroundService.showFoods();
        if (foods.size() > 0) {
            map.addAttribute("foods", foods);
        }
        return "arroundPage/allfoods";
    }

    @RequestMapping("/toAddAttraction")
    public String toAddAttraction(ModelMap map){
        return "arroundPage/addattraction";
    }

    @RequestMapping("/toAddFood")
    public String toAddFood(ModelMap map){
        return "arroundPage/addfood";
    }

    @RequestMapping("/Map")
    public String getMap(ModelMap map){
        return "arroundPage/map";
    }

    @RequestMapping("/Test")
    public String getTest(ModelMap map){
        System.out.println(111112222);
        return "arroundPage/test";
    }

}

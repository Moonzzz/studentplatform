package com.moon.studentplatform.web.arroundController;

import com.moon.studentplatform.dto.arround.Attractions;
import com.moon.studentplatform.dto.arround.Food;
import com.moon.studentplatform.service.arround.IArroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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

    /**
    *
    * author: Mr.Shi
    * description: 新增景点信息
    * Date:  2019/4/23
    **/
    @RequestMapping(value = "/addAttraction", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void addAttraction(HttpServletResponse resp, @RequestParam Map<String, String> objs, @RequestParam("picFile") MultipartFile picFile, ModelMap map) throws IOException {
        String name = objs.get("name");
        String position = objs.get("position");
        String phonum = objs.get("phonum");
        String description = objs.get("description");
        Attractions attractions = new Attractions(name,position,description);
        boolean flag = arroundService.addAttraction(picFile,attractions);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if (flag) {
            //return "200";
            out.flush();
            out.println("<script>" + "alert('创建社团成功，等待管理员审核~~~');");
            out.print(" window.history.go(-2);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('抱歉，创建社团失败!~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }
}

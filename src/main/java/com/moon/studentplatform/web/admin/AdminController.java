package com.moon.studentplatform.web.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moon.studentplatform.dto.society.Club;
import com.moon.studentplatform.service.society.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/14 14:23
 * @description：这个类是admin控制层
 * @modified By：
 */
@Controller
public class AdminController {
    @Autowired
    IClubService clubService;

    Club club = null;

    @RequestMapping("/toAdminPage")
    public String toAddClubPage(ModelMap map) {
        return "adminpage/mainpage";
    }

    @RequestMapping("/goToPage")
    public String goToPage(ModelMap map, @RequestParam("page") String page) {
        String toPage = "";
        switch (page) {
            case "organize":
                toPage = "adminpage/society";
                break;
            case "college":
                toPage = "adminpage/college";
                break;
        }
        return toPage;
    }

    @RequestMapping(value = "/modifyClub", method = RequestMethod.POST)
    @ResponseBody
    public String modifyClub(@RequestParam Map<String, String> objs) {
        String idStr = objs.get("id");
        int id = Integer.parseInt(idStr);
        String name = objs.get("name");
        String description = objs.get("description");
        String date = objs.get("date");
        String firstman = objs.get("firstman");
        String phonum = objs.get("phonum");
        String pass = objs.get("pass");
        club = new Club(id, name, description, date, firstman, phonum, pass);
        boolean flag = clubService.modifyClub(club);
        return flag ? "true" : "false";
    }

    @RequestMapping("/deleteClubs")
    @ResponseBody
    public String clubList(HttpServletRequest request) {
        int count = 1;
        String type = request.getParameter("type");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                System.out.println(id[i]);
                int flag = clubService.deleteClubById(id[i]);
                if (flag == 0) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            if (clubService.deleteClubById(id) == 0) count = 0;
        }
        return count > 0 ? "true" : "false";
    }

    @RequestMapping("/clubList")
    @ResponseBody
    public String clubList(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        String typeStr = objs.get("type");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        int type = Integer.parseInt(typeStr);
        List<Club> clubs = clubService.getLimitClubs((page - 1) * limit, limit, type);
        int count = clubService.getClubCount(type);
        return getString(clubs, count);
    }

    /**
     * create by: Mr Tang
     * description:<p>这个方法体的作用是将JSON数据包装成layui规定的数据形式返回给前台作数据填充
     * <p>create time: 2019/4/15 21:30
     * @return
     * 
     */
    private String getString(List<Club> clubs, int count) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String result = gson.toJson(clubs);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        return result;
    }
}
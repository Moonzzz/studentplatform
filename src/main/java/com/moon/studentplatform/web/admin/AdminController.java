package com.moon.studentplatform.web.admin;

import com.google.gson.Gson;
import com.moon.studentplatform.dto.arround.Attractions;
import com.moon.studentplatform.dto.arround.Food;
import com.moon.studentplatform.dto.official.Lecture;
import com.moon.studentplatform.dto.society.Club;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import com.moon.studentplatform.dto.society.ReplyManager;
import com.moon.studentplatform.service.arround.IArroundService;
import com.moon.studentplatform.service.official.IOfficialService;
import com.moon.studentplatform.service.society.IClubService;
import com.moon.studentplatform.service.society.IClubUserService;
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
    private final IClubService clubService;
    private final IClubUserService clubUserService;
    private final IArroundService arroundService;
    private final IOfficialService officialService;

    @Autowired
    public AdminController(IClubService clubService, IClubUserService clubUserService, IArroundService arroundService, IOfficialService officialService) {
        this.clubService = clubService;
        this.clubUserService = clubUserService;
        this.arroundService = arroundService;
        this.officialService = officialService;
    }

    Attractions attraction = null;
    Food food = null;
    Club club = null;
    List<ClubUser> clubUsers = null;
    List<ClubActivity> clubActivities = null;
    List<Attractions> attractions = null;
    List<Food> foods = null;

    @RequestMapping("/toAdminPage")
    public String toAddClubPage(ModelMap map) {
        return "adminpage/mainpage";
    }

    @RequestMapping("/goToPage")
    public String goToPage(ModelMap map, @RequestParam("page") String page) {
        String toPage = "";
        switch (page) {
            case "lecture":
                toPage = "adminpage/lecture";
                break;
            case "organize":
                toPage = "adminpage/society";
                break;
            case "college":
                toPage = "adminpage/college";
                break;
            case "organizeuser":
                toPage = "adminpage/organizeuser";
                break;
            case "collegeuser":
                toPage = "adminpage/collegeuser";
                break;
            case "article":
                toPage = "adminpage/article";
                break;
            case "attraction":
                toPage = "adminpage/attraction";
                break;
            case "food":
                toPage = "adminpage/food";
                break;
            case "comment":
                toPage = "adminPage/comment";
                break;
            case "reply":
                toPage = "adminpage/reply";
                break;
            default:
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
        club = new Club(id, name, description, date, firstman, phonum);
        boolean flag = clubService.modifyClub(club);
        return flag ? "true" : "false";
    }


    @RequestMapping(value = "/addLecture", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addLecture(@RequestParam Map<String, String> objs) {
        String title = objs.get("title");
        String typeId = objs.get("typeId");
        String context = objs.get("context");
        String dateStart = objs.get("dateStart");
        String dateEnd = objs.get("dateEnd");
        Lecture lecture = new Lecture(title, typeId, context, dateStart, dateEnd);
        int flag = officialService.addLecture(lecture);
        return flag > 0 ? "true" : "false";
    }


    @RequestMapping("/deleteLectures")
    @ResponseBody
    public String deleteLectures(HttpServletRequest request) {
        int count = 1;
        String type = request.getParameter("type");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                System.out.println(id[i]);
                int flag = officialService.deleteLectureById(id[i]);
                if (flag == 0) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            System.out.println("id是：" + id);
            if (officialService.deleteLectureById(id) == 0) count = 0;
        }
        return count > 0 ? "true" : "false";
    }

    @RequestMapping("/deleteClubs")
    @ResponseBody
    public String deleteClubs(HttpServletRequest request) {
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

    @RequestMapping("/deleteClubUsers")
    @ResponseBody
    public String deleteClubUsers(HttpServletRequest request) {
        int count = 1;
        String type = request.getParameter("type");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                System.out.println(id[i]);
                int flag = clubUserService.deleteClubUserById(id[i]);
                if (flag == 0) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            if (clubUserService.deleteClubUserById(id) == 0) count = 0;
        }
        return count > 0 ? "true" : "false";
    }

    @RequestMapping("/deleteArticles")
    @ResponseBody
    public String deleteArticles(HttpServletRequest request) {
        int count = 1;
        String type = request.getParameter("type");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                System.out.println(id[i]);
                int flag = clubUserService.deleteArticleById(id[i]);
                if (flag == 0) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            if (clubUserService.deleteArticleById(id) == 0) count = 0;
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
        System.out.println(clubs.toString());
        int count = clubService.getClubCount(type);
        return getString(clubs, count);
    }

    @RequestMapping("/replyList")
    @ResponseBody
    public String replyList(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        String table = objs.get("table");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        List<ReplyManager> replys = officialService.getLimitReplys((page - 1) * limit, limit, table);
        System.out.println(replys.toString());
        int count = officialService.getReplysCount(table);
        Gson gson = new Gson();
        String result = gson.toJson(replys);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        return result;
    }

    @RequestMapping("/modifyReply")
    @ResponseBody
    public String modifyReply(@RequestParam Map<String, String> objs) {
        String idStr = objs.get("id");
        String table = objs.get("table");
        int id = Integer.parseInt(idStr);
        String content = objs.get("content");
        boolean flag = officialService.modifyReplyContentById(id, content, table);
        return flag ? "true" : "false";
    }

    @RequestMapping("/deleteReply")
    @ResponseBody
    public String deleteReply(HttpServletRequest request) {
        int count = 1;
        int ids;
        String type = request.getParameter("type");
        String table = request.getParameter("table");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                ids = Integer.parseInt(id[i]);
                int flag = officialService.deleteReplyByID(ids, table);
                if (flag == 0) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            ids = Integer.parseInt(id);
            if (officialService.deleteReplyByID(ids, table) == 0) count = 0;
        }
        return count > 0 ? "true" : "false";
    }

    @RequestMapping(value = "/setPass", method = RequestMethod.POST)
    @ResponseBody
    public String stePass(@RequestParam Map<String, String> objs) {
        boolean flag = false;
        String id = objs.get("id");
        String pass = objs.get("pass");
        String type = objs.get("type");
        if (type.equals("userPass")) {
            flag = clubUserService.setIsPass(id, pass);
        } else if (type.equals("articlePass")) {
            flag = clubUserService.setArticleIsPass(id, pass);
        } else if (type.equals("clubPass")) {
            flag = clubService.setIsClubPass(id, pass);
        }
        return flag == true ? "true" : "false";
    }

    @RequestMapping("/allArticle")
    @ResponseBody
    public String allArticle(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        clubActivities = clubUserService.getLimitArticles((page - 1) * limit, limit);
        int count = clubUserService.getArticlesCount();
        Gson gson = new Gson();
        String result = gson.toJson(clubActivities);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        return result;
    }


    @RequestMapping("/allClubUser")
    @ResponseBody
    public String allClubUser(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        String typeStr = objs.get("type");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        int type = Integer.parseInt(typeStr);
        clubUsers = clubUserService.showAllClubUsers((page - 1) * limit, limit, type);
        Gson gson = new Gson();
        String result = gson.toJson(clubUsers);
        int count = clubUserService.getClubUserCount(type);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        return result;
    }

    @RequestMapping("/showAttractions")
    @ResponseBody
    public String showAttractions(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        attractions = arroundService.getLimitAttractions((page - 1) * limit, limit);
        Gson gson = new Gson();
        String result = gson.toJson(attractions);
        int count = arroundService.getAttractionCount();
        return getJson(result, count);
    }

    @RequestMapping("/showFoods")
    @ResponseBody
    public String showFoods(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        foods = arroundService.getLimitFoods((page - 1) * limit, limit);
        Gson gson = new Gson();
        String result = gson.toJson(foods);
        int count = arroundService.getFoodCount();
        return getJson(result, count);
    }

    /**
     * author: Mr.Shi
     * description: 删除景点信息
     * Date:  2019/4/22
     **/
    @RequestMapping("/deleteAttractions")
    @ResponseBody
    public String deleteAttractions(HttpServletRequest request) {
        int count = 1;
        int ids;
        String type = request.getParameter("type");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                System.out.println(id[i]);
                ids = Integer.parseInt(id[i]);
                if (!arroundService.isAttractionDelete(ids)) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            ids = Integer.parseInt(id);
            if (!arroundService.isAttractionDelete(ids)) count = 0;
        }
        return count > 0 ? "true" : "false";
    }

    /**
     * author: Mr.Shi
     * description: 删除美食信息
     * Date:  2019/4/22
     **/
    @RequestMapping("/deleteFoods")
    @ResponseBody
    public String deleteFoods(HttpServletRequest request) {
        int count = 1;
        int ids;
        String type = request.getParameter("type");
        if (type.equals("beth")) {
            String[] id = request.getParameterValues("id");
            for (int i = 0; i < id.length; i++) {
                System.out.println(id[i]);
                ids = Integer.parseInt(id[i]);
                if (!arroundService.isFoodDelete(ids)) count = 0;
            }
        } else if (type.equals("one")) {
            String id = request.getParameter("id");
            ids = Integer.parseInt(id);
            if (!arroundService.isFoodDelete(ids)) count = 0;
        }
        return count > 0 ? "true" : "false";
    }

    /**
     * author: Mr.Shi
     * description: 修改景点信息
     * Date:  2019/4/22
     **/
    @RequestMapping("/modifyAttraction")
    @ResponseBody
    public String modifyAttraction(@RequestParam Map<String, String> objs) {
        String idStr = objs.get("id");
        int id = Integer.parseInt(idStr);
        String name = objs.get("name");
        String description = objs.get("description");
        String position = objs.get("position");
        attraction = new Attractions(id, name, position, description);
        boolean flag = arroundService.modifyAttraction(attraction);
        return flag ? "true" : "false";
    }

    /**
     * author: Mr.Shi
     * description: 修改店铺信息
     * Date:  2019/4/22
     **/
    @RequestMapping("/modifyFood")
    @ResponseBody
    public String modifyFood(@RequestParam Map<String, String> objs) {
        String idStr = objs.get("id");
        int id = Integer.parseInt(idStr);
        String name = objs.get("name");
        String description = objs.get("description");
        String position = objs.get("position");
        food = new Food(id, name, position, description);
        boolean flag = arroundService.modifyFood(food);
        return flag ? "true" : "false";
    }

    /**
     * create by: Mr Tang
     * description:<p>这个方法体的作用是将JSON数据包装成layui规定的数据形式返回给前台作数据填充
     * <p>create time: 2019/4/15 21:30
     *
     * @return
     */
    private String getString(List<Club> clubs, int count) {
        // Gson gson = new GsonBuilder().create();
        Gson gson = new Gson();
        String result = gson.toJson(clubs);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        return result;
    }

    /**
     * author: Mr.Shi
     * description: 将数据转换为前台所需的json格式
     * Date:  2019/4/20
     **/
    private String getJson(String result, int count) {
        return "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
    }

}

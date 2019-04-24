package com.moon.studentplatform.web.official;

import com.google.gson.Gson;
import com.moon.studentplatform.dto.official.DeptType;
import com.moon.studentplatform.dto.official.Lecture;
import com.moon.studentplatform.dto.official.LectureSeach;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.service.official.IOfficialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/21 19:42
 * @description：这个类是主页controller类
 * @modified By：
 */
@Controller
public class OfficialController {
    @Autowired
    IOfficialService officialService;

    List<ClubActivity> dinfoDynamics = null;
    List<Lecture> lectures = null;
    List<DeptType> deptTypes = null;
    LectureSeach seach = null;

    @RequestMapping("/officialPage")
    public String officialPage(ModelMap map, @RequestParam("page") String page) {
        String toPage = "officialpage/infodynamics";
        switch (page) {
            case "infodynamics":
                dinfoDynamics = officialService.showAllInfoDynas();
                map.addAttribute("infos", dinfoDynamics);
                break;
            case "contests":
                dinfoDynamics = officialService.showAllContests(1);
                map.addAttribute("infos", dinfoDynamics);
                break;
            case "competitions":
                dinfoDynamics = officialService.showAllContests(3);
                map.addAttribute("infos", dinfoDynamics);
                break;
            case "lectures":
                toPage = "officialpage/lectures";
                break;
        }
        return toPage;
    }

    @RequestMapping("/lectureList")
    @ResponseBody
    public String lectureList(@RequestParam Map<String, String> objs) {
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        lectures = officialService.getLimitLectures((page - 1) * limit, limit);
        System.out.println(lectures.toString());
        int count = officialService.getAllLectureCount();
        return getString(lectures, count);
    }

    @RequestMapping("/allTypes")
    @ResponseBody
    public String allTypes() {
        deptTypes = officialService.getAllType();
        Gson gson = new Gson();
        return gson.toJson(deptTypes);
    }

    @RequestMapping("/indexLectures")
    @ResponseBody
    public String indexLectures() {
        lectures = officialService.getLimitLectures(0, 9);
        return new Gson().toJson(lectures);
    }

    @RequestMapping("/searchLecture")
    @ResponseBody
    public String searchLecture(@RequestParam Map<String, String> objs) {
        String keyword = objs.get("keyword");
        String typeIdStr = objs.get("type");
        String pageStr = objs.get("page");
        String limitStr = objs.get("limit");

        Integer typeId = Integer.parseInt(typeIdStr);
        if (keyword.equals("")) keyword = null;
        if (typeId == -1) typeId = null;
        int page = Integer.parseInt(pageStr);
        int limit = Integer.parseInt(limitStr);
        page = (page - 1) * limit;
        seach = new LectureSeach(page, limit, typeId, keyword);
        lectures = officialService.shearchLectures(seach);
        int count = officialService.getSearchCount(seach);
        return getString(lectures, count);
    }

    private String getString(List<Lecture> clubs, int count) {
        Gson gson = new Gson();
        String result = gson.toJson(clubs);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        System.out.println(result);
        return result;
    }
}

package com.moon.studentplatform.web.society;

import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.dto.society.Club;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import com.moon.studentplatform.service.society.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/3 9:28
 * @description：这个类是社团组织controller类，处理转接社团组织页面的请求
 * @modified By：
 */
@Controller
public class SocietyController {
    @Autowired
    IClubService clubService;

    ClubActivity activity = null;
    List<Club> clubs = null;
    List<ClubActivity> clubActivities = null;
    Club cDetail = null;
    ClubUser member = null;

    @RequestMapping("/allSocietys")
    public String allSocietys(ModelMap map) {
        clubs = clubService.showAllClubs();
        if (clubs.size() > 0) {
            map.addAttribute("clubs", clubs);
        }
        return "societyPage/allsocietys";
    }

    @RequestMapping("/allStuOrganize")
    public String allStuOrganize(ModelMap map) {
        clubs = clubService.showAllStuOrganizes();
        if (clubs.size() > 0) {
            map.addAttribute("clubs", clubs);
        }
        return "societyPage/stuorganize";
    }

    @RequestMapping("/allColleges")
    public String allColleges(ModelMap map) {
        clubs = clubService.showAllColleges();
        if (clubs.size() > 0) {
            map.addAttribute("clubs", clubs);
        }
        return "societyPage/allcolleges";
    }


    @RequestMapping("/toAddClubPage")
    public String toAddClubPage(ModelMap map) {
        return "societyPage/addclub";
    }

    @RequestMapping("/toJoinClubPage")
    public String toJoinClubPage(ModelMap map) {
        return "societyPage/joinclub";
    }

    @RequestMapping("/toActDetailPage")
    public String toActDetailPage(ModelMap map, @RequestParam("id") int id) {
        System.out.println("id是---：" + id);
        activity = clubService.showClubActDetailById(id);
        map.addAttribute("activity", activity);
        return "societyPage/activitydetail";
    }

    @RequestMapping(value = "/toClubDetailPage", method = RequestMethod.GET, produces = "application/json")
    public String toClubDetailPage(HttpServletRequest request, @RequestParam("id") int clubId, ModelMap map) {
        request.getSession().setAttribute("clubId", clubId);
        clubActivities = clubService.showClubActsByCId(clubId);
        cDetail = clubService.showClubDetailById(clubId);
        map.addAttribute("cDetail", cDetail);
        if (clubActivities.size() > 0) {
            map.addAttribute("clubActs", clubActivities);
        }
        map.addAttribute("clubId", clubId);
        return "societyPage/clubdetail";
    }

    @RequestMapping(value = "/addClub", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void addClub(HttpServletResponse resp, @RequestParam Map<String, String> objs, @RequestParam("picFile") MultipartFile picFile, ModelMap map) throws IOException {
        String name = objs.get("name");
        String datepublished = objs.get("datepublished");
        String firstman = objs.get("firstman");
        String phonum = objs.get("phonum");
        String description = objs.get("description");
        Club club = new Club(name, datepublished, firstman, phonum, description);
        boolean flag = clubService.addClub(picFile, club);
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


    @RequestMapping(value = "/joinClub", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void joinClub(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map<String, String> objs, ModelMap map) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        Long userId = user.getId();
        int user_Id = new Long(userId).intValue();
        int club_Id = (int) request.getSession().getAttribute("clubId");
        String reason = objs.get("reason");
        String experience = objs.get("experience");
        String joindate = objs.get("joindate");

        member = new ClubUser(user_Id, club_Id, reason, experience, joindate, "no");
        boolean flag = clubService.joinClub(member);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        if (flag) {
            //return "200";
            out.flush();
            out.println("<script>" + "alert('加入社团成功，等待管理员审核~~~');");
            out.print(" window.history.go(-2);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('抱歉，加入社团失败!~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }
}

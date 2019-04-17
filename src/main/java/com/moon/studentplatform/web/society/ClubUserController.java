package com.moon.studentplatform.web.society;

import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.service.society.IClubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/16 16:08
 * @description：这个类是社区会员控制层
 * @modified By：
 */
@Controller
public class ClubUserController {
    @Autowired
    IClubUserService clubUserService;

    @RequestMapping("/toEditArticlePage")
    public String toEditArticlePage() {
        return "societyPage/editarticle";
    }

    @RequestMapping(value = "/isUserPass", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String isUserPass(HttpSession session, @RequestParam("type") String type) {
        int count = 0;
        User user = (User) session.getAttribute("user");
        int clubId = (int) session.getAttribute("clubId");
        int userId = new Long(user.getId()).intValue();
        count = clubUserService.isUserPass(clubId, userId);
        if (type.equals("isPassEdit")) {
            return count > 0 ? "true" : "false";
        } else if (type.equals("isPassJoin")) {
            if (count == 1) {
                return "3";
            } else {
                int count1 = clubUserService.isUserApply(clubId, userId);
                return count1 > 0 ? "2" : "1";
            }
        }
        return "";
    }


    @RequestMapping(value = "/editArticle", method = RequestMethod.POST, produces = "application/json")
    public void editArticle(HttpSession session, HttpServletResponse resp, @RequestParam Map<String, String> objs, @RequestParam("picFile") MultipartFile picFile) throws IOException {
        User user = (User) session.getAttribute("user");
        int clubId = (int) session.getAttribute("clubId");
        int userId = new Long(user.getId()).intValue();
        //int clubId = Integer.parseInt(clubIdStr);
        String title = objs.get("title");
        String description = objs.get("description");
        String date = objs.get("date");
        String text = objs.get("text");
        String author = user.getUsername();
        ClubActivity activity = new ClubActivity(userId, clubId, title, author, date, text, description, "false");
        boolean flag = clubUserService.editArticle(picFile, activity);
        //System.out.println("当前clubID:" + clubId);
        //boolean flag = true;
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if (flag) {
            out.flush();
            out.println("<script>" + "alert('上传文章成功，等待管理员审核~~~');");
            out.print("window.history.go(-2);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('抱歉，上传文章失败!~~~');");
            out.print("window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }
}

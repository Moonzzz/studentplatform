package com.moon.studentplatform.web.society;

import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.service.society.IClubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String toActDetailPage(ModelMap map, @RequestParam("clubId") int clubId) {
        map.addAttribute("clubId", clubId);
        return "societyPage/editarticle";
    }

    @RequestMapping(value = "/editArticle", method = RequestMethod.POST, produces = "application/json")
    public void editArticle(HttpServletResponse resp, HttpServletRequest request, @RequestParam Map<String, String> objs, @RequestParam("picFile") MultipartFile picFile) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String clubIdStr = objs.get("clubId");

        int userId = new Long(user.getId()).intValue();
        int clubId = Integer.parseInt(clubIdStr);
        String title = objs.get("title");
        String description = objs.get("description");
        String date = objs.get("date");
        String text = objs.get("text");
        String author = user.getUsername();
        ClubActivity activity = new ClubActivity(userId, clubId, title, author, date, text, description, "false");
        boolean flag = clubUserService.editArticle(picFile, activity);
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

package com.moon.studentplatform.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moon.studentplatform.dto.Found;
import com.moon.studentplatform.service.IlostafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@Controller
public class LafController {
    @Autowired
    private IlostafService ilostafService;

    @RequestMapping(value = "/toshow")
    @ResponseBody
    public String show(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam(value = "name", defaultValue = "") String name) {
        List<Found> founds;
        if (name.equals("")) {
            founds = ilostafService.toshow((page - 1) * limit, limit);
        } else {
            founds = ilostafService.select(name);
        }
        int count = ilostafService.count();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String result = gson.toJson(founds);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        return result;
    }

    @RequestMapping(value = "/todelete")
    @ResponseBody
    public int todelete(String id) {
        String[] ids = id.split(",");
        int i = 0;
        for (String id1 : ids) {
            i = ilostafService.delete(Integer.parseInt(id1));
        }
        return i;
    }

    @RequestMapping(value = "/toadd")
    public void toadd(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        System.out.println(title);
        String type = request.getParameter("type");
        String adress = request.getParameter("adress");
        String description = request.getParameter("description");
        String number = request.getParameter("number");
        String publishtime = request.getParameter("publishtime");
        Date date = Date.valueOf(publishtime);
        Found found = new Found(title, type, adress, description, number, date);
        ilostafService.add(found);
        try {
            response.sendRedirect("pages/found/landf.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/toaddfound")
    public String add() {
        return "foundpage/addfound";
    }

    @RequestMapping("/toshowfound")
    public String show(Model map, HttpServletRequest request) {
        String type = request.getParameter("type");
        List<Found> founds;
        if ("".equals(type) || type == null) {
            founds = ilostafService.show();
        } else {
            founds = ilostafService.select(type);
        }

        if (founds.size() > 0) {
            map.addAttribute("founds", founds);
        }
System.out.println(founds);
        return "foundpage/showfound";
    }


    @RequestMapping("/toselect")
    @ResponseBody
    public String select( @RequestParam("name") String name) {
        String result = "";
        int count = 0;
        List<Found> founds;
        if (name.equals("")) {
            founds = ilostafService.show();
        } else {
            founds = ilostafService.select(name);
        }
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        result = gson.toJson(founds);
        result = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + result + "}";
        System.out.println(result);
        return result;
    }

    @RequestMapping("/add")
    public void addfound(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String title = request.getParameter("title");
        System.out.println(title);
        String type = request.getParameter("type");
        String adress = request.getParameter("adress");
        String description = request.getParameter("description");
        String number = request.getParameter("number");
        String publishtime = request.getParameter("publishtime");
        Date date = Date.valueOf(publishtime);
        Found found = new Found(title, type, adress, description, number, date);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if (ilostafService.add(found)) {
            //return "200";
            out.flush();
            out.println("<script>" + "alert('添加成功~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('抱歉，失败!~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }


}

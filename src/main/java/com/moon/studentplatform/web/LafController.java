package com.moon.studentplatform.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moon.studentplatform.dto.Found;
import com.moon.studentplatform.service.IlostafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
public class LafController {
    @Autowired
    private IlostafService ilostafService;

    @RequestMapping(value = "/toshow")
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
        Date date = java.sql.Date.valueOf(publishtime);
        Found found = new Found(title, type, adress, description, number, date);
        ilostafService.add(found);
        try {
            response.sendRedirect("pages/found/landf.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

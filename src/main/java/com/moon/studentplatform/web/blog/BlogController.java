package com.moon.studentplatform.web.blog;

import com.google.gson.Gson;
import com.moon.studentplatform.domain.Blog;
import com.moon.studentplatform.domain.Comment;
import com.moon.studentplatform.domain.Reply;
import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.service.IBlogService;
import com.moon.studentplatform.service.IUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Moon
 */

@Controller
@RequestMapping("/blog")
@Log4j2
public class BlogController {

    private final IBlogService blogService;
    private final IUploadService uploadBlogImgServiceImpl;
    private final static Long USER_ID = 20L;

    @Autowired
    public BlogController(IBlogService blogService, IUploadService uploadBlogImgServiceImpl) {
        this.blogService = blogService;
        this.uploadBlogImgServiceImpl = uploadBlogImgServiceImpl;
    }

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.put("list", blogService.getAll(1, 10));
        return "/blog/index";
    }

    @RequestMapping("/searchByTitle")
    public String search(ModelMap map, String title) {
        map.put("list", blogService.getAllByTitle(title, 1, 10));
        return "/blog/index";
    }

    @RequestMapping("/show/{blogId}")
    public String index(ModelMap map, @PathVariable Long blogId) {
        map.put("blog", blogService.getById(blogId));
        return "blog/show";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "blog/edit";
    }

    @RequestMapping(value = "addBlog", method = RequestMethod.POST)
    @ResponseBody
    public String addBlog(Integer categories, Blog blog) {
        blog.setUserId(USER_ID);
        blog.setDateLastModified(new Timestamp(System.currentTimeMillis()));
        Set<Integer> set = new HashSet<>();
        set.add(categories);
        blog.setCategories(set);
        return blogService.addBlog(blog).toString();
    }

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(Comment comment) {
        comment.setUserId(USER_ID);
        return blogService.addComment(comment).toString();
    }

    @RequestMapping(value = "addReply", method = RequestMethod.POST)
    @ResponseBody
    public String addReply(Reply reply, String replyToUsername) {
        reply.setUserId(USER_ID);
        User replyToUser = new User();
        replyToUser.setUsername(replyToUsername);
        reply.setReplyToUser(replyToUser);
        return blogService.addReply(reply).toString();
    }

    @RequestMapping(value = "updateLike", method = RequestMethod.POST)
    @ResponseBody
    public String updateLike(@RequestParam Map argMap) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(argMap));
        if (argMap.size() > 0) {
            return blogService.updateLikeNum(argMap).toString();
        }
        return "false";
    }


    @RequestMapping(value = "uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(String guid, HttpServletRequest request, @RequestParam("editormd-image-file") MultipartFile file) {
        //log.error(guid);
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return uploadBlogImgServiceImpl.uploadImg(guid, path, file);
    }

}

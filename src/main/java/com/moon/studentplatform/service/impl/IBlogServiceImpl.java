package com.moon.studentplatform.service.impl;

import com.google.common.collect.ImmutableMap;
import com.moon.studentplatform.domain.Blog;
import com.moon.studentplatform.domain.Comment;
import com.moon.studentplatform.domain.Reply;
import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.mapper.BlogMapper;
import com.moon.studentplatform.mapper.UserMapper;
import com.moon.studentplatform.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Moon
 */
@Service
public class IBlogServiceImpl implements IBlogService {
    private final BlogMapper blogMapper;
    private final UserMapper userMapper;
    private final static Pattern PATTERN = Pattern.compile("!\\[]\\(\\S*?\\.(png|jpeg|jpg)\\)");

    @Autowired
    public IBlogServiceImpl(BlogMapper blogMapper, UserMapper userMapper) {
        this.blogMapper = blogMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<Blog> getAll(int pageNo, int pageSize) {
        ImmutableMap<String, Integer> map = ImmutableMap
                .of("offset", (pageNo - 1) * pageSize, "pageSize", pageSize);
        return blogMapper.selectByDate(map);
    }

    @Override
    public List<Blog> getAllByTitle(String title, int pageNo, int pageSize) {
        ImmutableMap<String, Object> map = ImmutableMap
                .of("title", "%" + title + "%", "offset", (pageNo - 1) * pageSize, "pageSize", pageSize);
        return blogMapper.selectByTitle(map);
    }

    @Override
    public Blog getById(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addBlog(Blog blog) {
        Matcher matcher = PATTERN.matcher(blog.getContent());
        String images = "";
        for (int i = 0; i < 3; i++) {
            if (!matcher.find())
                break;
            String str = matcher.group();
            int startIndex = str.indexOf("blog/") + 5;
            images += str.substring(startIndex, str.length() - 1) + ",";
        }
        blog.setImages(images);
        int count = blogMapper.insertBlog(blog);
        blogMapper.insertBlogAndType(blog);
        return count > 0;
    }

    @Override
    public Boolean addComment(Comment comment) {
        comment.setDatePublished(new Timestamp(System.currentTimeMillis()));
        return blogMapper.insertComment(comment) > 0;
    }

    @Override
    public Boolean addReply(Reply reply) {
        User replyToUser = userMapper.findByUsername(reply.getReplyToUser().getUsername());
        reply.setReplyToUser(replyToUser);
        reply.setDatePublished(new Timestamp(System.currentTimeMillis()));
        return blogMapper.insertReply(reply) > 0;
    }

    @Override
    public Boolean updateLikeNum(Map argMap) {
        return blogMapper.updateCommentLike(argMap) > 0;
    }
}

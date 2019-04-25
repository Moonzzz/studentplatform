package com.moon.studentplatform.service;

import com.moon.studentplatform.domain.Blog;
import com.moon.studentplatform.domain.Comment;
import com.moon.studentplatform.domain.Reply;

import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */
public interface IBlogService {
    /**
     * 分页查询Blog
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @return blogList
     */
    List<Blog> getAll(int pageNo, int pageSize);

    /**
     * 标题查询Blog
     *
     * @param title   title
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @return blogList
     */
    List<Blog> getAllByTitle(String title, int pageNo, int pageSize);

    /**
     * 根据Id返回blog
     *
     * @param id blogID
     * @return Blog
     */
    Blog getById(Long id);

    /**
     * 返回true or false
     *
     * @param blog blog
     * @return boolean
     */
    Boolean addBlog(Blog blog);

    /**
     * 返回true or false
     *
     * @param comment comment
     * @return boolean
     */
    Boolean addComment(Comment comment);

    Boolean addReply(Reply reply);

    Boolean updateLikeNum(Map argMap);
}

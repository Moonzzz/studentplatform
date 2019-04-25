package com.moon.studentplatform.mapper;

import com.moon.studentplatform.domain.Blog;
import com.moon.studentplatform.domain.Comment;
import com.moon.studentplatform.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */
@Repository
@Mapper
public interface BlogMapper {
    /**
     * 返回根据最后修改时间排序的Blog列表
     *
     * @param argMap pageNo
     *               pageSize
     * @return BlogList
     */
    List<Blog> selectByDate(Map argMap);

    /**
     * 返回根据title的Blog列表
     *
     * @param argMap pageNo
     *               pageSize
     *               title
     * @return BlogList
     */
    List<Blog> selectByTitle(Map argMap);

    /**
     * 根据id返回blog
     *
     * @param id blogID
     * @return Blog
     */
    Blog selectById(Long id);

    /**
     * 插入blog返回int
     *
     * @param blog blog
     * @return int
     */
    int insertBlog(Blog blog);

    /**
     * 插入postAndType返回int
     *开启了多语句，没法再select内获取自增主键
     * @param blog blog
     * @return int[]
     */
    int insertBlogAndType(Blog blog);

    /**
     * 插入comment
     * @param comment comment
     * @return int
     */
    int insertComment(Comment comment);

    /**
     * 插入reply
     * @param reply reply
     * @return int
     */
    int insertReply(Reply reply);

    /**
     * 根据Id 和 likeNums 是否大于0进行更新 +1 || -1
     * @param argMap key commentId
     *               value likeNum
     * @return 返回受影响行数
     */
    int updateCommentLike(@Param("argMap") Map argMap);

}

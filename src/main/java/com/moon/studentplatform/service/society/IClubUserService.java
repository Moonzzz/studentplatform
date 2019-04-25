package com.moon.studentplatform.service.society;

import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/16 16:00
 * @description：这个类是社区会员service接口
 * @modified By：
 */
public interface IClubUserService {
    List<ClubUser> showAllClubUsers(int offset, int limit, int type);

    int getClubUserCount(int type);

    boolean setIsPass(String id, String pass);

    boolean setArticleIsPass(String id, String pass);

    int deleteClubUserById(String id);

    int deleteArticleById(String id);

    boolean editArticle(MultipartFile picFile, ClubActivity activity);

    int isUserPass(int clubId, int userId);

    int isUserApply(int clubId, int userId);

    List<ClubActivity> getLimitArticles(int offset, int limit);

    int getArticlesCount();


}

package com.moon.studentplatform.service.society;

import com.moon.studentplatform.dto.society.Club;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/6 19:21
 * @description：这个类是
 * @modified By：
 */
public interface IClubService {

    boolean addClub(MultipartFile picFile, Club club);

    int deleteClubById(String id);

    boolean modifyClub(Club club);

    boolean joinClub(ClubUser member);

    List<Club> showAllClubs();

    List<Club> showAllStuOrganizes();

    List<Club> getLimitClubs(int offset, int limit, int type);

    int getClubCount(int type);

    List<Club> showAllColleges();

    Club showClubDetailById(int id);

    List<ClubActivity> showClubActsByCId(int clubId);

    ClubActivity showClubActDetailById(int id);

    boolean setIsClubPass(String id, String pass);


}

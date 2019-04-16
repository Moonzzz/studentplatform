package com.moon.studentplatform.service.society;

import com.moon.studentplatform.dao.society.IFileDao;
import com.moon.studentplatform.dto.society.Club;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubMember;
import com.moon.studentplatform.mapper.society.IClubDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/6 19:22
 * @description：这个类是
 * @modified By：
 */
@Service("clubService")
public class ClubServiceImpl implements IClubService {
    @Autowired
    IClubDao clubDao;

    @Autowired
    IFileDao fileDao;

    @Override
    public boolean addClub(MultipartFile picFile, Club club) {
        System.out.println("pic size:  " + picFile.getSize());
        long pSize = picFile.getSize();
        int randomNum = fileDao.getSecondTimestamp(new Date());
        String pSuffix = fileDao.getSuffix(picFile.getOriginalFilename());
        String pRealPath = "D:/AIDEAworkspace/studentplatform-master/src/main/resources/static/society_files/" + randomNum + pSuffix;
        String icon = randomNum + pSuffix;
        System.out.println("icon:  " + icon);
        club.setIcon(icon);
        if (clubDao.addClub(club)) {
            boolean flag = fileDao.upLoadPhoto(picFile, pRealPath);
            if (flag) return true;
        }
        return false;
    }

    @Override
    public int deleteClubById(String id) {
        return clubDao.deleteClubById(id);
    }

    @Override
    public boolean modifyClub(Club club) {
        return clubDao.modifyClub(club);
    }

    @Override
    public boolean joinClub(ClubMember member) {
        return clubDao.joinClub(member);
    }

    @Override
    public List<Club> showAllClubs() {
        return clubDao.showAllClubs();
    }

    @Override
    public List<Club> showAllStuOrganizes() {
        return clubDao.showAllStuOrganizes();
    }

    @Override
    public List<Club> getLimitClubs(int offset, int limit, int type) {
        return clubDao.getLimitClubs(offset, limit, type);
    }

    @Override
    public int getClubCount(int type) {
        return clubDao.getClubCount(type);
    }

    @Override
    public List<Club> showAllColleges() {
        return clubDao.showAllColleges();
    }

    @Override
    public Club showClubDetailById(int id) {
        return clubDao.showClubDetailById(id);
    }

    @Override
    public List<ClubActivity> showClubActsByCId(int clubId) {
        return clubDao.showClubActsByCId(clubId);
    }

    @Override
    public ClubActivity showClubActDetailById(int id) {
        return clubDao.showClubActDetailById(id);
    }
}

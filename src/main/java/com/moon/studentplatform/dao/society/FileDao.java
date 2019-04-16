package com.moon.studentplatform.dao.society;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/6 19:23
 * @description：这个类是
 * @modified By：
 */
@Repository("fileDao")
public class FileDao implements IFileDao {
    @Override
    public boolean isTrueWordRange(MultipartFile file) {
        return false;
    }

    @Override
    public boolean isTrueWordSuffix(String originalName) {
        return false;
    }

    @Override
    public boolean isTruPicRange(MultipartFile file) {
        if (file.getSize() < 2097152) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTruePicSuffix(String originalName) {
        String suffix = originalName.substring(originalName.lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("png")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean upLoadPhoto(MultipartFile file, String realPath) {
        if (isTruPicRange(file)) {
            if (isTruePicSuffix(file.getOriginalFilename())) {
                File newFile = new File(realPath);
                try {
                    file.transferTo(newFile);
                    System.out.println("上传图片成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                System.out.println("上传失败！图片后缀不正确");
            }
        } else {
            System.out.println("上传失败！图片超出了规定范围大小");
        }
        return false;
    }

    @Override
    public boolean upLoadWord(MultipartFile file, String realPath) {
        return false;
    }

    @Override
    public boolean deleteFileMethod(String fileName) {
        return false;
    }


    @Override
    public boolean deleteFile(String fileName) {
        return false;
    }

    /**
     * create by: Mr Tang
     * description: 这个函数的功能是获取当前时间点与1970年1月1日的间隔秒数
     * create time: 2019/4/6 19:24
     * @return
     *
     */
    @Override
    public int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    @Override
    public String getSuffix(String originalName) {
        return "." + originalName.substring(originalName.lastIndexOf(".") + 1);
    }

}

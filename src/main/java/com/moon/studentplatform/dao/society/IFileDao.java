package com.moon.studentplatform.dao.society;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/6 17:48
 * @description：这个类是文件操作接口
 * @modified By：
 */
public interface IFileDao {
    boolean isTrueWordRange(MultipartFile file);
    boolean isTrueWordSuffix(String originalName);
    boolean isTruPicRange(MultipartFile file);
    boolean isTruePicSuffix(String originalName);
    boolean upLoadPhoto(MultipartFile file, String realPath);
    boolean upLoadWord(MultipartFile file, String realPath);
    boolean deleteFileMethod(String fileName);
    boolean deleteFile(String fileName);
    int getSecondTimestamp(Date date);
    String getSuffix(String originalName);
}

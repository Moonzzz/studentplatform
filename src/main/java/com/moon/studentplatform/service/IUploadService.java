package com.moon.studentplatform.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Moon
 */
public interface IUploadService {
    String uploadImg(String guid, String contextPath, MultipartFile file);
}

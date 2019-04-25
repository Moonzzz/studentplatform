package com.moon.studentplatform.service.impl;

import com.google.gson.Gson;
import com.moon.studentplatform.service.IUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Moon
 */
@Log4j2
@Service
public class UploadBlogImgServiceImpl implements IUploadService {
    private static final String FILE_PATH = "E:/temp/blog/";
    private static String DATE = new SimpleDateFormat("yyyyMMdd/").format(new Date());

    @Override
    public String uploadImg(String guid, String servletPath, MultipartFile file) {
        Objects.requireNonNull(file);
        Map<String, Object> resultMap = new HashMap<>(3);
        if (file.isEmpty() | Objects.equals(file.getOriginalFilename(), "")) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败，请选择文件!");
        } else {
            String filePath = FILE_PATH + DATE;
            File dir = new File(filePath);
            if (!dir.exists())
                dir.mkdirs();
            File destFile = new File(filePath + guid + "." + Objects.requireNonNull(file.getContentType()).split("/")[1]);
            try {
                file.transferTo(destFile);
                resultMap.put("success", 1);
                resultMap.put("message", "success");
                resultMap.put("url", servletPath + "/blog/" + DATE + destFile.getName());
            } catch (IOException e) {
                resultMap.put("success", 0);
                resultMap.put("message", "上传失败，请选择文件!");
                log.error(e.toString(), e);
            }
        }

        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }
}

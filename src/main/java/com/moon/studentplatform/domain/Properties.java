package com.moon.studentplatform.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Moon
 */
@Component
@Data
public class Properties {
    @Value("web.upload_path")
    private String uploadPath;

}

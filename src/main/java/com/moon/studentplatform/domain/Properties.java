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
    @Value("name")
    private String name;

    @Value("title")
    private String title;
}

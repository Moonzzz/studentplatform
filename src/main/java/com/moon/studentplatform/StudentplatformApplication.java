package com.moon.studentplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Moon
 */
@SpringBootApplication
@MapperScan("com.moon.studentplatform.mapper")
public class StudentplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentplatformApplication.class, args);
    }

}

package com.me.courses_selection;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.me.courses_selection.mapper")
public class CoursesSelectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursesSelectionApplication.class, args);
    }

}

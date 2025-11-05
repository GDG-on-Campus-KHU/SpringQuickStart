package com.example.boardweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BoardWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardWebApplication.class, args);
    }

}

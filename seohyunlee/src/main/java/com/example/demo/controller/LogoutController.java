package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("로그아웃 처리");

        // 세션 종료
        session.invalidate();

        // 로그인 화면으로 이동
        return "login"; // login.html 또는 login.jsp
    }
}

package com.springbook.view.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

    @GetMapping("/logout.do")
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) {
        System.out.println("로그아웃 처리");

        // 1. 브라우저와 연결된 세션 객체를 강제 종료한다.
        HttpSession session = request.getSession();
        session.invalidate();

        // 2. 세션 종료후, 메인 화면으로 이동한다.
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:login.do");
        return mav;
    }
}
package com.example.demo.controller;

import com.example.demo.user.UserDAO;
import com.example.demo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    // 로그인 폼을 보여주기 위한 GET 핸들러 추가
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // templates/login.html 렌더링
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            Model model) {

        System.out.println("로그인 처리");

        // 1. 사용자 데이터 저장
        UserVO vo = new UserVO();
        vo.setId(id);
        vo.setPassword(password);

        // 2. DB 연동
        UserVO user = userDAO.getUser(vo);

        // 3. 화면 이동
        if (user != null) {
            return "redirect:/getBoardList";  // 로그인 성공
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";  // login.html 또는 login.jsp 렌더링
        }
    }
}

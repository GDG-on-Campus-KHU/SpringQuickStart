package com.springbook.view.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {
    @GetMapping("/login.do")
    public String loginView(UserVO vo) {
        System.out.println("로그인 화면으로 이동");
        vo.setId("test");
        vo.setPassword("test123");
        return "login.jsp";
    }

    @PostMapping("/login.do")
    public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
        if (vo.getId() == null || vo.getId().equals("")) {
            throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
        }

        UserVO user = userDAO.getUser(vo);

        if (user != null) {
            session.setAttribute("userName", user.getName());
            return "getBoardList.do";
        } else {
            return "login.jsp";
        }
    }
}

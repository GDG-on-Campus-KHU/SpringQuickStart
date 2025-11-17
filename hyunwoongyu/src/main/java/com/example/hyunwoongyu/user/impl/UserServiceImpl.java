package com.example.hyunwoongyu.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hyunwoongyu.user.UserService;
import com.example.hyunwoongyu.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    // Setter 인젝션 메소드 (선택적으로 사용)
    public void setUserDAO (UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserVO getUser (UserVO vo) {
        return userDAO.getUser(vo);
    }
}
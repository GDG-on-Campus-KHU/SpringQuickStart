package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.board.BoardVO;
import com.example.demo.board.BoardDAO;

@Controller
public class GetBoardListController {

    @Autowired
    private BoardDAO boardDAO;

    @GetMapping("/getBoardList")
    public String getBoardList(Model model) {
        System.out.println("글 목록 검색 처리");

        // DB 연동
        BoardVO vo = new BoardVO();
        List<BoardVO> boardList = boardDAO.getBoardList(vo);

        // Model에 데이터 추가
        model.addAttribute("boardList", boardList);

        // View 이름 반환 (getBoardList.jsp 또는 getBoardList.html)
        return "getBoardList";
    }
}

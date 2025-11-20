package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.board.BoardVO;
import com.example.demo.board.BoardDAO;

@Controller
public class GetBoardController {

    @Autowired
    private BoardDAO boardDAO;

    @GetMapping("/getBoard")
    public String getBoard(
            @RequestParam("seq") int seq,
            Model model) {

        System.out.println("글 상세 조회 처리");

        // DB 연동
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        BoardVO board = boardDAO.getBoard(vo);

        // Model에 데이터 설정
        model.addAttribute("board", board);

        // JSP or Thymeleaf 뷰 이름
        return "getBoard";
    }
}

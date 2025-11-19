package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.board.BoardVO;
import com.example.demo.board.BoardDAO;

@Controller
public class UpdateBoardController {

    @Autowired
    private BoardDAO boardDAO;

    @PostMapping("/updateBoard")
    public String updateBoard(
            @RequestParam("seq") int seq,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        System.out.println("글 수정 처리");

        // 1. 데이터 저장
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        vo.setTitle(title);
        vo.setContent(content);

        // 2. DB 처리
        boardDAO.updateBoard(vo);

        // 3. 수정 후 글 목록으로 리다이렉트
        return "redirect:/getBoardList";
    }
}

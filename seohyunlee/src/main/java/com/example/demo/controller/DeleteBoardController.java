package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.board.BoardVO;
import com.example.demo.board.BoardDAO;

@Controller
public class DeleteBoardController {

    @Autowired
    private BoardDAO boardDAO;

    @PostMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("seq") int seq) {

        System.out.println("글 삭제 처리");

        // 1. 데이터 저장
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);

        // 2. DB 처리
        boardDAO.deleteBoard(vo);

        // 3. 삭제 후 글 목록으로 리다이렉트
        return "redirect:/getBoardList";
    }
}

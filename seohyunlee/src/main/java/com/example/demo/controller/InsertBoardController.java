package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.board.BoardVO;
import com.example.demo.board.BoardDAO;

@Controller
public class InsertBoardController {

    @Autowired
    private BoardDAO boardDAO;

    // GET 요청: 글 등록 폼 보여주기
    @GetMapping("/insertBoard")
    public String showInsertForm() {
        return "insertBoard"; // resources/templates/insertBoard.html
    }

    // POST 요청: 글 등록 처리
    @PostMapping("/insertBoard")
    public String insertBoard(
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content) {

        System.out.println("글 등록 처리");

        // 1. 데이터 저장
        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setWriter(writer);
        vo.setContent(content);

        // 2. DB 처리
        boardDAO.insertBoard(vo);

        // 3. 등록 후 목록으로 이동
        return "redirect:/getBoardList";
    }
}

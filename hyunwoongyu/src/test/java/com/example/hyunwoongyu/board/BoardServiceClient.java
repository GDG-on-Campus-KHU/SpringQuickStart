package com.example.hyunwoongyu.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
    public static void main(String[] args) {
        AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
        
        BoardService boardService = (BoardService) container.getBean("boardService");
        
        // 새 글 등록
        BoardVO vo = new BoardVO();
        vo.setTitle("새 글 제목");
        vo.setWriter("작성자");
        vo.setContent("새 글 내용입니다.");
        boardService.insertBoard(vo);
        
        // 글 목록 조회
        List<BoardVO> boardList = boardService.getBoardList(new BoardVO());
        for (BoardVO board : boardList) {
            System.out.println(board.toString());
        }
        
        container.close();
    }
}

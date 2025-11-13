package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    public void insertBoard(BoardVO board) {
        boardDAO.insertBoard(board);
    }

    public void updateBoard(BoardVO board) {
        boardDAO.updateBoard(board);
    }

    public void deleteBoard(BoardVO board) {
        boardDAO.deleteBoard(board);
    }

    public BoardVO getBoard(BoardVO board) {
        return boardDAO.getBoard(board);
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        return boardDAO.getBoardList(vo);
    }
}

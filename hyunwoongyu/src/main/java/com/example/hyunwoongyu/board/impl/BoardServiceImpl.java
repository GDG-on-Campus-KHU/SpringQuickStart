package com.example.hyunwoongyu.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hyunwoongyu.board.BoardService;
import com.example.hyunwoongyu.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAOSpring boardDAOSpring;

    @Override
    public void insertBoard(BoardVO vo) {
        boardDAOSpring.insertBoard(vo);
    }

    @Override
    public void updateBoard(BoardVO vo) {
        boardDAOSpring.updateBoard(vo);
    }

    @Override
    public void deleteBoard(BoardVO vo) {
        boardDAOSpring.deleteBoard(vo);
    }

    @Override
    public BoardVO getBoard(BoardVO vo) {
        return boardDAOSpring.getBoard(vo);
    }

    @Override
    public List<BoardVO> getBoardList(BoardVO vo) {
        return boardDAOSpring.getBoardList(vo);
    }
}

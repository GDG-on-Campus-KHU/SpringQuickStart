package com.example.demo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public void insertBoard(BoardVO vo) { boardDAO.insertBoard(vo); }

    @Override
    public void updateBoard(BoardVO vo) { boardDAO.updateBoard(vo); }

    @Override
    public void deleteBoard(BoardVO vo) { boardDAO.deleteBoard(vo); }

    @Override
    public BoardVO getBoard(BoardVO vo) { return boardDAO.getBoard(vo); }

    @Override
    public List<BoardVO> getBoardList(BoardVO vo) { return boardDAO.getBoardList(vo); }

    @Override
    public List<BoardVO> searchBoard(String keyword) { return boardDAO.searchBoard(keyword); }

    @Override
    public List<BoardVO> searchBoardByTitle(String keyword) { return boardDAO.searchBoardByTitle(keyword); }

    @Override
    public List<BoardVO> searchBoardByContent(String keyword) { return boardDAO.searchBoardByContent(keyword); }
}

package com.example.demo.board;

import java.util.List;

public interface BoardService {
    void insertBoard(BoardVO vo);
    void updateBoard(BoardVO vo);
    void deleteBoard(BoardVO vo);
    BoardVO getBoard(BoardVO vo);
    List<BoardVO> getBoardList(BoardVO vo);
    List<BoardVO> searchBoard(String keyword);
    List<BoardVO> searchBoardByTitle(String keyword);
    List<BoardVO> searchBoardByContent(String keyword);
}

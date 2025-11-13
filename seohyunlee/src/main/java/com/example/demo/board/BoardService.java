package com.example.demo.board;

public interface BoardService {
    void insertBoard(BoardVO vo);

    void updateBoard(BoardVO vo);

    void deleteBoard(BoardVO vo);

    BoardVO getBoard(BoardVO vo);

    java.util.List<BoardVO> getBoardList(BoardVO vo);
}

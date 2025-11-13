package Board.service;

import Board.domain.Board;
import java.util.List;

public interface BoardServiceImpl {

    // 등록
    void insertBoard(Board board);

    // 수정
    void updateBoard(Board board);

    // 삭제 (PK로)
    void deleteBoard(int seq);

    // 상세 조회 (PK로)
    Board getBoard(int seq);

    // 목록 조회
    List<Board> getBoardList();
}

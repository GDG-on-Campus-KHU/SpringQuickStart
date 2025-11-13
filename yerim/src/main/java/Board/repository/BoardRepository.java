package Board.repository;

import Board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // 기본 CRUD 전부 자동 제공
    // findAll()  → 목록
    // findById(id) → 단건
    // save(entity) → insert + update
    // deleteById(id) → 삭제

    // 필요하면 이렇게 추가 가능:
    // List<Board> findByTitleContaining(String keyword);
}


/*

나의 말:
package boardimpl;

import domain.BoardVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO {
    private Connection conn = null;;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private final String Board_INSERT = "insert into board(seq, title, writer, content) " +
            "values((select nvl(max(seq), 0)+1 from board),?,?,?)";
    private final String Board_UPDATE = "update board set title=?, content=? where seq=?";
    private final String Board_DELETE = "delete from board where seq=?";
    private final String Board_GET = "select * from board where seq=?";
    private final String Board_LIST = "select * from board order by seq desc";

    //CRUD
    public void insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(Board_INSERT);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getWriter());
            pstmt.setString(3, vo.getContent());
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.close(pstmt, conn);
        }
    }
    public void updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        try{
            conn = JDBCUtill.getConnection();
            pstmt = conn.prepareStatement(Board_UPDATE);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getWriter());
            pstmt.setInt(3, vo.getSeq());
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.close(pstmt, conn);
        }
    }
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        try{
            conn = JDBCUtill.getConnection();
            pstmt = conn.prepareStatement(Board_DELETE);
            pstmt.setInt(1, vo.getSeq());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.close(pstmt, conn);
        }
    }
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        BoardVO board = null;
        try{
            conn = JDBCUtill.getConnection();
            pstmt = conn.prepareStatement(Board_GET);
            pstmt.setInt(1, vo.getSeq());
            rs = pstmt.executeQuery();
            if(rs.next()){
                board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs, pstmt, conn);
        }
        return board;
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        System.out.println("===> JDBC로 getBoardList() 기능 처리");
        List<BoardVO> boardList = new ArrayList<BoardVO>();
        try{
            conn = JDBCUtill.getConnection();
            pstmt = conn.prepareStatement(Board_LIST);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
                boardList.add(board);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs, pstmt, conn);
        }
        return boardList;
    }
}
* */
package com.example.demo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO extends JdbcDaoSupport {

    private final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content) VALUES((SELECT COALESCE(MAX(seq),0)+1 FROM board), ?, ?, ?)";

    private final String BOARD_UPDATE = "UPDATE board SET title=?, content=? WHERE seq=?";

    private final String BOARD_DELETE = "DELETE FROM board WHERE seq=?";

    private final String BOARD_GET = "SELECT * FROM board WHERE seq=?";

    private final String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";

    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    // 글 등록
    @Transactional
    public void insertBoard(BoardVO vo) {
        System.out.println("====> JdbcTemplate으로 insertBoard() 기능 처리 <====");
        getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
    }

    // 글 수정
    public void updateBoard(BoardVO vo) {
        System.out.println("====> JdbcTemplate으로 updateBoard() 기능 처리 <====");
        getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("====> JdbcTemplate으로 deleteBoard() 기능 처리 <====");
        getJdbcTemplate().update(BOARD_DELETE, vo.getSeq());
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("====> JdbcTemplate으로 getBoard() 기능 처리 <====");
        return getJdbcTemplate().queryForObject(BOARD_GET, new Object[] { vo.getSeq() }, new BoardRowMapper());
    }

    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo) {
        System.out.println("====> JdbcTemplate으로 getBoardList() 기능 처리 <====");
        return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
    }

    // 내부 RowMapper 클래스 정의
    private static class BoardRowMapper implements RowMapper<BoardVO> {
        @Override
        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BoardVO board = new BoardVO();
            board.setSeq(rs.getInt("SEQ"));
            board.setTitle(rs.getString("TITLE"));
            board.setWriter(rs.getString("WRITER"));
            board.setContent(rs.getString("CONTENT"));
            board.setRegDate(rs.getString("REGDATE"));
            board.setCnt(rs.getInt("CNT"));
            return board;
        }
    }
}

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

    @Transactional
    public void insertBoard(BoardVO vo) {
        getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
    }

    public void updateBoard(BoardVO vo) {
        getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
    }

    public void deleteBoard(BoardVO vo) {
        getJdbcTemplate().update(BOARD_DELETE, vo.getSeq());
    }

    public BoardVO getBoard(BoardVO vo) {
        return getJdbcTemplate().queryForObject(BOARD_GET, new Object[]{vo.getSeq()}, new BoardRowMapper());
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
    }

    // 전체 검색
    public List<BoardVO> searchBoard(String keyword) {
        String sql = "SELECT * FROM board WHERE title LIKE ? OR content LIKE ? ORDER BY seq DESC";
        String searchKeyword = "%" + keyword + "%";
        return getJdbcTemplate().query(sql, new Object[]{searchKeyword, searchKeyword}, new BoardRowMapper());
    }

    // 제목 검색
    public List<BoardVO> searchBoardByTitle(String keyword) {
        String sql = "SELECT * FROM board WHERE title LIKE ? ORDER BY seq DESC";
        String searchKeyword = "%" + keyword + "%";
        return getJdbcTemplate().query(sql, new Object[]{searchKeyword}, new BoardRowMapper());
    }

    // 내용 검색
    public List<BoardVO> searchBoardByContent(String keyword) {
        String sql = "SELECT * FROM board WHERE content LIKE ? ORDER BY seq DESC";
        String searchKeyword = "%" + keyword + "%";
        return getJdbcTemplate().query(sql, new Object[]{searchKeyword}, new BoardRowMapper());
    }

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

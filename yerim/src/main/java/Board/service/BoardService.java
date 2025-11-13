package Board.service;

import Board.domain.Board;
import org.springframework.stereotype.Service;
import Board.repository.BoardRepository;

import java.util.List;

@Service
public class BoardService implements BoardServiceImpl {

    private BoardRepository repo;

    public void BoardServiceImpl(BoardRepository repo) {
        this.repo = repo;
    }

    public BoardService(BoardRepository repo) {
        this.repo = repo;
    }

    @Override
    public void insertBoard(Board board) {
        repo.save(board);   // insert
    }

    @Override
    public void updateBoard(Board board) {
        repo.save(board);   // 같은 save로 update 가능
    }

    @Override
    public void deleteBoard(int seq) {
        repo.deleteById(seq);
    }

    @Override
    public Board getBoard(int seq) {
        return repo.findById(seq).orElse(null);
    }

    @Override
    public List<Board> getBoardList() {
        return repo.findAll();
    }
}

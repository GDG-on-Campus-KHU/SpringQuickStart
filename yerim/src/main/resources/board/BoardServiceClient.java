package domain;

import Board.domain.Board;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import Board.service.BoardService;

import java.util.List;

public class BoardServiceClient {
    public static void main(String[] args) {
        //1. 컨테이너 구동
        AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
        //2. spring 컨테이너로부터 boardserviceimpl 객체 lookup
        BoardService boardService = (BoardService) container.getBean("boardService");
        //3. 글 등록 기능 테스트
        Board vo = new Board();
        vo.setTitle("test1");
        vo.setWriter("Tester");
        vo.setContent("demo");
        boardService.insertBoard(board);
        //4. 글 목록 검색 기능 테스트
        List<Board> boardList = boardService.getBoardList();
        for (Board boardVO : boardList){
            Sytem.out.println("---> "+ domain.toString());
        }
        //5. 컨테이너 종료
        container.close();
    }

}
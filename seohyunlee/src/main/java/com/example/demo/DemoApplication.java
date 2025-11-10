package com.example.demo;

import com.example.demo.board.BoardService;
import com.example.demo.board.BoardVO;
import com.example.demo.user.UserService;
import com.example.demo.user.UserVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BoardService boardService, UserService userService) {
        return args -> {
            // 글 등록 기능 테스트
            BoardVO vo = new BoardVO();
            vo.setTitle("ttesting");
            vo.setWriter("Author Name");
            vo.setContent("This is a sample content for the board.");
            boardService.insertBoard(vo);
            System.out.println("Board inserted: " + vo);

            // 글 목록 조회 기능 테스트
            java.util.List<BoardVO> boardList = boardService.getBoardList(vo);
            for (BoardVO board : boardList) {
                System.out.println(board.toString());
            }

            // 로그인 기능 테스트
            UserVO uservo = new UserVO();
            uservo.setId("test");
            uservo.setPassword("pass12");

            // UserService는 인스턴스 메서드이므로 빈으로 주입받은 userService를 사용해야 합니다.
            UserVO user = userService.getUser(uservo);
            if (user != null) {
                System.out.println("Login successful: " + user);
            } else {
                System.out.println("Login failed for user ID: " + uservo.getId());
            }
        };
    }
}

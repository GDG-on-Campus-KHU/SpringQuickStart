package com.example.demo.controller;

import com.example.demo.board.BoardVO;
import com.example.demo.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    public String getBoardList(Model model) {
        List<BoardVO> boardList = boardService.getBoardList(new BoardVO());
        model.addAttribute("boardList", boardList);
        return "getBoardList";
    }

    // 검색
    @GetMapping("/searchBoard")
    public String searchBoard(@RequestParam("keyword") String keyword,
                              @RequestParam("searchCondition") String condition,
                              Model model) {

        List<BoardVO> searchList;
        switch (condition) {
            case "TITLE":
                searchList = boardService.searchBoardByTitle(keyword);
                break;
            case "CONTENT":
                searchList = boardService.searchBoardByContent(keyword);
                break;
            default:
                searchList = boardService.searchBoard(keyword);
        }

        model.addAttribute("boardList", searchList);
        return "getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(@RequestParam("seq") int seq, Model model) {
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        BoardVO board = boardService.getBoard(vo);
        model.addAttribute("board", board);
        return "getBoard";
    }

    @GetMapping("/insertBoard")
    public String showInsertForm() { return "insertBoard"; }

    @PostMapping("/insertBoard")
    public String insertBoard(@RequestParam("title") String title,
                              @RequestParam("writer") String writer,
                              @RequestParam("content") String content,
                              @RequestParam(value = "file", required = false) MultipartFile file) {

        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setWriter(writer);
        vo.setContent(content);

        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                String originalFileName = file.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

                Path path = Paths.get(uploadDir + storedFileName);
                Files.createDirectories(path.getParent());
                file.transferTo(path.toFile());

                vo.setFileName(storedFileName);
                vo.setOriginalFileName(originalFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boardService.insertBoard(vo);
        return "redirect:/getBoardList";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("seq") int seq) {
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        boardService.deleteBoard(vo);
        return "redirect:/getBoardList";
    }

    @GetMapping("/updateBoard")
    public String showUpdateForm(@RequestParam("seq") int seq, Model model) {
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        BoardVO board = boardService.getBoard(vo);
        model.addAttribute("board", board);
        return "updateBoard";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(@RequestParam("seq") int seq,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam(value = "file", required = false) MultipartFile file) {

        BoardVO existing = new BoardVO();
        existing.setSeq(seq);
        existing = boardService.getBoard(existing);

        existing.setTitle(title);
        existing.setContent(content);

        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                String originalFileName = file.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

                Path path = Paths.get(uploadDir + storedFileName);
                Files.createDirectories(path.getParent());
                file.transferTo(path.toFile());

                existing.setFileName(storedFileName);
                existing.setOriginalFileName(originalFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boardService.updateBoard(existing);
        return "redirect:/getBoardList";
    }
}

package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.BoardService;

@Controller
@SessionAttributes("board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/dataTransform.do")
    @ResponseBody
    public List<BoardVO> dataTrasnform(BoardVO vo) {
        vo.setSearchCondition("TITLE");
        vo.setSearchKeyword("");
        List<BoardVO> boardList = boardService.getBoardList(vo);
        return boardList;
    }

    @RequestMapping("insertBoard.do")
    public String insertBoard(BoardVO vo) throws IOException {
        // 파일 업로드 처리
        MultipartFile uploadFile = vo.getUploadFile();
        if (!uploadFile.isEmpty()) {
            String fileName = uploadFile.getOriginalFilename();
            uploadFile.transferTo(new File("/Users/hyejiyu/Desktop/2025/khu/gdgoc/SpringQuickStart/" + fileName));
        }

        boardService.insertBoard(vo);
        return "getBoardList.do";
    }

    @GetMapping("/insertBoard.do")
    public String insertBoardView() {
        return "insertBoard.jsp";
    }

    @RequestMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO vo) {
        boardService.insertBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO vo) {
        boardService.deleteBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO vo, Model model) {
        model.addAttribute("board", boardService.getBoard((vo))); // Model 정보 저장
        return "getBoard.jsp"; // View 이름 리턴
    }

    @RequestMapping("/getBoardList.do")
    public String getBoardList(BoardVO vo, Model model) {
        // Null check
        if (vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
        if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
        // Model 정보 저장
        model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장
        return "getBoardList.jsp"; // View 이름 리턴
    }

    @ModelAttribute("conditionMap")
    public Map<String, String> searchConditionMap() {
        Map<String, String> conditionMap = new HashMap<String, String>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
        return conditionMap;
    }

}

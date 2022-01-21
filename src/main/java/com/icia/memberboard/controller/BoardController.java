package com.icia.memberboard.controller;

import com.icia.memberboard.dto.BoardDetailDTO;
import com.icia.memberboard.dto.BoardSaveDTO;
import com.icia.memberboard.dto.BoardUpdateDTO;
import com.icia.memberboard.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService bs;
    //전체 조회
    @GetMapping("/findAll")
    public String findAll(Model model){

        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList",boardList);

        return "board/findAll";
    }
    //게시글 저장 폼
    @GetMapping("/save")
    public String saveForm(){
        return "board/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO, HttpSession session) throws IOException {

        bs.save(boardSaveDTO);
        return "redirect:/board/findAll";
    }
    //상세조회

    @GetMapping("{boardId}")
    public String findById(Model model, @PathVariable Long boardId){

        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "board/detail";
    }
    //게시글 삭제
    @GetMapping("delete/{boardId}")
    public String delete(@PathVariable Long boardId){
        bs.delete(boardId);
        return "redirect:/board/findAll";
    }
    //게시글 수정 페이지 요청
    @GetMapping("update/{boardId}")
    public String update(Model model,@PathVariable Long boardId){
        model.addAttribute("board",bs.findById(boardId));
        return "board/update";
    }
    //게시글 수정 실행
    @PutMapping("{boardId}")
    public ResponseEntity updateDo (@RequestBody BoardUpdateDTO boardSaveDTO, @PathVariable Long boardId) throws IOException {
         bs.update(boardSaveDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}

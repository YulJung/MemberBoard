package com.icia.memberboard.controller;

import com.icia.memberboard.common.PagingConst;
import com.icia.memberboard.dto.*;
import com.icia.memberboard.service.BoardService;
import com.icia.memberboard.service.CommentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService bs;
    private final CommentServiceImpl cs;

    //게시글 저장 폼
    @GetMapping("/save")
    public String saveForm(){
        return "board/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO, HttpSession session) throws IOException {

        bs.save(boardSaveDTO);
        return "redirect:/board/";
    }
    //상세조회
    @GetMapping("{boardId}")
    public String findById(Model model, @PathVariable Long boardId){

        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        model.addAttribute("commentList",commentList);
        return "board/detail";
    }
    //게시글 삭제
    @GetMapping("delete/{boardId}")
    public String delete(@PathVariable Long boardId){
        bs.delete(boardId);
        return "redirect:/board/";
    }
    //게시글 수정 페이지 요청
    @GetMapping("update/{boardId}")
    public String updateForm(Model model,@PathVariable Long boardId){
        model.addAttribute("board",bs.findById(boardId));
        return "board/update";
    }

    //수정 파일처리 추가
    @PutMapping("{boardId}")
    public ResponseEntity update (@PathVariable Long boardId,@ModelAttribute BoardUpdateDTO boardUpdateDTO)  throws IOException {
        bs.update(boardUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
    //검색
    @GetMapping("/search")
    public String search(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword,
                         Model model) {

        List<BoardDetailDTO> bList = bs.search(searchType, keyword);
        model.addAttribute("boardList", bList);
        return "redirect:/board/";
    }
    //페이징
    @GetMapping
    public String findAll(@PageableDefault(page =1) Pageable pageable, Model model){
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList",boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage+PagingConst.BLOCK_LIMIT-1)<boardList.getTotalPages())?startPage+PagingConst.BLOCK_LIMIT-1 : boardList.getTotalPages();

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "board/paging";
    }

}

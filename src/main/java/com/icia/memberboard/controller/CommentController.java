package com.icia.memberboard.controller;

import com.icia.memberboard.dto.CommentDetailDTO;
import com.icia.memberboard.dto.CommentSaveDTO;
import com.icia.memberboard.repository.CommentRepository;
import com.icia.memberboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService cs;

    @PostMapping("/save")
    public @ResponseBody
    List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO) {
        Long boardId = cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        return commentList;
    }
}

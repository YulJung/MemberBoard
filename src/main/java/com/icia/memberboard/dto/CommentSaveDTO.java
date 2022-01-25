package com.icia.memberboard.dto;

import lombok.Data;

@Data
public class CommentSaveDTO {
    private Long boardId;
    private String memberEmail;
    private String commentContents;

}

package com.icia.memberboard.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardUpdateDTO {
    private Long boardId;
    private String boardContents;
    private String boardTitle;
    private MultipartFile boardFile;
    private String boardFileName;
    private String memberEmail;
}

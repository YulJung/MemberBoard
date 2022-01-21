package com.icia.memberboard.dto;

import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.MemberEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class BoardSaveDTO {

    private String boardTitle;
    private String memberEmail;
    private String boardContents;
    private String boardFileName;
    private MultipartFile boardFile;


}

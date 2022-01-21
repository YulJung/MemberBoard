package com.icia.memberboard.dto;

import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.MemberEntity;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class BoardDetailDTO {
    private String boardTitle;
    private Long boardId;
    private String memberEmail;
    private String boardContents;
    private Long boardView;
    private String boardFileName;
    private LocalDateTime boardDate;
    public static BoardDetailDTO toBoardDetailDTO(BoardEntity e) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardTitle(e.getBoardTitle());
        boardDetailDTO.setBoardId(e.getBoardId());
        boardDetailDTO.setBoardContents(e.getBoardContents());
        boardDetailDTO.setBoardView(e.getBoardView());
        boardDetailDTO.setBoardFileName(e.getBoardFileName());
        if(e.getUpdateTime()==null) boardDetailDTO.setBoardDate(e.getCreateTime());
        else boardDetailDTO.setBoardDate(e.getUpdateTime());
        boardDetailDTO.setMemberEmail(e.getMemberEntity().getMemberEmail());
        return boardDetailDTO;
    }
}

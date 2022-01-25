package com.icia.memberboard.dto;

import com.icia.memberboard.entity.CommentEntity;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDetailDTO {
    private String memberEmail;
    private String commentContents;
    private Long boardId;
    private Long commentId;
    private LocalDateTime commentDate;

    public static CommentDetailDTO toCommentDetailDTO(CommentEntity comment) {
        System.out.println(comment.getMemberEntity()+"!!!!");
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setCommentContents(comment.getCommentContents());
        commentDetailDTO.setCommentDate(comment.getCreateTime());
        commentDetailDTO.setMemberEmail(comment.getMemberEntity().getMemberEmail());
        commentDetailDTO.setBoardId(comment.getBoardEntity().getBoardId());
        commentDetailDTO.setCommentId(comment.getCommentId());

        return commentDetailDTO;
    }
}

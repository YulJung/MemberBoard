package com.icia.memberboard.entity;

import com.icia.memberboard.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentContents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private MemberEntity memberEntity;


    public static CommentEntity toCommentEntity(CommentSaveDTO commentSaveDTO,BoardEntity board,MemberEntity member) {
        CommentEntity comment = new CommentEntity();
        comment.setCommentContents(commentSaveDTO.getCommentContents());
        comment.setBoardEntity(board);
        comment.setMemberEntity(member);
        return comment;
    }
}

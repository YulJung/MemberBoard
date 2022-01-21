package com.icia.memberboard.entity;

import com.icia.memberboard.dto.BoardSaveDTO;
import com.icia.memberboard.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String boardTitle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private MemberEntity memberEntity;
    private String boardContents;

    private Long boardView = 0L;
    private String boardFileName;
    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    public static BoardEntity toBoardSaveEntity(BoardSaveDTO boardSaveDTO,MemberEntity member) {
        BoardEntity board = new BoardEntity();
        board.setBoardContents(boardSaveDTO.getBoardContents());
        board.setBoardTitle(boardSaveDTO.getBoardTitle());
        board.setBoardFileName(boardSaveDTO.getBoardFileName());
        board.setMemberEntity(member);
        System.out.println(member.toString()+"////");
        return board;
    }

    public static BoardEntity toBoardUpdateDTO(BoardUpdateDTO boardUpdateDTO,MemberEntity member) {
        BoardEntity board = new BoardEntity();
        board.setBoardId(boardUpdateDTO.getBoardId());
        board.setBoardTitle(boardUpdateDTO.getBoardTitle());
        board.setBoardContents(boardUpdateDTO.getBoardContents());
        board.setBoardFileName(boardUpdateDTO.getBoardFileName());
        board.setMemberEntity(member);
        return board;
    }
}

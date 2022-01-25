package com.icia.memberboard.service;

import com.icia.memberboard.dto.CommentDetailDTO;
import com.icia.memberboard.dto.CommentSaveDTO;
import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.CommentEntity;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.BoardRepository;
import com.icia.memberboard.repository.CommentRepository;
import com.icia.memberboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository cr;
    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        MemberEntity member = mr.findByMemberEmail(commentSaveDTO.getMemberEmail());
        Optional<BoardEntity> board = br.findById(commentSaveDTO.getBoardId());
        CommentEntity comment = CommentEntity.toCommentEntity(commentSaveDTO,board.get(),member);
        return cr.save(comment).getBoardEntity().getBoardId();
    }
    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {

        Optional<BoardEntity> boardEntity = br.findById(boardId);

        List<CommentEntity> commentEntityList = cr.findByBoardEntity(boardEntity);
        System.out.println(commentEntityList +"commentList");
        List<CommentDetailDTO> commentDetailDTOList = new ArrayList<>();
        if(commentEntityList.isEmpty()){
            return null;
        }
        else {
            try {


                for (CommentEntity comment : commentEntityList) {
                    CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(comment);
                    commentDetailDTOList.add(commentDetailDTO);
                }
                return commentDetailDTOList;
            }catch (NullPointerException nullPointerException){
                return null;
            }
        }

    }
}

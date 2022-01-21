package com.icia.memberboard.service;

import com.icia.memberboard.dto.BoardDetailDTO;
import com.icia.memberboard.dto.BoardSaveDTO;
import com.icia.memberboard.dto.BoardUpdateDTO;
import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.BoardRepository;
import com.icia.memberboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> list = br.findAll();
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for(BoardEntity e : list){
            boardDetailDTOList.add(BoardDetailDTO.toBoardDetailDTO(e));
        }
        return boardDetailDTOList;
    }

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        MultipartFile boardFile = boardSaveDTO.getBoardFile();
        String boardFileName= boardFile.getOriginalFilename();
        boardFileName = System.currentTimeMillis()+"_"+boardFileName;
        String savePath = "D:\\eclipse\\Spring\\Worksapce\\Spring\\MemberBoard\\src\\main\\resources\\static\\img"+boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFileName(boardFileName);
        MemberEntity member = mr.findByMemberEmail(boardSaveDTO.getMemberEmail());
        System.out.println(member.getMemberEmail()+"11111");
        BoardEntity board = BoardEntity.toBoardSaveEntity(boardSaveDTO,member);

        return br.save(board).getBoardId();
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> board = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(board.get());
        return boardDetailDTO;
    }

    @Override
    public void delete(Long boardId) {
        br.deleteById(boardId);
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) throws IOException {
        MultipartFile boardFile = boardUpdateDTO.getBoardFile();
        String boardFileName= boardFile.getOriginalFilename();
        boardFileName = System.currentTimeMillis()+"_"+boardFileName;
        String savePath = "D:\\eclipse\\Spring\\Worksapce\\Spring\\MemberBoard\\src\\main\\resources\\static\\img"+boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardUpdateDTO.setBoardFileName(boardFileName);
        MemberEntity member = mr.findByMemberEmail(boardUpdateDTO.getMemberEmail());

        BoardEntity board = BoardEntity.toBoardUpdateDTO(boardUpdateDTO,member);
        return br.save(board).getBoardId();
    }
}

package com.icia.memberboard.service;

import com.icia.memberboard.common.PagingConst;
import com.icia.memberboard.dto.BoardDetailDTO;
import com.icia.memberboard.dto.BoardPagingDTO;
import com.icia.memberboard.dto.BoardSaveDTO;
import com.icia.memberboard.dto.BoardUpdateDTO;
import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.BoardRepository;
import com.icia.memberboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        String savePath = "D:\\eclipse\\Spring\\Worksapce\\Spring\\MemberBoard\\src\\main\\resources\\static\\img\\"+boardFileName;
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
        String savePath = "D:\\eclipse\\Spring\\Worksapce\\Spring\\MemberBoard\\src\\main\\resources\\static\\img\\"+boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardUpdateDTO.setBoardFileName(boardFileName);
        MemberEntity member = mr.findByMemberEmail(boardUpdateDTO.getMemberEmail());

        BoardEntity board = BoardEntity.toBoardUpdateDTO(boardUpdateDTO,member);
        return br.save(board).getBoardId();
    }

    @Override
    public List<BoardDetailDTO> search(String searchType, String keyword) {
        List<BoardEntity> boardEntityList = new ArrayList<>();
        if (searchType.equals("title")) {
            System.out.println("search by title");
            boardEntityList = br.findByBoardTitleIsContaining(keyword);
            System.out.println(boardEntityList+"boardList:");
            List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
            for (BoardEntity e : boardEntityList) {
                boardDetailDTOList.add(BoardDetailDTO.toBoardDetailDTO(e));

            }
            return boardDetailDTOList;
        } else if (searchType.equals("writer")) {
            System.out.println("search by writer");
            MemberEntity member = mr.findByMemberEmail(keyword);
            boardEntityList = br.findByMemberEntity(member);
            List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
            for (BoardEntity e : boardEntityList) {
                boardDetailDTOList.add(BoardDetailDTO.toBoardDetailDTO(e));
            }
            return boardDetailDTOList;
        }
        return null;
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
            int page = pageable.getPageNumber(); //페이지번호가져옴
            //요청한 페이지가 1이면 페이지값을 0으로 만들고, 1이아니면 요청페이지에서 1을뺀다
//        page = page-1;
            page = (page==1)? 0:(page-1);
            Page<BoardEntity> board= br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"boardId")));

            Page<BoardPagingDTO> boardList = board.map(
                    board1 -> new BoardPagingDTO(board1.getBoardId(),
                            board1.getMemberEntity().getMemberEmail(),
                            board1.getBoardTitle())
            );
            return boardList;

        }


}

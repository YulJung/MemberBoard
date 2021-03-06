package com.icia.memberboard.service;

import com.icia.memberboard.dto.BoardDetailDTO;
import com.icia.memberboard.dto.BoardPagingDTO;
import com.icia.memberboard.dto.BoardSaveDTO;
import com.icia.memberboard.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface BoardService {
    List<BoardDetailDTO> findAll();

    Long save(BoardSaveDTO boardSaveDTO) throws IOException;

    BoardDetailDTO findById(Long boardId);

    void delete(Long boardId);

    Long update(BoardUpdateDTO boardSaveDTO) throws IOException;

    List<BoardDetailDTO> search(String searchType, String keyword);

    Page<BoardPagingDTO> paging(Pageable pageable);
}

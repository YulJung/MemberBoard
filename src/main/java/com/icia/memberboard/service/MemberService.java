package com.icia.memberboard.service;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;

    boolean login(MemberDetailDTO memberDetailDTO);

    String emailCheck(String memberEmail);

    List<MemberDetailDTO> findAll();

    void delete(Long memberId);
}

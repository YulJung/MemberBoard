package com.icia.memberboard.service;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;
import com.icia.memberboard.dto.MemberUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;

    boolean login(MemberDetailDTO memberDetailDTO);

    String emailCheck(String memberEmail);

    List<MemberDetailDTO> findAll();

    void delete(Long memberId);

    MemberDetailDTO findByMemberEmail(String memberEmail);

    Long update(MemberUpdateDTO memberUpdateDTO) throws IOException;
}

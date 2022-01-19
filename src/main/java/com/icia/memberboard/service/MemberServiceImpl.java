package com.icia.memberboard.service;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity member = MemberEntity.toMemberSaveEntity(memberSaveDTO);
        return mr.save(member).getMemberNo();
    }

    @Override
    public boolean login(MemberDetailDTO memberDetailDTO) {
        MemberEntity member = MemberEntity.toMemberLoginEntity(memberDetailDTO);
        return mr.findByMemberEmailAndMemberPassword(member.getMemberEmail(),member.getMemberPassword());
    }
}

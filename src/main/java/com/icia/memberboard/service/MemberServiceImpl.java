package com.icia.memberboard.service;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;
import com.icia.memberboard.entity.MemberEntity;
import com.icia.memberboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        MultipartFile fileName = memberSaveDTO.getMemberFile();
        String memberProfile = fileName.getOriginalFilename();
        memberProfile = System.currentTimeMillis()+"_"+memberProfile;
        String savePath = "D:\\eclipse\\Spring\\Worksapce\\Spring\\MemberBoard\\src\\main\\resources\\static\\img"+memberProfile;
        if(!fileName.isEmpty()){
            fileName.transferTo(new File(savePath));
        }
        memberSaveDTO.setMemberProfile(memberProfile);
        MemberEntity member = MemberEntity.toMemberSaveEntity(memberSaveDTO);
        return mr.save(member).getMemberId();
    }

    @Override
    public boolean login(MemberDetailDTO memberDetailDTO) {
        MemberEntity member = MemberEntity.toMemberLoginEntity(memberDetailDTO);
        MemberEntity byMemberEmailAndMemberPassword = mr.findByMemberEmailAndMemberPassword(member.getMemberEmail(), member.getMemberPassword());
        if(byMemberEmailAndMemberPassword==null){

            return false;
        }
        else return true;
    }

    @Override
    public String emailCheck(String memberEmail) {
        MemberEntity result = mr.findByMemberEmail(memberEmail);
        if(result==null) return "ok";
        else return "no";

    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntities = mr.findAll();
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for(MemberEntity entity : memberEntities){
            memberList.add(MemberDetailDTO.toMemberDetailDTO(entity));
        }
        return memberList;
    }

    @Override
    public void delete(Long memberId) {
        mr.deleteById(memberId);
    }
}

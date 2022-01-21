package com.icia.memberboard.dto;

import com.icia.memberboard.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberDetailDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberProfile;

    public static MemberDetailDTO toMemberDetailDTO(MemberEntity entity) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(entity.getMemberId());
        memberDetailDTO.setMemberEmail(entity.getMemberEmail());
        memberDetailDTO.setMemberName(entity.getMemberName());
        memberDetailDTO.setMemberPhone(entity.getMemberPhone());
        memberDetailDTO.setMemberProfile(entity.getMemberProfile());
        memberDetailDTO.setMemberPassword(entity.getMemberPassword());
        return memberDetailDTO;
    }
}

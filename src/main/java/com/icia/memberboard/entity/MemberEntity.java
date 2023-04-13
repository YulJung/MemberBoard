package com.icia.memberboard.entity;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;
import com.icia.memberboard.dto.MemberUpdateDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(unique = true)
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberProfile;


    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REFRESH, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REFRESH, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static MemberEntity toMemberSaveEntity(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberPhone(memberSaveDTO.getMemberPhone());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberProfile(memberSaveDTO.getMemberProfile());
        return memberEntity;
     
    }

    public static MemberEntity toMemberLoginEntity(MemberDetailDTO memberDetailDTO) {
        MemberEntity member = new MemberEntity();
        member.setMemberEmail(memberDetailDTO.getMemberEmail());
        member.setMemberPassword(memberDetailDTO.getMemberPassword());
        return member;
    }

    public static MemberEntity toMemberUpdateEntity(MemberUpdateDTO memberUpdateDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberUpdateDTO.getMemberId());
        memberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberUpdateDTO.getMemberPassword());
        memberEntity.setMemberName(memberUpdateDTO.getMemberName());
        memberEntity.setMemberPhone(memberUpdateDTO.getMemberPhone());
        memberEntity.setMemberProfile(memberUpdateDTO.getMemberProfile());
        return memberEntity;
    }
}

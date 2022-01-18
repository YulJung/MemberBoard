package com.icia.memberboard.dto;

import lombok.Data;

@Data
public class MemberDetailDTO {
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberProfile;
}

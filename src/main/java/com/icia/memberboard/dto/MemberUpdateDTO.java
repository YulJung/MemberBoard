package com.icia.memberboard.dto;

import lombok.Data;

@Data
public class MemberUpdateDTO {
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberProfile;
}

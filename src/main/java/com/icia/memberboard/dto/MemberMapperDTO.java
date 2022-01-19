package com.icia.memberboard.dto;

import lombok.Data;

@Data
public class MemberMapperDTO {
    private Long memberNo;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;

    public MemberMapperDTO(String eamil,String password,String mname,String phone){

    }
}

package com.koreait.basic.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// lombok
@Getter
@Setter
@ToString
// lombok
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private int gender;
    private String rft;
}

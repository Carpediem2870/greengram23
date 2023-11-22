package com.green.greengram2.user.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserInfoVo {
    private int feedCnt;
    private int favCnt;
    private String nm;
    private String createdAt;
    private String pic;
}

package com.green.greengram2.user;

import com.green.greengram2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUserSignup(UserSignupProcDto dto);
    UserSigninProcVo selUserForSignin(UserSigninDto dto);
    UserInfoVo getUserInfo(int targetIuser);
    int updUserInfo(UserPatchPicDto dto);

}

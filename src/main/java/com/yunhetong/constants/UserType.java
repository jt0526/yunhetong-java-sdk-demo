package com.yunhetong.constants;

/**
 * 用户类型
 *
 * Created by qianyj on 2017/4/29.
 */
public enum UserType {
    /*,个人用户*/
    PERSONAL_USER((byte)1),
    /*,企业用户*/
    COMPANY_USER((byte)2),
    /*,平台用户*/
    PLATFORM_USER((byte)4);
    private byte userType;
    UserType(byte userType){
        this.setUserType(userType);
    }
    public byte getUserType() {
        return userType;
    }
    public void setUserType(byte userType) {
        this.userType = userType;
    }
}

package com.yunhetong.model;

import com.yunhetong.constants.UserType;
import com.yunhetong.sdk.bean.LxUser;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 用户对象
 * Created by qianyj on 2017/4/29.
 */
public class AddUserModel {
    private String appUserId;
    private String cellNum;
    private Byte userType;
    private String userName;
    private String certifyType;
    private String certifyNumber;
    private String createSignature = "0";

    public AddUserModel() {
        this.appUserId = "javaDemoUser" + RandomStringUtils.randomAlphabetic(3);
        this.cellNum = "1" + RandomStringUtils.randomNumeric(10);
        this.userType = UserType.PERSONAL_USER.getUserType();
        this.userName = RandomStringUtils.randomAlphanumeric(10);
        this.certifyType = String.valueOf(LxUser.CertifyType.ID_CARD.getValue());
        this.certifyNumber = "3" + RandomStringUtils.randomNumeric(17);
        this.createSignature = "1";
    }

    public AddUserModel(String createSignature) {
        this.createSignature = createSignature;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public AddUserModel setAppUserId(String appUserId) {
        this.appUserId = appUserId;
        return this;
    }

    public String getCellNum() {
        return cellNum;
    }

    public AddUserModel setCellNum(String cellNum) {
        this.cellNum = cellNum;
        return this;
    }

    public Byte getUserType() {
        return userType;
    }

    public AddUserModel setUserType(Byte userType) {
        this.userType = userType;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AddUserModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getCertifyType() {
        return certifyType;
    }

    public AddUserModel setCertifyType(String certifyType) {
        this.certifyType = certifyType;
        return this;
    }

    public String getCertifyNumber() {
        return certifyNumber;
    }

    public AddUserModel setCertifyNumber(String certifyNumber) {
        this.certifyNumber = certifyNumber;
        return this;
    }

    public String getCreateSignature() {
        return createSignature;
    }

    public AddUserModel setCreateSignature(String createSignature) {
        this.createSignature = createSignature;
        return this;
    }
}

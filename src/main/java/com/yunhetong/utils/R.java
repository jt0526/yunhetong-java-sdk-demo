package com.yunhetong.utils;

import com.yunhetong.constants.UserType;
import com.yunhetong.model.ActorModel;
import com.yunhetong.model.AddUserModel;
import com.yunhetong.model.ContractModel;
import com.yunhetong.sdk.bean.LxContractActor;
import com.yunhetong.sdk.bean.LxUser;

/**
 * Created by qianyj on 2017/4/28.
 */
public class R {
    /**
     * 生成测试用户 userA
     *
     * @return 返回一个 userA
     */
    public static LxUser getUserA() {
        LxUser lxUser = new LxUser();
        lxUser.setAppUserId("javaDemoUserA")            // 设置用户id
                .setCertifyNumber("3301191923987621")                      // 设置证件号码
                .setUserType(LxUser.UserType.COMPANY)            // 设置用户类型
                .setPhone("18698575614")                      // 设置手机号码
                .setUserName("测试企业用户A")                          // 设置用户名
                .setCreateSignature("1")
                .setCertifyType(LxUser.CertifyType.BUSINESS_LICENCE)   // 设置实名认证类型
        ;
        return lxUser;
    }

    /**
     * 生成测试用户 userB
     *
     * @return 返回用户 B
     */
    public static LxUser getUserB() {
        LxUser lxUser = new LxUser();
        lxUser.setAppUserId("javaDemoUserB")                // 设置用户Id
                .setCertifyNumber("330119192398762162")                      // 设置证件号码
                .setUserType(LxUser.UserType.USER)            // 设置用户类型
                .setPhone("18698575613")                      // 设置手机号码
                .setUserName("测试个人用户B")                          // 设置用户名
                .setCreateSignature("1")
                .setCertifyType(LxUser.CertifyType.ID_CARD)   // 设置实名认证类型
        ;
        return lxUser;
    }

    /**
     * 生成测试平台用户 PlatFormUser
     *
     * @return 返回用户 PlatFormUser
     */
    public static AddUserModel getPlatFormUser() {
        AddUserModel addUserModel = new AddUserModel("1");
        addUserModel.setAppUserId("platformUser")                // 设置用户Id
                .setCertifyNumber("330119192398762159")                      // 设置证件号码
                .setUserType(UserType.PLATFORM_USER.getUserType())            // 设置用户类型
                .setCellNum("18698575609")                      // 设置手机号码
                .setUserName("平台用户")                          // 设置用户名
                .setCertifyType("4")   // 设置实名认证类型
        ;
        return addUserModel;
    }

    /**
     * 创建合同参与方
     *
     * @return 返回合同参与方
     */
    public static String getActor() {
        return "[{\"appUserId\": \"" + getUserA().getAppUserId() + "\",\"keyWord\": \"测试1\"}," +
                "{\"appUserId\": \"" + getUserB().getAppUserId() + "\",\"keyWord\": \"测试2\"}]";
    }

    /**
     * 模板创建测试合同
     * @return 测试合同
     */
    public static ContractModel getTestContract() {
        ContractModel contractModel = new ContractModel();
        contractModel.setTitle("测试合同")              // 设置合同标题
                .setDefContractNo("testContract")           // 设置自定义合同编号
                .setTemplateId("93961")                // 设置合同模板 Id
                .setParam("{\"${name1}\": \"测试1\", \"${name2}\": \"测试2\"}")           // 这是模板占位符
        ;
        return contractModel;
    }

    /**
     * 创建合同签署双方
     *
     * @return 返回合同签署双方
     */
    public static String getSigner() {
        return "[\"" + getUserA().getAppUserId() + "\"," +
                "\"" + getUserB().getAppUserId() + "\"]";
    }

}

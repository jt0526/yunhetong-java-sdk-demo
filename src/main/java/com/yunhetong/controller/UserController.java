package com.yunhetong.controller;

import com.yunhetong.model.AddUserModel;
import com.yunhetong.sdk.util.LxHttpUtil;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户操作类
 * <p>
 * Created by qianyj on 2017/4/29.
 */
@RestController
@RequestMapping(value = "user")
@PropertySource(value = "local.properties")
public class UserController {
    @Value("${sdk.appid}")
    private String appid;
    @Value("${sdk.password}")
    private String password;
    @Value("${sdk.host}")
    private String host;

    /**
     * 添加用户
     *
     * @return
     */
    @GetMapping(value = "/addUser")
    protected String addUser() {
        //创建添加的用户，并把相应字段放入map中
        AddUserModel user = new AddUserModel();
        BeanMap beanMap = new BeanMap(user);
        Map<String, String> params = new HashMap<String, String>();
        for (Object key : beanMap.keySet()) {
            if (!"class".equals(key.toString()))
                params.put(key.toString(), beanMap.get(key).toString());
        }
        params.put("appId", appid);
        params.put("password", password);
        return LxHttpUtil.post(host + "/userInfo/addUser", params);
    }

    /**
     * 修改手机号
     *
     * @param token 用户token
     * @return
     */
    @PostMapping(value = "/modifyCellNum")
    protected String modifyCellNum(@RequestParam(value = "token", required = false) String token) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        AddUserModel user = new AddUserModel();
        Map<String, String> params = new HashMap<String, String>(1);
        params.put("cellNum", user.getCellNum());

        return LxHttpUtil.post(host + "/userInfo/modifyCellNum?token=" + token, params);
    }

    /**
     * 修改手机号
     *
     * @param token 用户token
     * @return
     */
    @PostMapping(value = "/modifyUserName")
    protected String modifyUserName(@RequestParam(value = "token", required = false) String token) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        Map<String, String> params = new HashMap<String, String>(2);
        AddUserModel user = new AddUserModel();
        params.put("userName", user.getUserName());
        params.put("createSignature", user.getCreateSignature());

        return LxHttpUtil.post(host + "/userInfo/modifyUserName?token=" + token, params);
    }

}

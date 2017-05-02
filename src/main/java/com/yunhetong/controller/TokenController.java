package com.yunhetong.controller;

import com.yunhetong.sdk.util.LxHttpUtil;
import com.yunhetong.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * token操作类
 * <p>
 * Created by qianyj on 2017/4/29.
 */
@RestController
@RequestMapping("/token")
@PropertySource(value = "local.properties")
public class TokenController {
    @Value("${sdk.appid}")
    private String appid;
    @Value("${sdk.password}")
    private String password;
    @Value("${sdk.host}")
    private String host;

    /**
     * 获取token
     *
     * @param user
     * @return
     */
    @GetMapping(value = "/getToken")
    protected String getToken(@RequestParam(value = "user") String user) {

        Map<String, String> params = new HashMap<String, String>(3);
        params.put("appId", appid);
        params.put("password", password);
        if ("A".equals(user)) {
            params.put("appUserId", R.getUserA().getAppUserId());
        }
        if ("B".equals(user)) {
            params.put("appUserId", R.getUserB().getAppUserId());
        }
        if ("C".equals(user)) {
            params.put("appUserId", R.getPlatFormUser().getAppUserId());
        }

        return LxHttpUtil.post(host + "/token/getToken", params);
    }

}

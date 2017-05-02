package com.yunhetong.controller;

import com.yunhetong.model.ContractModel;
import com.yunhetong.sdk.util.LxHttpUtil;
import com.yunhetong.utils.LxHttpUploadFile;
import com.yunhetong.utils.R;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 合同操作类
 * Created by qianyj on 2017/5/1.
 */
@Controller
@RequestMapping(value = "contract")
@PropertySource(value = "local.properties")
public class ContractController {
    @Value("${sdk.host}")
    private String host;

    /**
     * 根据模板创建合同
     *
     * @param token
     * @return
     */
    @PostMapping(value = "/templateContract")
    @ResponseBody
    protected String templateContract(@RequestParam(value = "token", required = false) String token) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        BeanMap beanMap = new BeanMap(R.getTestContract());
        Map<String, String> params = new HashMap<String, String>();
        for (Object key : beanMap.keySet()) {
            if (!"class".equals(key.toString()))
                params.put(key.toString(), beanMap.get(key).toString());
        }

        return LxHttpUtil.post(host + "/contract/templateContract?token=" + token, params);
    }

    /**
     * 通过上传文件创建合同
     *
     * @param token
     * @param multipartFile 文件
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/fileContract")
    @ResponseBody
    protected String fileContract(String token, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        if (multipartFile == null)
            return "文件不能为空！";
        ContractModel contractModel = R.getTestContract();
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", contractModel.getTitle());
        params.put("defContractNo", contractModel.getDefContractNo());
        File file = new File("1.docx");
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);

        return LxHttpUploadFile.postUploadFile(host + "/contract/fileContract?token=" + token, file, params);
    }

    /**
     * 添加参与者
     *
     * @param token      合同创建者的token
     * @param contractId 合同id
     * @return
     */
    @PostMapping(value = "/addPartner")
    @ResponseBody
    protected String addPartner(@RequestParam(value = "token", required = false) String token,
                                @RequestParam(value = "contractId", required = false) String contractId) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        if (contractId == null || "".equals(contractId.trim()))
            return "contractId不能为空！";
        Map<String, String> params = new HashMap<String, String>();
        params.put("contractId", contractId);
        params.put("partners", R.getActor());

        return LxHttpUtil.post(host + "/contract/addPartner?token=" + token, params);
    }

    /**
     * 签署合同
     *
     * @param token      平台用户的token
     * @param contractId 合同id
     * @return
     */
    @PostMapping(value = "/signContract")
    @ResponseBody
    protected String signContract(@RequestParam(value = "token", required = false) String token,
                                  @RequestParam(value = "contractId", required = false) String contractId) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        if (contractId == null || "".equals(contractId.trim()))
            return "contractId不能为空！";
        Map<String, String> params = new HashMap<String, String>();
        params.put("contractId", contractId);
        params.put("signer", R.getSigner());

        return LxHttpUtil.post(host + "/contract/signContract?token=" + token, params);
    }

    /**
     * 作废合同
     *
     * @param token      合同参与者的token
     * @param contractId 合同id
     * @return
     */
    @PostMapping(value = "/invalidContract")
    @ResponseBody
    protected String invalidContract(@RequestParam(value = "token", required = false) String token,
                                     @RequestParam(value = "contractId", required = false) String contractId) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        if (contractId == null || "".equals(contractId.trim()))
            return "contractId不能为空！";
        Map<String, String> params = new HashMap<String, String>();
        params.put("contractId", contractId);

        return LxHttpUtil.post(host + "/contract/invalid?token=" + token, params);
    }

    /**
     * 查看合同列表
     *
     * @param token 平台用户的token
     * @return
     */
    @PostMapping(value = "/contractList")
    @ResponseBody
    protected String contractList(@RequestParam(value = "token", required = false) String token) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        return LxHttpUtil.get("/contract/list?token=" + token);
    }

    /**
     * 查看合同签署状态详情
     *
     * @param token      平台用户或者参与者用户的token
     * @param contractId 合同id
     * @return
     */
    @PostMapping(value = "/contractDetail")
    @ResponseBody
    protected String contractDetail(@RequestParam(value = "token", required = false) String token,
                                    @RequestParam(value = "contractId", required = false) String contractId) {
        if (token == null || "".equals(token.trim()))
            return "token不能为空！";
        if (contractId == null || "".equals(contractId.trim()))
            return "contractId不能为空！";
        return LxHttpUtil.get("/contract/detail?token=" + token + "&contractId=" + contractId);
    }

    /**
     * 下载合同
     *
     * @param token      平台用户或者参与者用户的token
     * @param contractId 合同id
     * @return
     */
    @GetMapping(value = "/downloadContract")
    protected String downloadContract(@RequestParam(value = "token") String token,
                                      @RequestParam(value = "contractId") String contractId) {
        return "redirect:" + host + "/contract/download?token=" + token + "&contractId=" + contractId;
    }

}

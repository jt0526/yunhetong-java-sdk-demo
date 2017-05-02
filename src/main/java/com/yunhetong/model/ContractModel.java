package com.yunhetong.model;

/**
 * Created by qianyj on 2017/5/1.
 */
public class ContractModel {
    private String title;
    private String defContractNo;
    private String templateId;
    private String param;

    public String getTitle() {
        return title;
    }

    public ContractModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDefContractNo() {
        return defContractNo;
    }

    public ContractModel setDefContractNo(String defContractNo) {
        this.defContractNo = defContractNo;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public ContractModel setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getParam() {
        return param;
    }

    public ContractModel setParam(String param) {
        this.param = param;
        return this;
    }
}

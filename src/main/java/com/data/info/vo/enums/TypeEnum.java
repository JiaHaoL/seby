package com.data.info.vo.enums;

public enum TypeEnum {

    type2023062201("http://www.bifen108.cn", "2023062201", "https://1danbo98.danbocd.cc:2330/vodplay/{NUMBER}-1-1.html");

    private String url;
    private String code;
    private String rule;

    TypeEnum(String url, String code, String rule) {
        this.url = url;
        this.code = code;
        this.rule = rule;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}

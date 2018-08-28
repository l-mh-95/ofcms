package com.ofsoft.cms.core.api.check;

/**
 * @author OF
 * @version v1.0
 * @className CheckType
 * @date 2018/8/25
 */
public enum CheckType {
    MOBILE("MobileCheck"),
    NUMBER("NumberCheck"),
    EMAIL("EmailCheck"),
    MONEY("MobileCheck");
    String value = "";
    private String ClassPath = "com.ofsoft.cms.core.api.check.";

    private CheckType(String value) {
        this.value = ClassPath + value;
    }

    public String getValue() {
        return this.value;
    }
}

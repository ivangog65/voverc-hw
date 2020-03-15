package com.voverc.provisioning.model;

public enum CommonConfigurationKeys {

    USERNAME("username"), PASSWORD("password"), DOMAIN("domain"), PORT("port"), CODECS("codecs");

    CommonConfigurationKeys(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}

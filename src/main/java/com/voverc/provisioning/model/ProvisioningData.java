package com.voverc.provisioning.model;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProvisioningData {

    private Map<String, String> data = new LinkedHashMap<>();

    private Map<String, String> put(String key, String value) {
        data.put(key, value);
        return data;
    }

    public void put(CommonConfigurationKeys key, String value) {
        data.put(key.getValue(), value);
    }

    public void putAll(Map<String, String> map) {
        map.forEach(this::put);
    }

    public String toJSON() {
        return new Gson().toJson(data);
    }

    public String toProperties() {
        StringBuilder sb = new StringBuilder();
        data.forEach((key, value) -> sb.append(key).append("=").append(value).append(System.lineSeparator()));
        return sb.toString();
    }
}

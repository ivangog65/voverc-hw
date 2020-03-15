package com.voverc.provisioning.model;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ProvisioningData {

    private Map<String, String> data = new LinkedHashMap<>();

    private Map<String, String> put(String key, String value) {
        data.put(key, value);
        return data;
    }

    public Map<String, String> put(CommonConfigurationKeys key, String value) {
        data.put(key.getValue(), value);
        return data;
    }

    public Map<String, String> putAll(Map<String, String> map) {
        map.forEach(this::put);
        return data;
    }
}

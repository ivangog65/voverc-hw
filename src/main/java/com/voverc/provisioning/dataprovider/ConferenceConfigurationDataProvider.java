package com.voverc.provisioning.dataprovider;

import com.voverc.provisioning.model.ProvisioningData;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;
import java.util.stream.Collectors;

public class ConferenceConfigurationDataProvider extends BaseConfigurationDataProvider {

    public Map<String, String> parseOverrideFragment(String overrideFragment) {
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        return jsonParser.parseMap(overrideFragment)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue())));
    }

    @Override
    public String formatProvisioningData(ProvisioningData provisioningData) {
        return provisioningData.toJSON();
    }

}

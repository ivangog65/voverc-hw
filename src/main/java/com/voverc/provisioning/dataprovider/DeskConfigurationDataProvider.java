package com.voverc.provisioning.dataprovider;

import com.google.common.collect.Maps;
import com.voverc.provisioning.model.ProvisioningData;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

public class DeskConfigurationDataProvider extends BaseConfigurationDataProvider {

    public Map<String, String> parseOverrideFragment(String overrideFragment) {
        Properties props = new Properties();
        try {
            props.load(new StringReader(overrideFragment));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Maps.fromProperties(props);
    }

    @Override
    public String formatProvisioningData(ProvisioningData provisioningData) {
        return provisioningData.toProperties();
    }

}

package com.voverc.provisioning.dataprovider;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.model.CommonConfigurationKeys;
import com.voverc.provisioning.model.ProvisioningData;
import com.voverc.provisioning.model.ProvisioningProperties;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public abstract class BaseConfigurationDataProvider {

    public ProvisioningData getConfiguration(Device device, ProvisioningProperties properties) {
        ProvisioningData data = new ProvisioningData();
        data.put(CommonConfigurationKeys.USERNAME, device.getUsername());
        data.put(CommonConfigurationKeys.PASSWORD, device.getPassword());
        data.put(CommonConfigurationKeys.DOMAIN, properties.getDomain());
        data.put(CommonConfigurationKeys.PORT, properties.getPort());
        data.put(CommonConfigurationKeys.CODECS, properties.getCodecs());
        if (StringUtils.isNotEmpty(device.getOverrideFragment())) {
            data.putAll(parseOverrideFragment(device.getOverrideFragment()));
        }
        return data;
    }

    protected abstract Map<String, String> parseOverrideFragment(String overrideFragment);

    public abstract String formatProvisioningData(ProvisioningData provisioningData);
}

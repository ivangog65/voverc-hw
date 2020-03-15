package com.voverc.provisioning.service;

import com.voverc.provisioning.dataprovider.BaseConfigurationDataProvider;
import com.voverc.provisioning.dataprovider.ConferenceConfigurationDataProvider;
import com.voverc.provisioning.dataprovider.DeskConfigurationDataProvider;
import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.model.ProvisioningData;
import com.voverc.provisioning.model.ProvisioningProperties;
import com.voverc.provisioning.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ProvisioningProperties provisioningProperties;


    public String getProvisioningFile(String macAddress) {
        Device device = deviceRepository.findByMacAddress(macAddress);
        if (device == null) {
            throw new IllegalArgumentException("Unable to find device with provided macAddress: " + macAddress);
        }
        BaseConfigurationDataProvider provider = getConfigurationProvider(device.getModel());
        ProvisioningData provisioningData = provider.getConfiguration(device, provisioningProperties);
        return provider.formatProvisioningData(provisioningData);
    }

    private BaseConfigurationDataProvider getConfigurationProvider(Device.DeviceModel model) {
        if (model == Device.DeviceModel.DESK) {
            return new DeskConfigurationDataProvider();
        }
        if (model == Device.DeviceModel.CONFERENCE) {
            return new ConferenceConfigurationDataProvider();
        }
        throw new RuntimeException("Unknown device model: " + model);
    }
}

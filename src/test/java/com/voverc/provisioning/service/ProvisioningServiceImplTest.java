package com.voverc.provisioning.service;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.model.ProvisioningProperties;
import com.voverc.provisioning.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProvisioningServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private ProvisioningProperties provisioningProperties;

    @InjectMocks
    private ProvisioningServiceImpl provisioningService;

    @Test(expected = IllegalArgumentException.class)
    public void testGetProvisioningFileDeviceIsNull() {
        when(deviceRepository.findByMacAddress(anyString())).thenReturn(null);
        provisioningService.getProvisioningFile("1");
        fail("IllegalArgumentException should be thrown.");
    }

    @Test
    public void testGetProvisioningFileConference() {
        String expected = "{\"username\":\"user\",\"password\":\"pass\",\"domain\":" +
                "\"sip.anotherdomain.com\",\"port\":\"5161\",\"codecs\":\"G561\",\"timeout\":\"10\"}";

        Device device = new Device();
        device.setMacAddress("11-22-33-44-55-66");
        device.setUsername("user");
        device.setPassword("pass");
        device.setModel(Device.DeviceModel.CONFERENCE);
        device.setOverrideFragment("{\"domain\":\"sip.anotherdomain.com\",\"port\":\"5161\",\"timeout\":10}");

        when(deviceRepository.findByMacAddress(anyString())).thenReturn(device);
        when(provisioningProperties.getDomain()).thenReturn("sip.voverc.com");
        when(provisioningProperties.getPort()).thenReturn("8888");
        when(provisioningProperties.getCodecs()).thenReturn("G561");

        String actual = provisioningService.getProvisioningFile("11-22-33-44-55-66");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetProvisioningFileDesk() {
        String expected = "username=user\n" +
                "password=pass\n" +
                "domain=sip.anotherdomain.com\n" +
                "port=5161\n" +
                "codecs=G561\n" +
                "timeout=10\n";

        Device device = new Device();
        device.setMacAddress("11-22-33-44-55-66");
        device.setUsername("user");
        device.setPassword("pass");
        device.setModel(Device.DeviceModel.DESK);
        device.setOverrideFragment("domain=sip.anotherdomain.com\nport=5161\ntimeout=10");

        when(deviceRepository.findByMacAddress(anyString())).thenReturn(device);
        when(provisioningProperties.getDomain()).thenReturn("sip.voverc.com");
        when(provisioningProperties.getPort()).thenReturn("8888");
        when(provisioningProperties.getCodecs()).thenReturn("G561");

        String actual = provisioningService.getProvisioningFile("11-22-33-44-55-66");
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void testGetProvisioningFileUnknownDeviceType() {
        Device device = new Device();
        device.setModel(null);

        when(deviceRepository.findByMacAddress(anyString())).thenReturn(device);

        provisioningService.getProvisioningFile("11-22-33-44-55-66");
        fail("RuntimeException should be thrown.");
    }
}

package com.voverc.provisioning.repository;

import com.voverc.provisioning.entity.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void testGetDeviceByMacAddress() {
        String macAddress = "1";
        Device device = new Device();
        device.setMacAddress(macAddress);
        device.setModel(Device.DeviceModel.DESK);
        entityManager.persist(device);

        Device actual = deviceRepository.findByMacAddress(macAddress);

        assertEquals(device, actual);
    }

    @Test
    public void testGetDeviceByMacAddressNotFound() {
        Device actual = deviceRepository.findByMacAddress("2");
        assertNull(actual);
    }
}

package com.voverc.provisioning.controller;

import com.voverc.provisioning.service.ProvisioningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProvisioningController.class)
public class ProvisioningControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProvisioningService provisioningService;

    @Test
    public void testGetProvisioningConfiguration() throws Exception {
        String macAddress = "test";
        String content = "TestContent";

        given(provisioningService.getProvisioningFile(macAddress)).willReturn(content);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/provisioning/" + macAddress))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }
}

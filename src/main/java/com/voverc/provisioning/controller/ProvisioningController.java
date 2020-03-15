package com.voverc.provisioning.controller;

import com.voverc.provisioning.service.ProvisioningService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {

    @Autowired
    private ProvisioningService provisioningService;

    @GetMapping("/provisioning/{macAddress}")
    public ResponseEntity<InputStreamResource> getProvisioningConfiguration(@PathVariable String macAddress) {
        String provisioningData = provisioningService.getProvisioningFile(macAddress);
        return ResponseEntity
                .ok()
                .body(new InputStreamResource(IOUtils.toInputStream(provisioningData, Charset.forName("UTF-8"))));
    }
}
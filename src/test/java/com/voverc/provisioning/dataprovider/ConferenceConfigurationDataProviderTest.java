package com.voverc.provisioning.dataprovider;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConferenceConfigurationDataProviderTest {

    private ConferenceConfigurationDataProvider provider = new ConferenceConfigurationDataProvider();

    @Test
    public void testParseOverrideFragment() {
        String fragment = "{\"domain\":\"sip.anotherdomain.com\",\"port\":\"5161\",\"timeout\":10}";
        Map<String, String> result = provider.parseOverrideFragment(fragment);
        assertNotNull(result);
        assertEquals(3, result.size());
    }
}

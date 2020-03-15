package com.voverc.provisioning.dataprovider;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeskConfigurationDataProviderTest {

    private DeskConfigurationDataProvider provider = new DeskConfigurationDataProvider();

    @Test
    public void testParseOverrideFragment() {
        String fragment = "port=5161\n" +
                "codecs=G561\n" +
                "timeout=10\n";
        Map<String, String> result = provider.parseOverrideFragment(fragment);
        assertNotNull(result);
        assertEquals(3, result.size());
    }
}

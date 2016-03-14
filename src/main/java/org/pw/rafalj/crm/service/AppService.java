package org.pw.rafalj.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rjozwiak on 2016-01-12.
 */
@Service
public class AppService {

    Logger log = LoggerFactory.getLogger(AppService.this.getClass());

    public Map<String, String> getAppProperties() throws IOException {
        Map<String, String> appProperties = new HashMap<String, String>();

        return appProperties;
    }
}

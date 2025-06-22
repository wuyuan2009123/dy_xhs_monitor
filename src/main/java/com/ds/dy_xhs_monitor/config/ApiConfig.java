package com.ds.dy_xhs_monitor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class ApiConfig {
    private String parseMainApi;
    private String zpUrl;
    private String proxyUrl;
    private String zpCountUrl;
    private String xhsUrl;
    private String appToken;
    private String sendMessageUrl;
}

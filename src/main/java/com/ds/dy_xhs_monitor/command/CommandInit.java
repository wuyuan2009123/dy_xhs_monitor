package com.ds.dy_xhs_monitor.command;

import com.ds.dy_xhs_monitor.config.ApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandInit implements CommandLineRunner {

    private final ApiConfig apiConfig;

    @Override
    public void run(String... args) throws Exception {
        String zpUrl = apiConfig.getZpUrl();
        String parseMainApi = apiConfig.getParseMainApi();
        log.info("init@@@@@@@@@@@@@@@@@ zpUrl:{},parseMainApi:{}", zpUrl, parseMainApi);
    }
}

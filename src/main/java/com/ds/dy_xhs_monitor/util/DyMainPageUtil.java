package com.ds.dy_xhs_monitor.util;

import com.ds.dy_xhs_monitor.config.ApiConfig;
import com.ds.dy_xhs_monitor.response.ZpCountRespVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DyMainPageUtil {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;


    public String getZpCountForPlay(String msId) {
        String zpCountUrl = apiConfig.getZpCountUrl();
        String url = zpCountUrl + msId;
        ZpCountRespVo forObject = restTemplate.getForObject(url, ZpCountRespVo.class);
        Integer awesomeCount = forObject.getData().getUser().getAwemeCount();
        return String.valueOf(awesomeCount);
    }
}

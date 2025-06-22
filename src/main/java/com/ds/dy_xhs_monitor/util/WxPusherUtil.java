package com.ds.dy_xhs_monitor.util;


import com.ds.dy_xhs_monitor.config.ApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class WxPusherUtil {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    public Boolean sendMessage(String nickName, String url, Set<String> pushIds){
        String appToken = apiConfig.getAppToken();
        String sendMessageUrl = apiConfig.getSendMessageUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestBody = buildReqMap(url, appToken,nickName,pushIds);
        // 封装请求
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(sendMessageUrl, requestEntity, String.class);
        String body = response.getBody();
        log.info("nickName:{},url:{},send status:{},response body:{}",nickName,url,response.getStatusCode().value(),body);
        return response.getStatusCode().is2xxSuccessful();
    }

    private Map<String, Object> buildReqMap(String url, String appToken,String nickName,Set<String> pushIds) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("appToken", appToken);
        requestBody.put("content", getPlatForm(url) +" 的 " + nickName+" 有更新了,url:"+url);
        requestBody.put("contentType", 1);
        requestBody.put("verifyPay",false);
        //        requestBody.put("uids", List.of("UID_yTgSa4F9muqL4zw6FwWY6yNeYKwx"));
        requestBody.put("uids", pushIds);
        requestBody.put("url", url);
        return requestBody;
    }

    private static String getPlatForm(String url){
        if (StringUtils.hasText(url) && (url.contains(".xiaohongshu.com") || url.contains("xhs"))){
            return "红薯";
        }
        if (StringUtils.hasText(url) && url.contains(".douyin.com")){
            return "斗因";
        }
        return "未知";
    }


}

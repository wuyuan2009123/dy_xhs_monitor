package com.ds.dy_xhs_monitor.util;

import com.ds.dy_xhs_monitor.config.ApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class HongShuPageUtil {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    public Proxy getProxyIp() {
        String proxyResponse = restTemplate.getForObject(apiConfig.getProxyUrl(), String.class);
        String[] parts = proxyResponse.split(":");
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);
        // 设置代理
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }

    @SneakyThrows
    public Map<String, String> getLastTitle(String msId) {
        String xhsUrl = apiConfig.getXhsUrl();
        String url = xhsUrl + msId;
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        headers.put("Accept-Language", "en-US,en;q=0.9");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Connection", "keep-alive");
        Document document = Jsoup.connect(url)
                .proxy(getProxyIp())
                .timeout(10000) // 设置连接和读取超时时间为 10 秒
                .headers(headers).get();
        Elements nickNameElements = document.select("#userPageContainer > div.user > div > div.info-part > div.info > div.basic-info > div.user-basic > div.user-nickname > div");
        String nickName = nickNameElements.get(0).text().trim();
        Elements noteElements = document.select("#userPostedFeeds > section");
        int limit = Math.min(noteElements.size(), 8); // 获取最多前8个
        List<String> titleList =  new ArrayList<>(limit);
        if (noteElements.size() > 0) {
            for (int i = 0; i < limit; i++) {
                String selector = String.format("#userPostedFeeds > section:nth-child(%d) > div > div > a > span", i + 1);
                Element spanElement = document.selectFirst(selector);
                if (spanElement != null) {
                    String trim = EmojiRemoverUtil.removeEmojiAndSpecialChars(spanElement.text().trim());
                    titleList.add(trim);
                    log.info("selector:{},spanElement.html():{}",selector,trim);
                }
            }
            Collections.sort(titleList);
            String join = String.join(",", titleList);
            log.info("titleList:{},join:{}",titleList,join);
            return Map.of("nickName", nickName, "lastTitle", join);

        }
        return Map.of("nickName", "N", "lastTitle", "N");
    }




}

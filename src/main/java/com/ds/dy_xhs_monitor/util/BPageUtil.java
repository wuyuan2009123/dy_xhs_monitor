package com.ds.dy_xhs_monitor.util;

import com.ds.dy_xhs_monitor.config.ApiConfig;
import com.ds.dy_xhs_monitor.response.BiBiResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BPageUtil {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;


    public String getLastTitle(String msId) {
        String bibiUrl = apiConfig.getBibiUrl();
        String url = bibiUrl+"1588698599";
        BiBiResponseVo forObject = restTemplate.getForObject(url, BiBiResponseVo.class);
        BiBiResponseVo.OBJ obj = Optional.of(forObject).map(BiBiResponseVo::getData).map(BiBiResponseVo.DataRes::getData).map(BiBiResponseVo.D2::getList).orElse(new BiBiResponseVo.OBJ());
        if (Objects.nonNull(obj.getVlist()) && obj.getVlist().size()>0) {
            BiBiResponseVo.Vlist vlist = obj.getVlist().get(0);
            return vlist.getTitle();
        }else {
            return "N";
        }
    }

//    public static void main(String[] args) throws IOException {
//        String baseUrl = "https://api.douyin.wtf/api/bilibili/web/fetch_user_post_videos?pn=1&uid=";
//        String url = baseUrl+"1588698599";
//        Map<String, String> headers = new HashMap<>();
//        headers.put("accept", "application/json");
//        RestTemplate restTemplate = new RestTemplate();
//        BiBiResponseVo forObject = restTemplate.getForObject(url, BiBiResponseVo.class);
//        BiBiResponseVo.OBJ obj = Optional.of(forObject).map(BiBiResponseVo::getData).map(BiBiResponseVo.DataRes::getData).map(BiBiResponseVo.D2::getList).orElse(new BiBiResponseVo.OBJ());
//        if (Objects.nonNull(obj.getVlist()) && obj.getVlist().size()>0) {
//            BiBiResponseVo.Vlist vlist = obj.getVlist().get(0);
//            System.out.println(vlist.getTitle());
//        }else {
//            System.out.println("N");
//        }
//
//
//    }
}

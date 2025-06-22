package com.ds.dy_xhs_monitor.provider;

import cn.hutool.json.JSONUtil;
import com.ds.dy_xhs_monitor.config.ApiConfig;
import com.ds.dy_xhs_monitor.entity.MonitorUser;
import com.ds.dy_xhs_monitor.response.MainResponseVo;
import com.ds.dy_xhs_monitor.util.DyMainPageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DouyinProvider implements ParseProvider {

    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;
    private final DyMainPageUtil dyMainPageUtil;

    @Override
    public MonitorUser getUserINfo(String url) {
        ResponseEntity<MainResponseVo> forEntity = restTemplate.getForEntity(apiConfig.getParseMainApi() + url, MainResponseVo.class);
        MainResponseVo body = forEntity.getBody();
        log.info("get main info:{}", JSONUtil.toJsonPrettyStr(body));
        MonitorUser monitorUser = new MonitorUser();
        monitorUser.setUid(body.getData().getUid());
        monitorUser.setParseUrl(url);
        monitorUser.setNickName(body.getData().getNickname());
        monitorUser.setMsId(body.getData().getId());
        monitorUser.setCreatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        String zpCount = dyMainPageUtil.getZpCountForPlay(body.getData().getId());
        log.info("get main zpCount :{}", zpCount);
        monitorUser.setZpCount(zpCount);
        return monitorUser;
    }

    @Override
    public String getZpCountFor(String msId) {
        return dyMainPageUtil.getZpCountForPlay(msId);
    }

    @Override
    public boolean supper(String url) {
        return StringUtils.hasText(url) && url.contains(".douyin.com");
    }
}

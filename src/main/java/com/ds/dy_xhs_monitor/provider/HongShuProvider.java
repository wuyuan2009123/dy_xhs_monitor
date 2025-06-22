package com.ds.dy_xhs_monitor.provider;

import com.ds.dy_xhs_monitor.entity.MonitorUser;
import com.ds.dy_xhs_monitor.util.HongShuPageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HongShuProvider implements ParseProvider {

    private final HongShuPageUtil hongShuPageUtil;

    @Override
    public MonitorUser getUserINfo(String url) {
        MonitorUser monitorUser = new MonitorUser();
        String msId = getLastPartAfterSlash(url);
        monitorUser.setUid(msId);
        monitorUser.setParseUrl(url);
        monitorUser.setMsId(msId);
        monitorUser.setCreatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        Map<String, String> map = hongShuPageUtil.getLastTitle(msId);
        monitorUser.setNickName(map.get("nickName"));
        log.info("get main zpCount :{}", map.get("lastTitle"));
        monitorUser.setZpCount(map.get("lastTitle"));
        return monitorUser;
    }

    @Override
    public boolean supper(String url) {
        return StringUtils.hasText(url) && (url.contains(".xiaohongshu.com") || url.contains("xhs"));
    }

    @Override
    public String getZpCountFor(String msId) {
        Map<String, String> map = hongShuPageUtil.getLastTitle(msId);
        return map.get("lastTitle");
    }

    public static String getLastPartAfterSlash(String url) {
        if (url == null || url.isEmpty()) {
            return "";
        }

        int lastIndex = url.lastIndexOf('/');
        if (lastIndex != -1 && lastIndex < url.length() - 1) {
            return url.substring(lastIndex + 1);
        } else {
            return "";
        }
    }
}

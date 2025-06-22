package com.ds.dy_xhs_monitor.controller;

import cn.hutool.json.JSONUtil;
import com.ds.dy_xhs_monitor.entity.MonitorUser;
import com.ds.dy_xhs_monitor.entity.User;
import com.ds.dy_xhs_monitor.mapper.MonitorUserMapper;
import com.ds.dy_xhs_monitor.mapper.UserMapper;
import com.ds.dy_xhs_monitor.provider.ParseProvider;
import com.ds.dy_xhs_monitor.util.WxPusherUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final MonitorUserMapper monitorUserMapper;
    private final UserMapper userMapper;
    private final WxPusherUtil wxPusherUtil;
    private final ObjectProvider<ParseProvider> parseProviders;


    @GetMapping("/add/monitor/url")
    public String addMonitorUrl(@RequestParam(value = "url") String url) {
        log.info("addMonitorUrl:{}", url);
        Optional<ParseProvider> first = parseProviders.stream().filter(it -> it.supper(url)).findFirst();
        if (first.isPresent()) {
            ParseProvider parseProvider = first.get();
            log.info("add url,support :{}", parseProvider.getClass().getName());
            MonitorUser userINfo = parseProvider.getUserINfo(url);
            monitorUserMapper.save(userINfo);
            return "添加成功:" + JSONUtil.toJsonPrettyStr(userINfo);
        } else {
            log.info("not support ,url:{}", url);
            return "添加失败: 暂时不支持此平台";
        }
    }

    @GetMapping("/add/push/id")
    public String addPushId(@RequestParam(value = "uid") String uid) {
        log.info("addPushId:{}", uid);
        User user = new User();
        user.setPushId(uid);
        user.setCreatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        user.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        User save = userMapper.save(user);
        return "email 添加成功:" + save.getPushId();
    }

    @GetMapping("/users")
    public List<MonitorUser> users() {
        return monitorUserMapper.findAll();
    }


    @GetMapping("/exe")
    public String batch() {
        List<MonitorUser> all = monitorUserMapper.findAll();
        Set<String> pushIds = userMapper.findAll().stream().map(User::getPushId).collect(Collectors.toSet());
        for (MonitorUser monitorUser : all) {
            String msId = monitorUser.getMsId();
            String parseUrl = monitorUser.getParseUrl();
            log.info("zpUrl:{}", parseUrl);
            String dbZpCount = monitorUser.getZpCount();
            try {
                ParseProvider first = parseProviders.stream().filter(it -> it.supper(monitorUser.getParseUrl())).findFirst().orElseThrow();
                String zpCount = first.getZpCountFor(msId);
                log.info("new page zpCount:{},db zpCount:{}, new page hash:{},db zpCount hash:{}",
                        zpCount, dbZpCount,zpCount.hashCode(), dbZpCount.hashCode());
                if (!zpCount.equals(dbZpCount)) {
                    //推送消息
                    Boolean pushBoolean = wxPusherUtil.sendMessage(monitorUser.getNickName(), monitorUser.getParseUrl(), pushIds);
                    log.info(monitorUser.getNickName() + " 推送 是否成功：{}", pushBoolean);

                    monitorUser.setZpCount(zpCount);
                    monitorUser.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
                    monitorUserMapper.save(monitorUser);
                }
            } catch (Exception ex) {
                log.error(parseUrl + ":" + ex.getMessage());
            }
        }
        return "OK";
    }


}

package com.ds.dy_xhs_monitor.controller;

import com.ds.dy_xhs_monitor.config.ApiConfig;
import com.ds.dy_xhs_monitor.entity.MonitorUser;
import com.ds.dy_xhs_monitor.mapper.MonitorUserMapper;
import com.ds.dy_xhs_monitor.response.QsyResponseDataVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final MonitorUserMapper monitorUserMapper;
    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<MonitorUser> users = monitorUserMapper.findAll();
        model.addAttribute("monitorUsers", users);
        return "index"; // 返回 index.html 视图
    }

    @GetMapping("/qsy")
    @ResponseBody
    public ResponseEntity<QsyResponseDataVo> qsy(String  url){
        String qsyUrl = apiConfig.getQsyUrl();
        QsyResponseDataVo forObject = restTemplate.getForObject(qsyUrl+url, QsyResponseDataVo.class);
        return ResponseEntity.ok(forObject);
    }
}

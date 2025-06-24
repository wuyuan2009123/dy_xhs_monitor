package com.ds.dy_xhs_monitor.controller;

import com.ds.dy_xhs_monitor.entity.MonitorUser;
import com.ds.dy_xhs_monitor.mapper.MonitorUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final MonitorUserMapper monitorUserMapper;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<MonitorUser> users = monitorUserMapper.findAll();
        model.addAttribute("monitorUsers", users);
        return "index"; // 返回 index.html 视图
    }
}

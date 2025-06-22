package com.ds.dy_xhs_monitor.provider;

import com.ds.dy_xhs_monitor.entity.MonitorUser;

public interface ParseProvider {
    MonitorUser getUserINfo(String url);

    boolean supper(String url);

    default String getZpCountFor(String msId){
        return "0";
    }
}

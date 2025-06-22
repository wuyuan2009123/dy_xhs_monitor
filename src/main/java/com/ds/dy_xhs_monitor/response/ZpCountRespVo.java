package com.ds.dy_xhs_monitor.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZpCountRespVo {
    private Integer code;
    private String router;
    private DataRes data; // 修改为 List 类型

    @Data
    public static class DataRes {
        private User user;
    }


    @Data
    public static class User {
        @JsonProperty("aweme_count")
        private Integer awemeCount;
    }

}

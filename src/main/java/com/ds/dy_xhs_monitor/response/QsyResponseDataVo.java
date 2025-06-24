package com.ds.dy_xhs_monitor.response;


import lombok.Data;

@Data
public class QsyResponseDataVo {
    private String msg;
    private int code;
    private DataInfoWrapper data;

    @Data
    public static class DataInfoWrapper {
        private DataInfo data_info;
        private int surplus_number;
        private String apiType;
    }
    @Data
    public static class DataInfo {
        private String msg;
        private String img;
        private int code;
        private String title;
        private String pics;
        private String url;
    }
}

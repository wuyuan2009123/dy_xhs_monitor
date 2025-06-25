package com.ds.dy_xhs_monitor.response;


import lombok.Data;

@Data
public class QsyResponseData2Vo {
    private boolean success;
    private DataInfoWrapper data;
//    private String tips;
    private String time;

    @Data
    public static class DataInfoWrapper {
        private String video_title;
        private String video_url;
        private String download_url;
        private String image_url;
    }
}

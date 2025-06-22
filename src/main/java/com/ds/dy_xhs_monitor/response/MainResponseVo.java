package com.ds.dy_xhs_monitor.response;

import lombok.Data;

@Data
public class MainResponseVo {
    private Integer code;
    private String msg;
    private String secUid;
    private DataRes data;

    @Data
    public static class DataRes {
        private String id;
        private String nickname;
        private String enterpriseVerifyReason;
        private String uid;
        private String uniqueId;
        private String avatar;
        private String avatarSmall;
        private String shortId;
        private String signature;
    }
}

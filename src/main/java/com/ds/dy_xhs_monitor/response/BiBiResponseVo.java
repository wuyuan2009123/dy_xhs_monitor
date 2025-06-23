package com.ds.dy_xhs_monitor.response;

import lombok.Data;

import java.util.List;

@Data
public class BiBiResponseVo {
    private Integer code;
    private String router;

    private DataRes data; // 修改为 List 类型


    @Data
    public static class DataRes {
        private D2 data;
    }

    @Data
    public static class D2 {
        private OBJ  list;
    }

    @Data
    public static class OBJ {
        private List<Vlist>  vlist;
    }

    @Data
    public static class Vlist {
        private String title;
        private Integer comment;
        private String author;
    }

}

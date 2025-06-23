package com.ds.dy_xhs_monitor.util;

public class PathUtil {
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

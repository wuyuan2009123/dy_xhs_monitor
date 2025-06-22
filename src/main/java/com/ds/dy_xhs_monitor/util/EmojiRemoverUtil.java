package com.ds.dy_xhs_monitor.util;


public class EmojiRemoverUtil {
    public static String removeEmojiAndSpecialChars(String input) {
        return input.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");

    }

}

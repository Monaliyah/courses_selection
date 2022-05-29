package com.me.courses_selection.utils;

import java.util.UUID;

/**
 *
 * June
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }
}

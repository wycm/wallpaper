package com.wy.wallpaper.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by wy on 12/3/2016.
 */
public class Config {
    public static final Properties properties = new Properties();
    static {
        try {
            properties.load(Config.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

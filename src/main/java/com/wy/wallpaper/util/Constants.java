package com.wy.wallpaper.util;

/**
 * Created by yang.wang on 11/21/16.
 */
public class Constants {
    /**
     * 必应壁纸首页，用于获取壁纸地址
     */
    public static final String BING_INDEX = "http://www.bing.com";
    /**
     * 提取img地址的regex
     */
    public static final String IMG_REGEX = "g_img=(\\{.*?\\});";
    /**
     * 测试地址
     */
    public static final String TEST_IMG_URL = "http://www.bing.com/az/hprichbg/rb/BlackchurchRock_ROW10941808214_1920x1080.jpg";
    /**
     * 最近18天壁纸信息
     * 最多只能返回18天的壁纸信息
     */
    public static final String BING_IMGS_URL = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=16&n=2";

    public static final String PROJECT_DIR = "/wallpaper";

    public static final String BING_DAILY_WALLPAPER_DIR = "/bing-daily-wallpaper";

    public static final String DEFAULT_WALLPAPER_NAME = "default.jpg";

    public static final int SCREEN_WIDTH = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);

    public static final int SCREEN_HEIGHT = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);

    public static final String USER_HOME = System.getProperty("user.home");
}

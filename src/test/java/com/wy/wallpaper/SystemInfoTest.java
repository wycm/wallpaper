package com.wy.wallpaper;

import org.junit.Test;

/**
 * Created by yang.wang on 11/21/16.
 */
public class SystemInfoTest {
    @Test
    public void getSysInfo(){
        System.out.println(System.getProperty("os.name"));
    }
}

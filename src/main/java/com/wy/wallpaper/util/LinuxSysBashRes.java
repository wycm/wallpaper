package com.wy.wallpaper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yang.wang on 11/21/16.
 */
public class LinuxSysBashRes {
    public static String runCommand(String command){
        StringBuilder sb = new StringBuilder();
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));


        String s = "";
        try {
            while((s = reader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

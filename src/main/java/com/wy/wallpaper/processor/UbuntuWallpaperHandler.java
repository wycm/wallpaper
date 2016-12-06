package com.wy.wallpaper.processor;

import com.wy.wallpaper.util.Config;
import com.wy.wallpaper.util.FileUtils;
import com.wy.wallpaper.util.LinuxSysBashRes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang.wang on 11/21/16.
 * ubuntu设置壁纸
 * gsettings set org.gnome.desktop.background picture-uri file:///home/serrano/Pictures/y.jpg
 */
public class UbuntuWallpaperHandler extends SimpleWallpaperHandler {
    @Override
    public void setWallpaper(String path) {
        String command = "gsettings set org.gnome.desktop.background picture-uri file://" + path;
        LinuxSysBashRes.runCommand(command);
    }

    @Override
    public void createScript() {
        String scriptContent = Config.properties.getProperty("ubuntuScript");
        scriptContent = scriptContent.replaceAll("\\$\\{jarAbsolutePath\\}", FileUtils.getJARPath());
        File file = new File("/etc/rc.local");
        try {
            List<String> list = new ArrayList<String>();
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = bf.readLine()) != null){
                if(temp.contains("exit 0")){
                    list.add(scriptContent);
                }
                list.add(temp);
            }
            bf.close();
            FileWriter fw = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fw, false);
            for(String s : list){
                pw.write(s);
                pw.write("\r\n");
            }
            fw.flush();
            pw.flush();
            fw.close();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteScript() {

    }

    @Override
    public boolean scriptExists() {
        return false;
    }
}

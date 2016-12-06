package com.wy.wallpaper.processor;

import com.wy.wallpaper.util.Config;
import com.wy.wallpaper.util.FileUtils;
import com.wy.wallpaper.util.LinuxSysBashRes;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yang.wang on 11/21/16.
 * ubuntu设置壁纸
 * gsettings set org.gnome.desktop.background picture-uri file:///home/serrano/Pictures/y.jpg
 */
public class UbuntuWallpaperHandler extends SimpleWallpaperHandler {
    private File scriptFile = new File("/etc/rc.local");
    @Override
    public void setWallpaper(String path) {
        String command = "gsettings set org.gnome.desktop.background picture-uri file://" + path;
        LinuxSysBashRes.runCommand(command);
    }

    /**
     * 在 /etc/rc.local中添加命令
     */
    @Override
    public void createScript() {
        String scriptContent = Config.properties.getProperty("ubuntuScript");
        scriptContent = scriptContent.replaceAll("\\$\\{jarAbsolutePath\\}", FileUtils.getJARPath());
        try {
            List<String> list = new ArrayList<String>();
            BufferedReader bf = new BufferedReader(new FileReader(scriptFile));
            String temp = null;
            while ((temp = bf.readLine()) != null){
                if(temp.contains("exit 0")){
                    list.add(scriptContent);
                }
                list.add(temp);
            }
            bf.close();
            printFile(list, scriptFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteScript() {
        List<String> list = getFileContent();
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).contains("setTodayBingWallpaper")){
                list.remove(i);
                i--;
            }
        }
        printFile(list, scriptFile);
    }

    @Override
    public boolean scriptExists() {
        List<String> list = getFileContent();
        for(String s : list){
            if(s.contains("setTodayBingWallpaper")){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取 /etc/rc.local中的内容
     * @return
     */
    private List<String> getFileContent(){
        List<String> list = new LinkedList<String>();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(scriptFile));
            String temp = null;
            while ((temp = bf.readLine()) != null){
                list.add(temp);
            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * list的内容按行输出至文件
     * @param list
     * @param file
     */
    private void printFile(List<String> list, File file){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fw, false);
            for(String s : list){
                pw.write(s);
                pw.write("\r\n");
            }
            fw.flush();
            pw.flush();
            fw.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

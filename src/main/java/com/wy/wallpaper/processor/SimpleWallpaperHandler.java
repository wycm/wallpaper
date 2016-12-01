package com.wy.wallpaper.processor;

import com.wy.wallpaper.entity.BingImage;
import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.util.Constants;
import com.wy.wallpaper.util.FileUtils;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yang.wang on 11/21/16.
 */
public abstract class SimpleWallpaperHandler implements WallpaperHandler{
    private volatile boolean todayWPExist = false;
    /**
     * 必应每日壁纸
     * 直接下载
     * @param bingHttpClient
     */
    @Override
    public void setTodayBingWallpaper(BingHttpClient bingHttpClient){
        String url = bingHttpClient.getTodayWallpaperUrl();
        String path = getBingWallpaperPath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(Calendar.getInstance().getTime());
        String fileName = date + ".jpg";
        String filePath = path + fileName;
        bingHttpClient.downloadFile(url, path, fileName, false);
        setWallpaper(filePath);
    }

    /**
     * @param bingHttpClient
     */
    @Override
    public void downloadWallpaperToLocal(BingHttpClient bingHttpClient){
        String path = getBingWallpaperPath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        //最近10天的壁纸都存在情况下，不更新壁纸
        boolean downloadFlag = false;
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            String date = sdf.format(calendar.getTime());
            String filePath = getBingWallpaperPath() + date + ".jpg";
            if (!FileUtils.isExistFile(filePath)){
                downloadFlag = true;
                break;
            }
        }
        if(!downloadFlag){
            todayWPExist = true;
            return;
        }
        Map<String, BingImage> map = bingHttpClient.getLatestWallpaperInfo();
        List<String> list = new ArrayList<String>(map.size());
        list.addAll(map.keySet());
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for(String date : list){
            String fileName = date + ".jpg";
            String filePath = path + fileName;
            if(!FileUtils.isExistFile(filePath)){
                bingHttpClient.downloadFile(map.get(date).getUrl(),path, fileName, false);
                todayWPExist = true;
            }
        }
    }
    private String getBingWallpaperPath(){
        String userHome = System.getProperty("user.home");
        String path = userHome + Constants.PROJECT_DIR + Constants.BING_DAILY_WALLPAPER_DIR + "/";
        return path;
    }

    /**
     * 获取必应
     * 今日的壁纸
     * @return
     */
    @Override
    public Image getBingTodayImage(){
        Image image = null;
        try {
            image = new Image(new FileInputStream(new File(getBingTodayImgFilePath())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }
    public String getBingTodayImgFilePath(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String todayDate = sdf.format(calendar.getTime());
        String filePath = Constants.USER_HOME
                + Constants.PROJECT_DIR
                + Constants.BING_DAILY_WALLPAPER_DIR
                + "/" + todayDate + ".jpg";
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!todayWPExist){
                continue;
            }
            break;
        }
        return filePath;
    }
}

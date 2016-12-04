package com.wy.wallpaper.http.bing;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wy.wallpaper.entity.BingImage;
import com.wy.wallpaper.entity.Page;
import com.wy.wallpaper.http.HttpClient;
import com.wy.wallpaper.util.Constants;
import com.wy.wallpaper.util.HttpClientUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yang.wang on 11/21/16.
 */
public class BingHttpClient extends HttpClient {
    private Map<String, BingImage> latestBingImageInfoMap = null;
    @Override
    public void initHttpClient() {
    }

    /**
     * 根据bing首页获取最新壁纸的url
     * @return
     */
    public String getTodayWallpaperUrl(){
        Page page = getWebPage(Constants.BING_INDEX);
        Pattern pattern = Pattern.compile(Constants.IMG_REGEX);
        Matcher matcher = pattern.matcher(page.getHtml());
        String url = null;
        if (matcher.find()){
            String json = matcher.group(1);
            JSONObject jsonObject = JSON.parseObject(json);
            url = jsonObject.get("url").toString();
            if(!url.startsWith("http")){
                url = Constants.BING_INDEX + url;
            }
            return url;
        }
        return null;
    }

    /**
     * 获取最新壁纸
     * @return
     */
    public BingImage getLatestWallpaper(){
        Map<String, BingImage> map = getLatestWallpaperInfo();
        List<String> list = new ArrayList<String>(map.size());
        list.addAll(map.keySet());
        Collections.sort(list);
        return map.get(list.get(list.size() - 1));
    }
    /**
     * 获取最近几天必应壁纸信息
     * @return map<日期(yyyyMMdd), 当天壁纸信息>
     */
    public Map<String, BingImage> getLatestWallpaperInfo(){
        if(latestBingImageInfoMap != null){
            return latestBingImageInfoMap;
        }
        Map<String, BingImage> map = new HashMap<String, BingImage>();
        Page page = getWebPage(Constants.BING_IMGS_URL);
        String json = page.getHtml();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray images = jsonObject.getJSONArray("images");
        Object[] bingImages =  images.toArray();
        for(Object t : bingImages){
            BingImage bi = JSON.toJavaObject((JSONObject) t, BingImage.class);
            String url = bi.getUrl();
            if(!url.startsWith("http")){
                url = Constants.BING_INDEX + url;
            }
            bi.setUrl(url);
            map.put(bi.getStartdate(), bi);
        }
        latestBingImageInfoMap = map;
        return map;
    }
}

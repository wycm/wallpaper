package com.wy.wallpaper;

import com.wy.wallpaper.util.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yang.wang on 11/21/16.
 */
public class HttpTest {
    @Test
    public void testDownload() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://www.bing.com/az/hprichbg/rb/BlackchurchRock_ROW10941808214_1920x1080.jpg");
        CloseableHttpResponse response = httpClient.execute(request);
        InputStream is = response.getEntity().getContent();
        FileUtils.inputStreamToFile(is, "/wy/1.jpg");
    }
}

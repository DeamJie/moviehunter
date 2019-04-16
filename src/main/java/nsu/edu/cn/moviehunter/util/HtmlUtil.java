package nsu.edu.cn.moviehunter.util;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Repository
public class HtmlUtil {
    public static String getHtml(String urlstring) throws IOException {
        //得到地址
        URL url = new URL(urlstring);
        //建立连接
        URLConnection conn =  url.openConnection();
        conn.setConnectTimeout(5000);
        //获得数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
        StringBuilder html = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            html.append(line);
        }
        bufferedReader.close();
        return html.toString();
    }
}

package nsu.edu.cn.moviehunter.remover;


import nsu.edu.cn.moviehunter.util.HtmlUtil;
import nsu.edu.cn.moviehunter.util.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class MovieDataHunter {

    @Autowired
    HtmlUtil htmlUtil;
    public Movie getData(String s,int typeId) {
        String html = null;
        try {
            html = htmlUtil.getHtml(s);
        } catch (IOException e) {
            System.out.println("URL错误"+e.getMessage());
        }
        if (html==null){
            return null;
        }else {
            Document doc = Jsoup.parse(html);
            Elements tables = doc.select("div#Zoom");//class等于tbspan的table标签
            Elements links = tables.select("p"); //带有href属性的a元素
            Element e = links.first();
            Movie movie = new Movie();
            getDownload(doc,movie);
            getImg(e,movie);
            movie.setLabelid(typeId);
            packingMovie(e,movie);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return movie;
        }
    }
    //获取图片
    public void getImg(Element e,Movie movie){
        try{
        Elements imgs = e.select("img");
            String[] datas = e.toString().split("<br>");
            movie.setImg(imgs.first().attr("src"));}
            catch (Exception ex){

            }

    }

    public void getDownload(Document document,Movie movie){
        try {
            String s = document.select("table").select("tbody").select("tr").select("a[href]").first().attr("href");
            movie.setDownload(s);
        }catch (Exception exc){

        }

    }
    //把所有的信息包装为movie对象
    public void packingMovie(Element e,Movie movie){
        try {
            String[] sss = e.toString().split("<br>");
            for(int i=0;i<sss.length;i++) {
                if (sss[i].contains("◎简　　介")) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = i + 2; j < sss.length; j++) {
                        if (sss[j].contains("img") || sss[j].contains("◎")) break;
                        sb.append(sss[j]);
                    }
                    String s = sb.toString();
                    movie.setIntro(s.replaceAll(" ", ""));
                } else if (sss[i].contains("◎")) {
                    if (sss[i].contains("◎译　　名")) {
                        movie.setTranslationname(sss[i].substring(5));
                    }
                    if (sss[i].contains("◎片　　名")) {
                        movie.setName(sss[i].substring(5));
                    }
                    if (sss[i].contains("◎年　　代")) {
                        movie.setTime(Integer.valueOf(sss[i].substring(5).trim().replaceAll("　", "")));
                    }
                    if (sss[i].contains("◎国　　家") || sss[i].contains("◎产　　地")) {
                        movie.setCountry(sss[i].substring(5));
                    }
                    if (sss[i].contains("◎豆瓣评分")) {
                        movie.setScore(Double.valueOf(sss[i].substring(5).split("/")[0].replaceAll("　", "")));

                    }
                    if (sss[i].contains("◎IMDb评分")) {
                        movie.setScore(Double.valueOf(sss[i].substring(14).split("/")[0]));
                    }
                    if (sss[i].contains("◎IMDB评分")) {
                        movie.setScore(Double.valueOf(sss[i].substring(8).split("/")[0]));
                    }
                }
            }
            }catch (Exception exception){
        }
    }
}

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 从80的电影详情界面上获取电影的信息
     * @param s
     * @return
     */
    public Movie getMovieDataFrom80s(String s,int typeId){
        Movie movie = new Movie();
        try{ String html = "";
            movie = new Movie();
            try {
                html = htmlUtil.getHtml(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(html == null){

            }else {
                Elements tables = Jsoup.parse(html).select("span.span_block");
                Elements tables1 = Jsoup.parse(html).select("div[style=float:left; margin-right:10px;]");
                Elements tables2 = Jsoup.parse(html).select("h1.font14w");
                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(tables2.text());
                movie.setTime(Integer.valueOf(m.replaceAll("").trim()));
                if(tables1.text().indexOf("豆瓣评分：") != -1){
                    movie.setScore(Double.valueOf(tables1.text().replaceAll("豆瓣评分： ","")));}
                for (Element e : tables){
                    System.out.println(e.toString());
                    if(e.text().contains("类型：")){
                        movie.setLabelid(typeId);

                    }
                    if(e.text().contains("地区：")){
                        movie.setCountry(e.text().split("：")[1].trim());
                        System.out.println(e.text().split("：")[1].trim());
                    }
                    if(e.text().contains("上映日期：")){
                        movie.setTime(Integer.valueOf(e.text().split("：")[1].trim().substring(0,4)));
                        System.out.println(e.text().split("：")[1].trim().substring(0,4));
                    }
                }

                Elements nameAndImg = Jsoup.parse(html).select("div.img").select("img");
                for (Element e : nameAndImg){
                    movie.setImg(e.attr("src"));
                    System.out.println("img:"+e.attr("src"));
                    movie.setName(e.attr("title"));
                    System.out.println("name:"+e.attr("title"));
                }

                Elements introduce = Jsoup.parse(html).select("p#movie_content_all");
                movie.setIntro(introduce.text());
                System.out.println(introduce.text());

                Elements download = Jsoup.parse(html).select("form#myform").select("a[thunderpid]");
                movie.setDownload(download.first().attr("href"));
                System.out.println(download.first().attr("href"));
            }
            try {
                Thread.sleep(466);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return movie;

        }catch (Exception e){
         return  movie;
        }
    }
}

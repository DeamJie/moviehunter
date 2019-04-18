package nsu.edu.cn.moviehunter.hunter;



import nsu.edu.cn.moviehunter.basket.Basket;
import nsu.edu.cn.moviehunter.util.HtmlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MovieUrlHunter {
    @Autowired
    HtmlUtil htmlUtil;
    @Autowired
    Basket basket;
    public void getUrl(String s,String tableName) throws IOException {
        String html = htmlUtil.getHtml(s);
        String pre = "https://www.ygdy8.com";
        if (html==null){

        }else {
            Document doc = Jsoup.parse(html);
            Elements tables = doc.select("tr").select("td").select("b");//class等于tbspan的table标签
            for(Element e:tables){
                Elements links = e.select("a[href]");
                if(links.size()>=2){

                    basket.putUrl(pre+links.last().attr("href"),tableName);
                }else if(links.size()==1){

                    basket.putUrl(pre+links.last().attr("href"),tableName);
                }
            }
        }

    }
    public int getNum(String s) throws IOException {
        String html = htmlUtil.getHtml(s);
        if (html==null){
            return 0;
        }else {
            Document doc = Jsoup.parse(html);
            Elements tables = doc.select("tr");
            String string = tables.select("td").select("a").last().attr("href");
            String[] page = string.split("&");
            return Integer.valueOf(page[page.length-1].replace("PageNo=",""));
        }

    }

    /**
     * 得到80S网站某个网页里边全部的电影URL地址
     * @param s 网址
     * @throws IOException
     */
    public void getUrlFrom80s(String s) throws IOException {
        String html = htmlUtil.getHtml(s);
        String pre = "http://www.80s.la";
        if(html == null){

        }else {
            Document doc = Jsoup.parse(html);
            Elements tables = doc.select("ul.me1").select("li");
            Elements urls = tables.select("a[title]");
            for(Element e : urls){
                System.out.println(e.attr("href"));
            }
        }
    }

    /**
     * 获取80S网址某一个页面的页码数
     * @param s 网址
     * @return 具体的页码数量
     */
    public int getNumOf80S(String s){
        String html = null;
        try {
            html = htmlUtil.getHtml(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(html == null){
            return 0;
        }else {
            Document doc = Jsoup.parse(html);
            Elements tables = doc.select("div.pager").select("a[href]");
            String finalPageUrl = tables.last().attr("href");
            System.out.println(finalPageUrl);
            String[] pages = finalPageUrl.split("p");
            return  Integer.parseInt(pages[pages.length-1]);
        }
    }
}

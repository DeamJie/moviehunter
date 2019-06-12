package nsu.edu.cn.moviehunter.hunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@Scope("prototype")
public class HunterTask implements Runnable{
    @Autowired
    private MovieUrlHunter movieUrlHunter;
    private String startUrl;
    private int listName;
    public void setStartUrl(String startUrl,int typeId) {
        this.startUrl = startUrl;
        this.listName = typeId;
    }

    @Override
    public void run() {
        int j=20;
        try {
            j=movieUrlHunter.getNumOf80S(startUrl.replace("num",String.valueOf(1)));
            System.out.println(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=1;i<=j;i++){
            try {
               movieUrlHunter.getUrlFrom80s(startUrl.replace("num",String.valueOf(i)),listName+"");
               Thread.sleep(1000);
                System.out.println(i);
            } catch (IOException e) {
                System.out.println("URL错误"+e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("URL错误"+e.getMessage());
            }
        }
    }


}

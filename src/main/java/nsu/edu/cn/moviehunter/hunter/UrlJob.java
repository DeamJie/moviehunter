package nsu.edu.cn.moviehunter.hunter;

import nsu.edu.cn.moviehunter.util.Label;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UrlJob  implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MovieUrlHunter movieUrlHunter = new MovieUrlHunter();
        Label label = (Label) jobExecutionContext.getJobDetail().getJobDataMap().get("label");
        System.out.println("start");
        int j=20;
        try {
            j=movieUrlHunter.getNumOf80S(label.getStartUrl().replace("num",String.valueOf(1)));
            System.out.println(label.getName()+"总共有"+j+"页");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=1;i<=j;i++){
            try {
                movieUrlHunter.getUrlFrom80s(label.getStartUrl().replace("num",String.valueOf(i)),label.getId()+"");
                Thread.sleep(15000);
                System.out.println(label.getName()+"执行到"+i+"页");
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
        }
    }
}

package nsu.edu.cn.moviehunter.hunter;

import nsu.edu.cn.moviehunter.util.Label;
import nsu.edu.cn.moviehunter.util.SpringComponentUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;

public class UrlJob  implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MovieUrlHunter movieUrlHunter = (MovieUrlHunter) SpringComponentUtil.getBean("movieUrlHunter");
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
                System.out.println(label.getName()+"执行到"+i+"页");
                System.out.println("  ");
                Thread.sleep(15000);
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
        }
    }
}

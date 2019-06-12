package nsu.edu.cn.moviehunter.util;

import nsu.edu.cn.moviehunter.hunter.UrlJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuatzUtil {
    private final  String testCornString = "0 15 * * * ?";//15分钟时执行
    private final String cronString ="0 0 3 * * ?";//每天3点执行
    @Autowired
    private Scheduler scheduler;

    public  void startJob(Label label) throws SchedulerException {
        String name = label.getId().toString();
        String group = "label";
        JobDetail jobDetail = JobBuilder.newJob(UrlJob.class).withIdentity(name,group).build();
        jobDetail.getJobDataMap().put("label",label);
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(testCornString).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cron).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }
}

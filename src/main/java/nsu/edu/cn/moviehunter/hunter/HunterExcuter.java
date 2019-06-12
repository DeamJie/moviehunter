package nsu.edu.cn.moviehunter.hunter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 维护一个HunterTask的list从数据库的label表中获取标签的名称，
 * 每个标签生成一个huntertask
 */
@Repository
public class HunterExcuter implements InitializingBean {

    @Resource
    HunterTask hunterTask;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private ArrayList<String> startUrlList;
    @Override
    public void afterPropertiesSet(){
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        hunterTask.setStartUrl("http://www.80s.la/movie/list/9-----pnum",7);
    }
}

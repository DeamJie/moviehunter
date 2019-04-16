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
        hunterTask.setStartUrl("http://s.ygdy8.com/plus/so.php?keyword=%CF%B2%BE%E7&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=1382&num",1);
    }

    public void excut(){
        scheduledThreadPoolExecutor.execute(hunterTask);
    }

    public void init(){
        startUrlList = new ArrayList<>();
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%CF%B2%BE%E7&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=1382&num");
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%B0%AE%C7%E9&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=392&num");
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%B6%AF%D7%F7&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=1347&num");
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%BF%C6%BB%C3&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=429&num");
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%BF%D6%B2%C0&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=489&num");
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%D5%BD%D5%F9&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=101&num");
        startUrlList.add("http://s.ygdy8.com/plus/so.php?keyword=%D0%FC%D2%C9&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=427&num");
    }
}

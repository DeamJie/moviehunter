package nsu.edu.cn.moviehunter.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringComponentUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if(context == null){
            context = applicationContext;
        }
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }
}

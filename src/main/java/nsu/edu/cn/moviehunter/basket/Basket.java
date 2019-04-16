package nsu.edu.cn.moviehunter.basket;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Repository
public class Basket implements InitializingBean {
    private JedisPool jedisPool;
    public void putUrl(String url ,String listName){
        Jedis jedis = jedisPool.getResource();
        jedis.lpush(listName,url);
        if(jedis!=null){
            jedis.close();
        }
    }
    public List<String> getUrl(int timeOut, String listName){
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.brpop(timeOut,listName);
        if(jedis!=null){
            jedis.close();
        }
        System.out.println(jedis.llen(listName));
        return list;
    }
    @Override
    public void afterPropertiesSet(){
        jedisPool = new JedisPool("localhost", 6379);
    }


}

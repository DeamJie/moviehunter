package nsu.edu.cn.moviehunter.remover;

import nsu.edu.cn.moviehunter.basket.Basket;
import nsu.edu.cn.moviehunter.util.Movie;
import nsu.edu.cn.moviehunter.util.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class RemoverExcuter {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private Basket basket;
    @Autowired
    private MovieDataHunter movieDataHunter;
    public void removeToMySql(){
        while (true){
            ArrayList<String> url = (ArrayList<String>) basket.getUrl(200,"6");
            System.out.println(url.get(1));
            Movie movie = movieDataHunter.getData(url.get(1),6);
            if (movie!=null)
            movieMapper.insertMovie(movie);
        }

    }

}

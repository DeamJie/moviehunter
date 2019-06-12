package nsu.edu.cn.moviehunter.remover;

import nsu.edu.cn.moviehunter.basket.Basket;
import nsu.edu.cn.moviehunter.util.Movie;
import nsu.edu.cn.moviehunter.util.MovieMapper;
import nsu.edu.cn.moviehunter.util.SpringComponentUtil;

import java.util.ArrayList;
public class RemoverExcuter implements Runnable{
    private MovieMapper movieMapper;
    private Basket basket;
    private MovieDataHunter movieDataHunter;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    private int labelId;

    public RemoverExcuter(int labelId){
        this.labelId = labelId;
        movieMapper= (MovieMapper) SpringComponentUtil.getBean("movieMapper");
        basket = (Basket) SpringComponentUtil.getBean("basket");
        movieDataHunter = (MovieDataHunter) SpringComponentUtil.getBean("movieDataHunter");
    }

    @Override
    public void run() {
        while (true){
            ArrayList<String> url = (ArrayList<String>) basket.getUrl(2000,labelId+"");
            System.out.println("开始获取"+url.get(1)+"的信息");
            Movie movie = movieDataHunter.getMovieDataFrom80s(url.get(1),labelId);
            if (movie!=null) movieMapper.insertMovie(movie);
        }
    }
}

package nsu.edu.cn.moviehunter;

import nsu.edu.cn.moviehunter.basket.Basket;
import nsu.edu.cn.moviehunter.hunter.HunterExcuter;
import nsu.edu.cn.moviehunter.hunter.HunterTask;
import nsu.edu.cn.moviehunter.hunter.MovieUrlHunter;
import nsu.edu.cn.moviehunter.remover.MovieDataHunter;
import nsu.edu.cn.moviehunter.remover.RemoverExcuter;
import nsu.edu.cn.moviehunter.util.Movie;
import nsu.edu.cn.moviehunter.util.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class MoviehunterApplication implements CommandLineRunner {
	@Autowired
	private MovieUrlHunter movieUrlHunter;
    @Autowired
    MovieDataHunter movieDataHunter;
    @Autowired
    HunterTask hunterTask;
    @Autowired
    HunterExcuter hunterExcuter;
    @Autowired
	Basket basket;
    @Autowired
	RemoverExcuter removerExcuter;
    @Autowired
	MovieMapper movieMapper;
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MoviehunterApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) {

//			hunterTask.setStartUrl("http://www.ygdy8.net/html/gndy/rihan/list_6_num.html");
//			new Thread(hunterTask).start();
//			movieDataHunter.getData("https://www.ygdy8.com/html/gndy/jddy/20180620/57021.html",1);
//			hunterTask.setStartUrl("aaaaa");
//			hunterTask1.setStartUrl("bbbb");
//            System.out.println(hunterTask.getStartUrl());
//            System.out.println(hunterTask1.getStartUrl());
//		    hunterExcuter.excut();
//		movieUrlHunter.getUrl("http://s.ygdy8.com/plus/so.php?typeid=1&keyword=%CF%B2%BE%E7");
//        System.out.println(movieUrlHunter.getNum("http://s.ygdy8.com/plus/so.php?keyword=%D5%BD%D5%F9&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid=1&TotalResult=101&PageNo=7"));
//		removerExcuter.removeToMySql();
//		Movie movie = movieDataHunter.getData("https://www.ygdy8.com/html/gndy/dyzz/20170918/55052.html",7);
//		movieMapper.insertMovie(movie);
//		try {
//			new MovieUrlHunter().getUrlFrom80s("http://www.80s.la/movie/list/1----h");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(new MovieUrlHunter().getNumOf80S("http://www.80s.la/movie/list/1----h"));
		new MovieDataHunter().getMovieDataFrom80s("http://www.80s.la/movie/1346");
	}
}


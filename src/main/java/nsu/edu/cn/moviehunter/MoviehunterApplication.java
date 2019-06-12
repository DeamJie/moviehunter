package nsu.edu.cn.moviehunter;

import nsu.edu.cn.moviehunter.remover.RemoverExcuter;
import nsu.edu.cn.moviehunter.util.Label;
import nsu.edu.cn.moviehunter.util.LabelMapper;
import nsu.edu.cn.moviehunter.util.QuatzUtil;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MoviehunterApplication implements CommandLineRunner {
	@Autowired
	private LabelMapper labelMapper;
	@Autowired
	private QuatzUtil quatzUtil;
	private Logger logger = LoggerFactory.getLogger(MoviehunterApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MoviehunterApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) {
		List<Label> labelList = labelMapper.selectAll();
		for (Label l : labelList){
			try {
				quatzUtil.startJob(l);
				new Thread(new RemoverExcuter(l.getId())).start();
			} catch (SchedulerException e) {
				logger.error("开启任务失败"+l.getName());
			}
		}
	}
}


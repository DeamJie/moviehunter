package nsu.edu.cn.moviehunter;

import nsu.edu.cn.moviehunter.util.Label;
import nsu.edu.cn.moviehunter.util.LabelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviehunterApplicationTests {
	@Autowired
	private LabelMapper labelMapper;
	@Test
	public void contextLoads() {
		List<Label> list = labelMapper.selectAll();
		System.out.println(list.size());
	}

}


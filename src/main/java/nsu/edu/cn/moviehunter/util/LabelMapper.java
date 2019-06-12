package nsu.edu.cn.moviehunter.util;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LabelMapper {
    String SELECT_KEY = "id,name,start_url";

    @Select({"select " ,SELECT_KEY,"from label"})
    List<Label> selectAll();

}

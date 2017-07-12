package cn.scau.hjr.dao;

import cn.scau.hjr.model.BlackId;
import cn.scau.hjr.model.BlackIdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlackIdMapper {
    int countByExample(BlackIdExample example);

    int deleteByExample(BlackIdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlackId record);

    int insertSelective(BlackId record);

    List<BlackId> selectByExample(BlackIdExample example);

    BlackId selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlackId record, @Param("example") BlackIdExample example);

    int updateByExample(@Param("record") BlackId record, @Param("example") BlackIdExample example);

    int updateByPrimaryKeySelective(BlackId record);

    int updateByPrimaryKey(BlackId record);
}
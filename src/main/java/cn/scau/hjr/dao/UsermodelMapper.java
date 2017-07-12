package cn.scau.hjr.dao;

import cn.scau.hjr.model.Usermodel;
import cn.scau.hjr.model.UsermodelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsermodelMapper {
    int countByExample(UsermodelExample example);

    int deleteByExample(UsermodelExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(Usermodel record);

    int insertSelective(Usermodel record);

    List<Usermodel> selectByExample(UsermodelExample example);

    Usermodel selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") Usermodel record, @Param("example") UsermodelExample example);

    int updateByExample(@Param("record") Usermodel record, @Param("example") UsermodelExample example);

    int updateByPrimaryKeySelective(Usermodel record);

    int updateByPrimaryKey(Usermodel record);
}
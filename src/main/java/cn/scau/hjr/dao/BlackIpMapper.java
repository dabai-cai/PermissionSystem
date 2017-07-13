package cn.scau.hjr.dao;

import cn.scau.hjr.model.BlackIp;

public interface BlackIpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackIp record);

    int insertSelective(BlackIp record);

    BlackIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlackIp record);

    int updateByPrimaryKey(BlackIp record);
}
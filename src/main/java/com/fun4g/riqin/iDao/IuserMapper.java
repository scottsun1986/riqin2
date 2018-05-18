package com.fun4g.riqin.iDao;

import com.fun4g.riqin.model.Iuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IuserMapper {
    int deleteByPrimaryKey(String id);

    int insert(Iuser record);

    int insertSelective(Iuser record);

    Iuser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Iuser record);

    int updateByPrimaryKey(Iuser record);
    Iuser selectByWxId(@Param("wx_id")String wxid);
    List<Iuser> selectAllValid();
}
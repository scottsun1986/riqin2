package com.fun4g.riqin.iDao;

import com.fun4g.riqin.model.Mytask;

import java.util.List;

public interface MytaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mytask record);

    int insertSelective(Mytask record);

    Mytask selectByPrimaryKey(Integer id);
    Mytask selectByPrimaryKey2(Integer id);

    int updateByPrimaryKeySelective(Mytask record);

    int updateByPrimaryKey(Mytask record);
    List<Mytask> selectToBeNotifyTask();
}
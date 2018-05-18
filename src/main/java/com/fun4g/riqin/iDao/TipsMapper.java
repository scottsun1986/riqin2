package com.fun4g.riqin.iDao;

import com.fun4g.riqin.model.Mytask;
import com.fun4g.riqin.model.Tips;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TipsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tips record);

    int insertSelective(Tips record);

    Tips selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tips record);

    int updateByPrimaryKey(Tips record);

    List<Tips> selectToBeNotifyTask();
    List<Tips>  selectNotAlertedByUserId(@Param("user_id")String user_id);
}
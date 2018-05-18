package com.fun4g.riqin.iDao;

import com.fun4g.riqin.model.Source;

import java.util.List;

public interface SourceMapper {
    int deleteByPrimaryKey(Integer sourceId);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Integer sourceId);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
    List<Source> selectAllValid();
}
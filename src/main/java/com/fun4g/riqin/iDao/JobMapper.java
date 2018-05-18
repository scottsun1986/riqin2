package com.fun4g.riqin.iDao;

import com.fun4g.riqin.model.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(Integer jobId);

    Integer insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Integer jobId);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    List<Job> selectByHandlerIdAndJobType(@Param("job_handler_id")String job_handler_id, @Param("job_type")String job_type);
    List<Job> selectNext7DayJobsByHandlerId(@Param("job_handler_id")String job_handler_id);
    /**
     *
     */
}
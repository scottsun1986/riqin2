package com.fun4g.riqin.iDao;

import com.fun4g.riqin.model.JobBackup;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface JobBackupMapper {
    int deleteByPrimaryKey(Integer jobBackId);

    int insert(JobBackup record);

    int insertSelective(JobBackup record);

    JobBackup selectByPrimaryKey(Integer jobBackId);

    int updateByPrimaryKeySelective(JobBackup record);

    int updateByPrimaryKey(JobBackup record);

    List<JobBackup> selectByBackupTime(@Param("backup_time_f")Date backup_time_f, @Param("backup_time_t")Date backup_time_t);

    List selectByBackupTimeForExport(@Param("backup_time_f")Date backup_time_f, @Param("backup_time_t")Date backup_time_t);
    List selectBySearch(@Param("backup_time_f")Date backup_time_f, @Param("backup_time_t")Date backup_time_t,@Param("keywd")String keywd,@Param("is_important")Integer isImportant,@Param("is_order_by_time")Integer isOrderByTime);
    JobBackup selectMoreDetailByPrimaryKey(Integer jobBackId);

}
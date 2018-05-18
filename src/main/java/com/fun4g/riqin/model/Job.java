package com.fun4g.riqin.model;



import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;


public class Job {
    public Source getJobSource() {
        return jobSource;
    }

    public void setJobSource(Source jobSource) {
        this.jobSource = jobSource;
    }

    private Source jobSource;
    @NumberFormat
    private Integer jobId;
    @NumberFormat
    private Integer sourceId;

    private String creatorId;
    @DateTimeFormat
    private Date createTime;

    private String jobComment;
    @NumberFormat
    private Byte isSingle;

    public Byte getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Byte isImportant) {
        this.isImportant = isImportant;
    }

    @NumberFormat
    private Byte isImportant;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date requiredTime;

    private String cycle;

    private String jobHandlerId;

    private String jobType;
    @NumberFormat
    private Integer jobGroupId;
    @NumberFormat
    private Integer jobStatus;

    private String jobFeedback;
    @DateTimeFormat
    private Date finishTime;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJobComment() {
        return jobComment;
    }

    public void setJobComment(String jobComment) {
        this.jobComment = jobComment == null ? null : jobComment.trim();
    }

    public Byte getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Byte isSingle) {
        this.isSingle = isSingle;
    }

    public Date getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Date requiredTime) {
        this.requiredTime = requiredTime;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    public String getJobHandlerId() {
        return jobHandlerId;
    }

    public void setJobHandlerId(String jobHandlerId) {
        this.jobHandlerId = jobHandlerId == null ? null : jobHandlerId.trim();
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public Integer getJobGroupId() {
        return jobGroupId;
    }

    public void setJobGroupId(Integer jobGroupId) {
        this.jobGroupId = jobGroupId;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobFeedback() {
        return jobFeedback;
    }

    public void setJobFeedback(String jobFeedback) {
        this.jobFeedback = jobFeedback == null ? null : jobFeedback.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
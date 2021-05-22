package com.newland.boss.export.cdr.bean;

import java.util.Date;

/**
 * @author ${linfengpeng}
 * @Title: QueryCdrOperation
 * @ProjectName TempProject
 * @Description: 导入查询话单日志表
 * @date 2020/8/10 9:14
 */
public class QueryCdrOperation {
    /**
     * 用户ID
      */
    private Long userId;
    /**
     * 批量存放主机
     */
    private String batchHost;
    /**
     * 批量存放路径
     */
    private String batchFilePath;
    /**
     * 批量存放文件名
     */
    private String batchFileName;

    /**
     * 查询工单开始时间
     */
    private Date startTime;
    /**
     * 查询工单终止时间
     */
    private Date endTime;
    /**
     * 话单处理状态
     */
    private int status;
    /**
     * 查询话单表 更新日志时间
     */
    private String updateTime;

    /**
     * 导入工单流水
     */
    private int OperationId;
    /**
     * 操作员工号
     */
    private int operatorId;

    /**
     * 工单类型
     * @return
     */
    private int operatorType;

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBatchHost() {
        return batchHost;
    }

    public void setBatchHost(String batchHost) {
        this.batchHost = batchHost;
    }

    public String getBatchFilePath() {
        return batchFilePath;
    }

    public void setBatchFilePath(String batchFilePath) {
        this.batchFilePath = batchFilePath;
    }

    public String getBatchFileName() {
        return batchFileName;
    }

    public void setBatchFileName(String batchFileName) {
        this.batchFileName = batchFileName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getOperationId() {
        return OperationId;
    }

    public void setOperationId(int operationId) {
        OperationId = operationId;
    }
}

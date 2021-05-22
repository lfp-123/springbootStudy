package com.newland.boss.ops.scan.bean;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lfp
 * @Title: ResoureConfig
 * @ProjectName ops
 * @Description: 采集配置bean
 * @date 2021/1/21 14:52
 */
@Component
public class ResourceConfig {

    private String processId;
    private String taskId;
    private Long collectType;
    private Long fileType;
    private String fileNameFormate;
    private Long remoteSys;
    private String remoteAddr;
    private String remoteUser;
    private String remotePwd;
    private String remotePort;
    private String remotePath;
    private Integer remotePathType;
    private String remotePathBack;
    private Integer remoteDealType;
    private Long localSys;
    private String localPath;
    private Integer localPathType;
    private String localPathTmp;
    private Integer fileDateStart;
    private Long fileDateLenth;
    private Integer fileSeqStart;
    private Long fileSeqLenth;
    private Long fileSeqMin;
    private Long fileSeqMax;
    private String ohterMsg;
    private String description;
    private Integer flag;
    private Date insertTime;
    private Byte isLog;

    public ResourceConfig() {
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getCollectType() {
        return collectType;
    }

    public void setCollectType(Long collectType) {
        this.collectType = collectType;
    }

    public Long getFileType() {
        return fileType;
    }

    public void setFileType(Long fileType) {
        this.fileType = fileType;
    }

    public String getFileNameFormate() {
        return fileNameFormate;
    }

    public void setFileNameFormate(String fileNameFormate) {
        this.fileNameFormate = fileNameFormate;
    }

    public Long getRemoteSys() {
        return remoteSys;
    }

    public void setRemoteSys(Long remoteSys) {
        this.remoteSys = remoteSys;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRemotePwd() {
        return remotePwd;
    }

    public void setRemotePwd(String remotePwd) {
        this.remotePwd = remotePwd;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public Integer getRemotePathType() {
        return remotePathType;
    }

    public void setRemotePathType(Integer remotePathType) {
        this.remotePathType = remotePathType;
    }

    public String getRemotePathBack() {
        return remotePathBack;
    }

    public void setRemotePathBack(String remotePathBack) {
        this.remotePathBack = remotePathBack;
    }

    public Integer getRemoteDealType() {
        return remoteDealType;
    }

    public void setRemoteDealType(Integer remoteDealType) {
        this.remoteDealType = remoteDealType;
    }

    public Long getLocalSys() {
        return localSys;
    }

    public void setLocalSys(Long localSys) {
        this.localSys = localSys;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public Integer getLocalPathType() {
        return localPathType;
    }

    public void setLocalPathType(Integer localPathType) {
        this.localPathType = localPathType;
    }

    public String getLocalPathTmp() {
        return localPathTmp;
    }

    public void setLocalPathTmp(String localPathTmp) {
        this.localPathTmp = localPathTmp;
    }

    public Integer getFileDateStart() {
        return fileDateStart;
    }

    public void setFileDateStart(Integer fileDateStart) {
        this.fileDateStart = fileDateStart;
    }

    public Long getFileDateLenth() {
        return fileDateLenth;
    }

    public void setFileDateLenth(Long fileDateLenth) {
        this.fileDateLenth = fileDateLenth;
    }

    public Integer getFileSeqStart() {
        return fileSeqStart;
    }

    public void setFileSeqStart(Integer fileSeqStart) {
        this.fileSeqStart = fileSeqStart;
    }

    public Long getFileSeqLenth() {
        return fileSeqLenth;
    }

    public void setFileSeqLenth(Long fileSeqLenth) {
        this.fileSeqLenth = fileSeqLenth;
    }

    public Long getFileSeqMin() {
        return fileSeqMin;
    }

    public void setFileSeqMin(Long fileSeqMin) {
        this.fileSeqMin = fileSeqMin;
    }

    public Long getFileSeqMax() {
        return fileSeqMax;
    }

    public void setFileSeqMax(Long fileSeqMax) {
        this.fileSeqMax = fileSeqMax;
    }

    public String getOhterMsg() {
        return ohterMsg;
    }

    public void setOhterMsg(String ohterMsg) {
        this.ohterMsg = ohterMsg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Byte getIsLog() {
        return isLog;
    }

    public void setIsLog(Byte isLog) {
        this.isLog = isLog;
    }

    @Override
    public String toString() {
        return "ResourceConfig{" +
                "processId='" + processId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", collectType=" + collectType +
                ", fileType=" + fileType +
                ", fileNameFormate='" + fileNameFormate + '\'' +
                ", remoteSys=" + remoteSys +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", remoteUser='" + remoteUser + '\'' +
                ", remotePwd='" + remotePwd + '\'' +
                ", remotePort='" + remotePort + '\'' +
                ", remotePath='" + remotePath + '\'' +
                ", remotePathType=" + remotePathType +
                ", remotePathBack='" + remotePathBack + '\'' +
                ", remoteDealType=" + remoteDealType +
                ", localSys=" + localSys +
                ", localPath='" + localPath + '\'' +
                ", localPathType=" + localPathType +
                ", localPathTmp='" + localPathTmp + '\'' +
                ", fileDateStart=" + fileDateStart +
                ", fileDateLenth=" + fileDateLenth +
                ", fileSeqStart=" + fileSeqStart +
                ", fileSeqLenth=" + fileSeqLenth +
                ", fileSeqMin=" + fileSeqMin +
                ", fileSeqMax=" + fileSeqMax +
                ", ohterMsg='" + ohterMsg + '\'' +
                ", description='" + description + '\'' +
                ", flag=" + flag +
                ", insertTime=" + insertTime +
                ", isLog=" + isLog +
                '}';
    }
}

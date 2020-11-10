package com.example.springdemo.mybatis;

import java.util.Date;

public class Sessionkey {
    private Integer id;

    private String tboxId;

    private String sessionkey;

    private Date sessionkeyTime;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer version;

    private Byte deleteMark;

    private Byte enableMark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTboxId() {
        return tboxId;
    }

    public void setTboxId(String tboxId) {
        this.tboxId = tboxId == null ? null : tboxId.trim();
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey == null ? null : sessionkey.trim();
    }

    public Date getSessionkeyTime() {
        return sessionkeyTime;
    }

    public void setSessionkeyTime(Date sessionkeyTime) {
        this.sessionkeyTime = sessionkeyTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Byte getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Byte deleteMark) {
        this.deleteMark = deleteMark;
    }

    public Byte getEnableMark() {
        return enableMark;
    }

    public void setEnableMark(Byte enableMark) {
        this.enableMark = enableMark;
    }
}
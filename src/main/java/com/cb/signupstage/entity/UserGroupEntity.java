package com.cb.signupstage.entity;

import java.io.Serializable;
import java.util.Date;

public class UserGroupEntity implements Serializable {
    private Long id;

    private Long accountId;

    private String groupName;

    private Long parentId;

    private Date createTime;

    private String description;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public UserGroupEntity(Long id, Long accountId, String groupName, Long parentId, Date createTime, String description, Integer status) {
        this.id = id;
        this.accountId = accountId;
        this.groupName = groupName;
        this.parentId = parentId;
        this.createTime = createTime;
        this.description = description;
        this.status = status;
    }

    public UserGroupEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountId=").append(accountId);
        sb.append(", groupName=").append(groupName);
        sb.append(", parentId=").append(parentId);
        sb.append(", createTime=").append(createTime);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
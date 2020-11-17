package com.cb.signupstage.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserGroup implements Serializable {
    private Long id;

    private Long accountId;

    private String groupName;

    private Long parentId;

    private Date createTime;

    private String description;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public UserGroup(Long id, Long accountId, String groupName, Long parentId, Date createTime, String description, Integer status) {
        this.id = id;
        this.accountId = accountId;
        this.groupName = groupName;
        this.parentId = parentId;
        this.createTime = createTime;
        this.description = description;
        this.status = status;
    }

    public UserGroup() {
        super();
    }


}
package com.ifarm.console.shared.domain.po;



/**
 *
 */
public class UserRolePO extends BasePO {
    private static final long serialVersionUID = 3371204090150138391L;
    private Integer userTid;
    private Integer roleTid;

    public Integer getUserTid() {
        return userTid;
    }

    public void setUserTid(Integer userTid) {
        this.userTid = userTid;
    }

    public Integer getRoleTid() {
        return roleTid;
    }

    public void setRoleTid(Integer roleTid) {
        this.roleTid = roleTid;
    }
}

package com.ifarm.console.shared.domain.po;

public class PermissionNotRolePO extends BasePO {
/**
	 * 
	 */
	private static final long serialVersionUID = 6024381527683572284L;
private Integer userid;
private Integer permissionid;

    /**
     * @return Integer return the userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return Integer return the permissionid
     */
    public Integer getPermissionid() {
        return permissionid;
    }

    /**
     * @param permissionid the permissionid to set
     */
    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

}

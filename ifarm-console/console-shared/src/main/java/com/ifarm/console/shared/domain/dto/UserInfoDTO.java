    package com.ifarm.console.shared.domain.dto;


import com.github.framework.util.serializer.BeanCopyUtils;
import com.ifarm.console.shared.domain.po.UserInfoPO;

import java.util.List;

/**
 *
 **/
public class UserInfoDTO extends BaseDTO {
    private static final long serialVersionUID = -5402091371261060045L;
    private String userName;
    private String password;
    private String nickName;
    private String salt;
    private String email;
    private String mobileNo;
    private String empCode;
    private String deptCode;
    private String notes;
 
    private List<String> roles;
    private List<String> permissions;

    public UserInfoPO convertPO() {
        UserInfoPO userInfoPO = new UserInfoPO();
        BeanCopyUtils.copyBean(this, userInfoPO);
        return userInfoPO;
    }
    public void clearCredentialsSalt() {
        this.password = null;
        this.salt = null;
    }

    public String getCredentialsSalt() {
        return userName;
    }

    public UserInfoDTO() {
    }

    public UserInfoDTO(String userName, String empCode, String deptCode) {
        this.userName = userName;
        this.empCode = empCode;
        this.deptCode = deptCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}

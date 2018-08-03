package com.example.cyui.demo.dto.Register;

import com.example.cyui.demo.enums.RegisterRoleEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class RegisterDto implements Serializable {

    private String registerNo;
    private String registerName;
    private Integer role;
    private Integer age;
    private String address;
    private Integer status;
    private String roleName;

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String value) {
        this.registerNo = StringUtils.isNotBlank(value)?value:null;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String value) {
        this.registerName = StringUtils.isNotBlank(value)?value:null;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer value) {
        this.role = null!=value?value:null;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer value) {
        this.age = null!=value?value:null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = StringUtils.isNotBlank(value)?value:null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer value) {
        this.status = null!=value?value:null;
    }

    public String getRoleName() {
        return null!=this.role? RegisterRoleEnum.getValue(String.valueOf(this.role)):null;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "registerNo='" + registerNo + '\'' +
                ", registerName='" + registerName + '\'' +
                ", role=" + role +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

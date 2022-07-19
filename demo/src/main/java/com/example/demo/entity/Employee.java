package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工信息
 * @TableName employee
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

   
    @TableField(fill =FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;



}
//@Data
//@TableName(value ="employee")
//public class Employee implements Serializable {
//    /**
//     * 主键
//     */
//    @TableId
//    private Long id;
//
//    /**
//     * 姓名
//     */
//    private String name;
//
//    /**
//     * 用户名
//     */
//    private String username;
//
//    /**
//     * 密码
//     */
//    private String password;
//
//    /**
//     * 手机号
//     */
//    private String phone;
//
//    /**
//     * 性别
//     */
//    private String sex;
//
//    /**
//     * 身份证号
//     */
//    private String idNumber;
//
//    /**
//     * 状态 0:禁用，1:正常
//     */
//    private Integer status;
//
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 更新时间
//     */
//    private Date updateTime;
//
//    /**
//     * 创建人
//     */
//    private Long createUser;
//
//    /**
//     * 修改人
//     */
//    private Long updateUser;
//
//    @TableField(exist = false)
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 主键
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * 主键
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * 姓名
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * 姓名
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * 用户名
//     */
//    public String getUsername() {
//        return username;
//    }
//
//    /**
//     * 用户名
//     */
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    /**
//     * 密码
//     */
//    public String getPassword() {
//        return password;
//    }
//
//    /**
//     * 密码
//     */
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    /**
//     * 手机号
//     */
//    public String getPhone() {
//        return phone;
//    }
//
//    /**
//     * 手机号
//     */
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    /**
//     * 性别
//     */
//    public String getSex() {
//        return sex;
//    }
//
//    /**
//     * 性别
//     */
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//
//    /**
//     * 身份证号
//     */
//    public String getIdNumber() {
//        return idNumber;
//    }
//
//    /**
//     * 身份证号
//     */
//    public void setIdNumber(String idNumber) {
//        this.idNumber = idNumber;
//    }
//
//    /**
//     * 状态 0:禁用，1:正常
//     */
//    public Integer getStatus() {
//        return status;
//    }
//
//    /**
//     * 状态 0:禁用，1:正常
//     */
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    /**
//     * 创建时间
//     */
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    /**
//     * 创建时间
//     */
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    /**
//     * 更新时间
//     */
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    /**
//     * 更新时间
//     */
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    /**
//     * 创建人
//     */
//    public Long getCreateUser() {
//        return createUser;
//    }
//
//    /**
//     * 创建人
//     */
//    public void setCreateUser(Long createUser) {
//        this.createUser = createUser;
//    }
//
//    /**
//     * 修改人
//     */
//    public Long getUpdateUser() {
//        return updateUser;
//    }
//
//    /**
//     * 修改人
//     */
//    public void setUpdateUser(Long updateUser) {
//        this.updateUser = updateUser;
//    }
//
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        Employee other = (Employee) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
//            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
//            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
//            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
//            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
//            && (this.getIdNumber() == null ? other.getIdNumber() == null : this.getIdNumber().equals(other.getIdNumber()))
//            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
//            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
//            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
//            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
//            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
//        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
//        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
//        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
//        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
//        result = prime * result + ((getIdNumber() == null) ? 0 : getIdNumber().hashCode());
//        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
//        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
//        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
//        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
//        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", name=").append(name);
//        sb.append(", username=").append(username);
//        sb.append(", password=").append(password);
//        sb.append(", phone=").append(phone);
//        sb.append(", sex=").append(sex);
//        sb.append(", idNumber=").append(idNumber);
//        sb.append(", status=").append(status);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", updateTime=").append(updateTime);
//        sb.append(", createUser=").append(createUser);
//        sb.append(", updateUser=").append(updateUser);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
//}
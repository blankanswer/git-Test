package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 订单表
 * @TableName orders
 */
@Data
@TableName(value ="orders")
public class Orders implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 订单号
     */
    private String number;

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    private Integer status;

    /**
     * 下单用户
     */
    private Long userId;

    /**
     * 地址id
     */
    private Long addressBookId;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 结账时间
     */
    private LocalDateTime checkoutTime;

    /**
     * 支付方式 1微信,2支付宝
     */
    private Integer payMethod;

    /**
     * 实收金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String consignee;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 订单号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 下单用户
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 下单用户
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 地址id
     */
    public Long getAddressBookId() {
        return addressBookId;
    }

    /**
     * 地址id
     */
    public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }

    /**
     * 下单时间
     */
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    /**
     * 下单时间
     */
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 结账时间
     */
    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    /**
     * 结账时间
     */
    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    /**
     * 支付方式 1微信,2支付宝
     */
    public Integer getPayMethod() {
        return payMethod;
    }

    /**
     * 支付方式 1微信,2支付宝
     */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 实收金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 实收金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Orders other = (Orders) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAddressBookId() == null ? other.getAddressBookId() == null : this.getAddressBookId().equals(other.getAddressBookId()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getCheckoutTime() == null ? other.getCheckoutTime() == null : this.getCheckoutTime().equals(other.getCheckoutTime()))
            && (this.getPayMethod() == null ? other.getPayMethod() == null : this.getPayMethod().equals(other.getPayMethod()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAddressBookId() == null) ? 0 : getAddressBookId().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        result = prime * result + ((getCheckoutTime() == null) ? 0 : getCheckoutTime().hashCode());
        result = prime * result + ((getPayMethod() == null) ? 0 : getPayMethod().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", addressBookId=").append(addressBookId);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", checkoutTime=").append(checkoutTime);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", amount=").append(amount);
        sb.append(", remark=").append(remark);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", userName=").append(userName);
        sb.append(", consignee=").append(consignee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
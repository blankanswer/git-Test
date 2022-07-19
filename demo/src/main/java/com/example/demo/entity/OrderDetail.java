package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细表
 * @TableName order_detail
 */
@Data
@TableName(value ="order_detail")
public class OrderDetail implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 套餐id
     */
    private Long setmealId;

    /**
     * 口味
     */
    private String dishFlavor;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 金额
     */
    private BigDecimal amount;

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
     * 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 菜品id
     */
    public Long getDishId() {
        return dishId;
    }

    /**
     * 菜品id
     */
    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    /**
     * 套餐id
     */
    public Long getSetmealId() {
        return setmealId;
    }

    /**
     * 套餐id
     */
    public void setSetmealId(Long setmealId) {
        this.setmealId = setmealId;
    }

    /**
     * 口味
     */
    public String getDishFlavor() {
        return dishFlavor;
    }

    /**
     * 口味
     */
    public void setDishFlavor(String dishFlavor) {
        this.dishFlavor = dishFlavor;
    }

    /**
     * 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        OrderDetail other = (OrderDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getDishId() == null ? other.getDishId() == null : this.getDishId().equals(other.getDishId()))
            && (this.getSetmealId() == null ? other.getSetmealId() == null : this.getSetmealId().equals(other.getSetmealId()))
            && (this.getDishFlavor() == null ? other.getDishFlavor() == null : this.getDishFlavor().equals(other.getDishFlavor()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getDishId() == null) ? 0 : getDishId().hashCode());
        result = prime * result + ((getSetmealId() == null) ? 0 : getSetmealId().hashCode());
        result = prime * result + ((getDishFlavor() == null) ? 0 : getDishFlavor().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", image=").append(image);
        sb.append(", orderId=").append(orderId);
        sb.append(", dishId=").append(dishId);
        sb.append(", setmealId=").append(setmealId);
        sb.append(", dishFlavor=").append(dishFlavor);
        sb.append(", number=").append(number);
        sb.append(", amount=").append(amount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
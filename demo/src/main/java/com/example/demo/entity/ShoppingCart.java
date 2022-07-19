package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 购物车
 * @TableName shopping_cart
 */
@Data
@TableName(value ="shopping_cart")
public class ShoppingCart implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 主键
     */
    private Long userId;

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

    /**
     * 创建时间
     */

    private LocalDateTime createTime;


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
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
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
     * 主键
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 主键
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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

    /**
     * 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
        ShoppingCart other = (ShoppingCart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDishId() == null ? other.getDishId() == null : this.getDishId().equals(other.getDishId()))
            && (this.getSetmealId() == null ? other.getSetmealId() == null : this.getSetmealId().equals(other.getSetmealId()))
            && (this.getDishFlavor() == null ? other.getDishFlavor() == null : this.getDishFlavor().equals(other.getDishFlavor()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDishId() == null) ? 0 : getDishId().hashCode());
        result = prime * result + ((getSetmealId() == null) ? 0 : getSetmealId().hashCode());
        result = prime * result + ((getDishFlavor() == null) ? 0 : getDishFlavor().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", dishId=").append(dishId);
        sb.append(", setmealId=").append(setmealId);
        sb.append(", dishFlavor=").append(dishFlavor);
        sb.append(", number=").append(number);
        sb.append(", amount=").append(amount);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
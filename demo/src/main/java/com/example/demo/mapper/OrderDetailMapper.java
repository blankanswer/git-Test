package com.example.demo.mapper;

import com.example.demo.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【order_detail(订单明细表)】的数据库操作Mapper
* @createDate 2022-07-18 00:48:18
* @Entity com.example.demo.entity.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}





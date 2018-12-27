package com.czy.sell.repository;

import com.czy.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/7
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    public List<OrderDetail> findByOrderId(String orderId);
}

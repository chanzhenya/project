package com.czy.sell.repository;

import com.czy.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Judith
 * @date 2018/12/7
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}

package com.etech.repository;

import com.etech.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionsRepository extends PagingAndSortingRepository<OrderStatus, Long>,
        CrudRepository<OrderStatus, Long>, JpaRepository<OrderStatus, Long> {
}

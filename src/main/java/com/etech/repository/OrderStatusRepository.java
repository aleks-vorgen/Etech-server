package com.etech.repository;

import com.etech.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderStatusRepository extends PagingAndSortingRepository<OrderStatus, Long>,
        CrudRepository<OrderStatus, Long>, JpaRepository<OrderStatus, Long> {
}

package com.etech.repository;

import com.etech.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>,
        CrudRepository<Order, Long>, JpaRepository<Order, Long> {
}

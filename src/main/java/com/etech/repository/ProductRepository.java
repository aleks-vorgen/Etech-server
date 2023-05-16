package com.etech.repository;

import com.etech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
        CrudRepository<Product, Long>, JpaRepository<Product, Long> {
    List<Product> findAllByTitleContainsIgnoreCaseOrProducerContainsIgnoreCase(String title, String producer);
}

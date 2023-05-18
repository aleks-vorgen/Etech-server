package com.etech.repository;

import com.etech.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>,
        CrudRepository<Category, Long>, JpaRepository<Category, Long> {
    List<Category> findAllByTitleContainsIgnoreCase(String title);
}

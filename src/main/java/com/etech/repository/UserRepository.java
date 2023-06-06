package com.etech.repository;

import com.etech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        CrudRepository<User, Long>, JpaRepository<User, Long> {

    User findByEmail(@Param("email") String email);
    User findByUsername(@Param("username") String username);
}
package com.etech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.etech.model.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        CrudRepository<User, Long>, JpaRepository<User, Long> {

    List<User> findByEmail(@Param("email") String email);
    User findByUsername(@Param("username") String username);
}
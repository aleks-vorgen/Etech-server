package com.etech.repository;

import com.etech.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>,
        CrudRepository<Comment, Long>, JpaRepository<Comment, Long> {
}

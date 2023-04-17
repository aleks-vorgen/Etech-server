package com.etech.controller;

import com.etech.model.Comment;
import com.etech.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    //@GetMapping("/comments")
    public List<Comment> getCommentList() {
        return commentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}

package com.jotov.sarafan.service;

import com.jotov.sarafan.domain.Comment;
import com.jotov.sarafan.domain.User;
import com.jotov.sarafan.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthot(user);
        commentRepo.save(comment);

        return comment;
    }
}

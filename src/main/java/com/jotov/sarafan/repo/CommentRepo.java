package com.jotov.sarafan.repo;

import com.jotov.sarafan.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}

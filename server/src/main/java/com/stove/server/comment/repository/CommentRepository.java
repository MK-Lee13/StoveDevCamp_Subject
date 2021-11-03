package com.stove.server.comment.repository;

import com.stove.server.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Minky on 2021-11-02
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentId(Long parentId);
    long countByParentId(Long parentId);
    void deleteByIdAndPassword(Long id, String password);
    Optional<Comment> findByIdAndPassword(Long id, String password);
}

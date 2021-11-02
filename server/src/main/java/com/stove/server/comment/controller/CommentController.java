package com.stove.server.comment.controller;

import com.stove.server.comment.dto.CommentRequestDto;
import com.stove.server.comment.dto.CommentResponseDto;
import com.stove.server.comment.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Minky on 2021-11-02
 */

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{parentId}")
    public ResponseEntity<List<CommentResponseDto>> getChildComments(@PathVariable Long parentId) {
        return ResponseEntity.ok(commentService.getChildComments(parentId));
    }

    @PostMapping
    public ResponseEntity<Void> createComments(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        Long id = commentService.create(commentRequestDto);
        return ResponseEntity.created(URI.create("api/v1/comments" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComments(
            @PathVariable Long id,
            @RequestHeader(value = "password", required = true) String password
    ) {
        commentService.deleteById(id, password);
        return ResponseEntity.noContent().build();
    }
}

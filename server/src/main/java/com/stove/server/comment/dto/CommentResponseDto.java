package com.stove.server.comment.dto;

import com.stove.server.comment.domain.Comment;
import com.stove.server.comment.domain.CommentChildFlag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Minky on 2021-11-02
 */

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private Long parentId;
    private CommentChildFlag commentChildFlag;
    private String nickname;
    private String body;
    private LocalDateTime createdDate;

    public CommentResponseDto(
            Long id,
            Long parentId,
            CommentChildFlag commentChildFlag,
            String nickname,
            String body,
            LocalDateTime createdDate
    ) {
        this.id = id;
        this.parentId = parentId;
        this.commentChildFlag = commentChildFlag;
        this.nickname = nickname;
        this.body = body;
        this.createdDate = createdDate;
    }

    public static CommentResponseDto of(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getParentId(),
                comment.getCommentChildFlag(),
                comment.getNickname(),
                comment.getBody(),
                comment.getCreatedDate()
        );
    }

    public static List<CommentResponseDto> listOf(List<Comment> comments) {
        return comments.stream()
                .map(CommentResponseDto::of)
                .collect(Collectors.toList());
    }
}

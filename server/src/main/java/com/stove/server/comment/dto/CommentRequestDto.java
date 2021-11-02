package com.stove.server.comment.dto;

import com.stove.server.comment.domain.Comment;
import com.stove.server.comment.domain.CommentChildFlag;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Minky on 2021-11-02
 */
@Getter
public class CommentRequestDto {
    private Long parentId;
    @NotBlank(message = "nickname cannot be blank")
    private String nickname;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "body cannot be blank")
    private String body;
    @NotNull(message = "board ID cannot be Null")
    private Long boardId;

    public CommentRequestDto(
            Long boardId,
            Long parentId,
            String nickname,
            String password,
            String body
    ) {
        this.boardId = boardId;
        this.parentId = parentId;
        this.nickname = nickname;
        this.password = password;
        this.body = body;
    }

    public Comment toEntity() {
        return new Comment(
                null,
                parentId,
                CommentChildFlag.NONE_CHILD,
                nickname,
                password,
                body
        );
    }
}

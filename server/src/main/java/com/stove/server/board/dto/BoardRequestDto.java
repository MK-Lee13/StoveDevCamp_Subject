package com.stove.server.board.dto;

import com.stove.server.board.domain.Board;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Minky on 2021-10-30
 */

@Getter
public class BoardRequestDto {
    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotBlank(message = "message cannot be blank")
    private String body;
    private String thumbnailUrl;

    public BoardRequestDto(String title, String body, String thumbnailUrl) {
        this.title = title;
        this.body = body;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Board toEntity() {
        return new Board(null, title, body, thumbnailUrl);
    }
}

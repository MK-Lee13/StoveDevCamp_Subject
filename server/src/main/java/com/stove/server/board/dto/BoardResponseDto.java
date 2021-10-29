package com.stove.server.board.dto;

import com.stove.server.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Minky on 2021-10-30
 */

@NoArgsConstructor
@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String body;
    private String thumbnailUrl;

    public BoardResponseDto(Long id, String title, String body, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static BoardResponseDto of(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBody(),
                board.getThumbnailUrl()
        );
    }

    public static List<BoardResponseDto> listOf(List<Board> boards) {
        return boards.stream()
                .map(BoardResponseDto::of)
                .collect(Collectors.toList());
    }
}

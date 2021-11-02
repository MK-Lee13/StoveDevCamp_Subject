package com.stove.server.board.domain;

import com.stove.server.board.dto.BoardRequestDto;
import com.stove.server.comment.domain.Comment;
import com.stove.server.common.domain.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Minky on 2021-10-30
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_sequence_gen")
    @SequenceGenerator(name = "board_sequence_gen", sequenceName = "board_sequence")
    @Column(name = "board_id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(nullable = false, length = 4000)
    private String body;

    @Column(length = 400)
    private String thumbnailUrl;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;

    @Builder
    public Board(Long id, String title, String body, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.body = boardRequestDto.getBody();
        this.thumbnailUrl = boardRequestDto.getThumbnailUrl();
    }
}

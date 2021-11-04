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
@Table(name = "board_tb")
public class Board extends BaseTimeEntity {
    @Id
    @SequenceGenerator(name = "board_sequence_gen", sequenceName = "board_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "board_sequence_gen")
    @Column(name = "board_id")
    private Long id;

    @NotNull
    @Column(name = "board_title", nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(name = "board_body", nullable = false, length = 4000)
    private String body;

    @Column(name = "board_thumbnail_url", length = 400)
    private String thumbnailUrl;

    @OneToMany(targetEntity = Comment.class, mappedBy = "board", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
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

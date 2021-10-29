package com.stove.server.board.domain;

import com.stove.server.common.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    @Nullable
    private String thumbnailUrl;

    @Builder
    public Board(Long id, String title, String body, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.thumbnailUrl = thumbnailUrl;
    }
}

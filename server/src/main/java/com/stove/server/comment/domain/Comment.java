package com.stove.server.comment.domain;

import com.stove.server.board.domain.Board;
import com.stove.server.common.domain.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Minky on 2021-11-02
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence_gen")
    @SequenceGenerator(name = "comment_sequence_gen", sequenceName = "comment_sequence")
    @Column(name = "comment_id")
    private Long id;

    @Column
    private Long parentId;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private CommentChildFlag commentChildFlag;

    @NotNull
    @Column(nullable = false, length = 50)
    private String nickname;

    @NotNull
    @Column(nullable = false, length = 100)
    private String password;

    @NotNull
    @Column(nullable = false, length = 4000)
    private String body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(
            Long id,
            Long parentId,
            CommentChildFlag commentChildFlag,
            String nickname,
            String password,
            String body
    ) {
        this.id = id;
        this.parentId = parentId;
        this.commentChildFlag = commentChildFlag;
        this.nickname = nickname;
        this.password = password;
        this.body = body;
    }
}

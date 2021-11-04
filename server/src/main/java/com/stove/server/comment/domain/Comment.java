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
@Table(name = "comment_tb")
public class Comment extends BaseTimeEntity {
    @Id
    @SequenceGenerator(name = "comment_sequence_gen", sequenceName = "comment_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "comment_sequence_gen")
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "comment_child_flag", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private CommentChildFlag commentChildFlag;

    @NotNull
    @Column(name = "comment_nickname", nullable = false, length = 50)
    private String nickname;

    @NotNull
    @Column(name = "comment_password", nullable = false, length = 100)
    private String password;

    @NotNull
    @Column(name = "comment_body", nullable = false, length = 4000)
    private String body;

    @ManyToOne(targetEntity = Board.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
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

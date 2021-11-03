package com.stove.server.comment.service;

import com.stove.server.board.domain.Board;
import com.stove.server.board.repository.BoardRepository;
import com.stove.server.comment.domain.Comment;
import com.stove.server.comment.domain.CommentChildFlag;
import com.stove.server.comment.dto.CommentRequestDto;
import com.stove.server.comment.dto.CommentResponseDto;
import com.stove.server.comment.repository.CommentRepository;
import com.stove.server.common.exception.custom.NotFoundBoardException;
import com.stove.server.common.exception.custom.NotFoundCommentException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static com.stove.server.comment.dto.CommentResponseDto.listOf;

/**
 * Created by Minky on 2021-11-02
 */

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getChildComments(Long parentId) {
        List<Comment> reComments = commentRepository.findByParentId(parentId);
        return listOf(reComments);
    }

    @Transactional
    public Long create(CommentRequestDto commentRequestDto) {
        Long parentId = commentRequestDto.getParentId();
        if (parentId != null) {
            validateNoneChildComment(parentId);
        }
        Comment notSavedComment = commentRequestDto.toEntity();
        Board board = findBoardById(commentRequestDto.getBoardId());
        notSavedComment.setBoard(board);
        Comment comment = commentRepository.save(notSavedComment);
        return comment.getId();
    }

    @Transactional
    public void update(Long id, CommentRequestDto commentRequestDto) {
        Long parentId = commentRequestDto.getParentId();
        if (parentId != null) {
            validateNoneChildComment(parentId);
        }
        Comment comment = commentRequestDto.toEntity();
        comment.setId(id);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteById(Long id, String password) {
        Comment comment = commentRepository.findByIdAndPassword(id, password)
                .orElseThrow(NotFoundCommentException::new);
        Long parentId = comment.getParentId();
        if (parentId != null) {
            validateInChildComment(parentId);
        }
        commentRepository.deleteByIdAndPassword(id, password);
    }

    private void validateNoneChildComment(Long parentId) {
        Comment parentComment = findCommentById(parentId);
        parentComment.setCommentChildFlag(CommentChildFlag.IN_CHILD);
        commentRepository.save(parentComment);
    }

    private void validateInChildComment(Long parentId) {
        long parentIdCounts = commentRepository.countByParentId(parentId);
        if (parentIdCounts < 2L) {
            Comment parentComment = findCommentById(parentId);
            parentComment.setCommentChildFlag(CommentChildFlag.NONE_CHILD);
            commentRepository.save(parentComment);
        }
    }

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(NotFoundCommentException::new);
    }

    private Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);
    }
}

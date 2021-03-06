package com.stove.server.board.service;

import com.stove.server.board.domain.Board;
import com.stove.server.board.dto.BoardRequestDto;
import com.stove.server.board.dto.BoardResponseDto;
import com.stove.server.board.repository.BoardRepository;
import com.stove.server.common.exception.custom.NotFoundBoardException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stove.server.board.dto.BoardResponseDto.listOf;
import static com.stove.server.board.dto.BoardResponseDto.of;

/**
 * Created by Minky on 2021-11-02
 */

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<BoardResponseDto> getBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return new PageImpl<>(listOf(boards.getContent()), pageable, boards.getTotalElements());
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = findBoardById(id);
        return of(board);
    }

    @Transactional
    public Long create(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(boardRequestDto.toEntity());
        return board.getId();
    }

    @Transactional
    public void update(Long id, BoardRequestDto boardRequestDto) {
        Board board = findBoardById(id);
        board.update(boardRequestDto);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteById(Long id) {
        findBoardById(id);
        boardRepository.deleteById(id);
    }

    private Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(NotFoundBoardException::new);
    }
}

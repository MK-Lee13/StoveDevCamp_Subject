package com.stove.server.board.service;

import com.stove.server.board.domain.Board;
import com.stove.server.board.dto.BoardRequestDto;
import com.stove.server.board.dto.BoardResponseDto;
import com.stove.server.board.repository.BoardRepository;
import com.stove.server.common.exception.custom.NotFoundBoardException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stove.server.board.dto.BoardResponseDto.listOf;

/**
 * Created by Minky on 2021-11-02
 */

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return listOf(boards);
    }

    @Transactional
    public Long create(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(boardRequestDto.toEntity());
        return board.getId();
    }

    @Transactional
    public void update(Long id, BoardRequestDto boardRequestDto) {
        findBoardById(id);
        Board board = boardRequestDto.toEntity();
        board.setId(id);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteById(Long id) {
        findBoardById(id);
        boardRepository.deleteById(id);
    }

    private void findBoardById(Long id) {
        boardRepository.findById(id)
                .orElseThrow(NotFoundBoardException::new);
    }
}

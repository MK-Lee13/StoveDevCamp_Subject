package com.stove.server.board.controller;

import com.stove.server.board.dto.BoardRequestDto;
import com.stove.server.board.dto.BoardResponseDto;
import com.stove.server.board.service.BoardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Minky on 2021-11-02
 */

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards() {
        return ResponseEntity.ok(boardService.getBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @PostMapping
    public ResponseEntity<Void> createBoard(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        Long id = boardService.create(boardRequestDto);
        return ResponseEntity.created(URI.create("api/v1/boards/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBoard(
            @PathVariable Long id,
            @Valid @RequestBody BoardRequestDto boardRequestDto
    ) {
        boardService.update(id, boardRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.stove.server.board.repository;

import com.stove.server.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Minky on 2021-11-02
 */

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}

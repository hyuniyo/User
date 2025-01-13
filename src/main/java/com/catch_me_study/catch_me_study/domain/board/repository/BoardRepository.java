package com.catch_me_study.catch_me_study.domain.board.repository;

import com.catch_me_study.catch_me_study.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    List<BoardEntity> findByIsDeletedFalse();

    Optional<BoardEntity> findByIdAndIsDeletedFalse(String id);
}

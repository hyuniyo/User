package com.catch_me_study.catch_me_study.domain.board.service;

import com.catch_me_study.catch_me_study.domain.board.dto.BoardDto;
import com.catch_me_study.catch_me_study.domain.board.entity.BoardEntity;
import com.catch_me_study.catch_me_study.domain.board.mapper.BoardMapper;
import com.catch_me_study.catch_me_study.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public List<BoardDto> getBoardList() {

        List<BoardEntity> boardEntityList = boardRepository.findByIsDeletedFalse();
        return boardMapper.toDto(boardEntityList);
    }

    private BoardEntity findById(String id) {

        Optional<BoardEntity> boardEntity = boardRepository.findByIdAndIsDeletedFalse(id);
        boardEntity.orElseThrow(() -> new RuntimeException("유효하지 않은 게시물 ID입니다."));

        return boardEntity.get();

    }

    public BoardDto getBoardById(String id) {

        return boardMapper.toDto(findById(id));
    }

    public BoardDto createBoard(BoardDto boardDto) {

        BoardEntity boardEntity = boardMapper.toEntity(boardDto);

        boardRepository.save(boardEntity);

        return boardMapper.toDto(boardEntity);
    }

    public BoardDto updateBoard(BoardDto boardDto) {

        BoardEntity boardEntity = findById(boardDto.getId());
        boardEntity.update(boardDto.getTitle(), boardDto.getContent());

        boardRepository.save(boardEntity);

        return boardMapper.toDto(boardEntity);
    }

    public String deleteBoardById(String id) {

        BoardEntity boardEntity = findById(id);
        boardEntity.delete();

        boardRepository.save(boardEntity);

        return boardEntity.getId();
    }

}

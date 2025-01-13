package com.catch_me_study.catch_me_study.domain.board.mapper;

import com.catch_me_study.catch_me_study.domain.board.dto.BoardDto;
import com.catch_me_study.catch_me_study.domain.board.entity.BoardEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardDto toDto(BoardEntity boardEntity);

    BoardEntity toEntity(BoardDto boardDto);

    List<BoardDto> toDto(List<BoardEntity> boardEntityList);

    List<BoardEntity> toEntity(List<BoardDto> boardDtoList);
}
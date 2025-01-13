package com.catch_me_study.catch_me_study.domain.board.controller;

import com.catch_me_study.catch_me_study.domain.board.dto.BoardDto;
import com.catch_me_study.catch_me_study.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardDto> getBoardList() {
        return boardService.getBoardList();
    }

    @GetMapping("{id}")
    public BoardDto getBoardById(@PathVariable String id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public BoardDto createBoard(@RequestBody @Valid BoardDto boardDto) {
        return boardService.createBoard(boardDto);
    }

    @PutMapping
    public BoardDto updateBoard(@RequestBody @Valid BoardDto boardDto) {
        return boardService.updateBoard(boardDto);
    }

    @DeleteMapping("{id}")
    public String deleteBoardById(@PathVariable String id) {
        return boardService.deleteBoardById(id);
    }

}

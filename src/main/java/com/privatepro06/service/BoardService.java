package com.privatepro06.service;

import com.privatepro06.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> findAll();
    BoardDTO findByBno(Long bno);
    Long register(BoardDTO dto);
    void modify(BoardDTO dto);
    void remove(Long bno);
}

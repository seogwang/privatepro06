package com.privatepro06.service;

import com.privatepro06.dto.QnaDTO;

import java.util.List;

public interface QnaService {
    List<QnaDTO> findAll();
    QnaDTO findByQno(Long qno);
    Long register(QnaDTO dto);
    QnaDTO modify(QnaDTO dto);
    void remove(Long qno);
}

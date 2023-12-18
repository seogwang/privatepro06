package com.privatepro06.repository;

import com.privatepro06.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("공지사항 : " + i)
                    .content("이것은 공지사항 1의 내용입니다.")
                    .writer("관리자")
                    .build();
            boardRepository.save(board);
            log.info("BNO : " + board.getBno());
        });
    }
}

package com.privatepro06.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long bno;

    @Size(max = 200)
    private String title;

    @Size(max = 2000)
    private String content;

    @Size(max = 50)
    private String writer;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}

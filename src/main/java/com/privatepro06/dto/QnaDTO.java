package com.privatepro06.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    private Long qno;

    @Size(max = 200)
    private String title;

    @Size(max = 2000)
    private String content;


}

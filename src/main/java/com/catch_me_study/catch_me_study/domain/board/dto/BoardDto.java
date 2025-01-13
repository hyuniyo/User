package com.catch_me_study.catch_me_study.domain.board.dto;

import com.catch_me_study.catch_me_study.domain.common.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto extends BaseDto {

    @NotBlank
    @Length(max = 100)
    private String title;

    @Length(max = 5000)
    private String content;

    @NotBlank
    @Length(max = 50)
    private String writer;

}

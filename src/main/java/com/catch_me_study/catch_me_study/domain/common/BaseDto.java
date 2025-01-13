package com.catch_me_study.catch_me_study.domain.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseDto {

    private String id;

    private LocalDateTime createDateTime;

    private LocalDateTime modifyDateTime;

    private Boolean isDeleted = false;

}
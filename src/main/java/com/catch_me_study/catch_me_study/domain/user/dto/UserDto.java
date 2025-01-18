package com.catch_me_study.catch_me_study.domain.user.dto;

//데이터 전송 객체
//클라이언트와 서버 간 데이터 주고받기
//클라이언트에게 보여주고 싶은 데이터만 전달
//DTO에는 클라이언트가 필요로 하는 정보만 포함

import com.catch_me_study.catch_me_study.domain.common.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor; //모든 필드를 초기화하는 생성자를 자동으로 생성
import lombok.Getter; //해당 클래스의 모든 필드에 대한 getter 메서드 자동 생성.
import lombok.NoArgsConstructor; //매개변수 없는 기본 생성자를 자동으로 생성
import lombok.experimental.SuperBuilder; // DTO를 빌더 패턴으로 만들기 쉽게 해줌.

//Getter 애너테이션이 없을 경우 public string getEmail(){ return email; } 이런식으로 수동으로 만들어줘야함
//SuperBuilder 이 없을 경우 생성자를 사용해야함
//ID가 포함되지 않는 이유는 BaseDto에 있기 떄문임. 클라이언트에서 받아와야하는 경우는 있기 때문에 있어야함.
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 80)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String name;
}

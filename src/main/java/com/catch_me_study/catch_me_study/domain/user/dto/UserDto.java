package com.catch_me_study.catch_me_study.domain.user.dto;

//데이터 전송 객체
//클라이언트와 서버 간 데이터 주고받기
//클라이언트에게 보여주고 싶은 데이터만 전달
//DTO에는 클라이언트가 필요로 하는 정보만 포함

import com.catch_me_study.catch_me_study.domain.common.BaseDto;
import jakarta.validation.constraints.AssertTrue;
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

    public interface Create {

    }

    public interface Update {

    }
    //유효성 검사를 그룹화하려는 목적으로 인터페이스 코드를 작성함
    //회원가입할 때는 email이 필수로 입력되어야 하고, 수정할 때는 email을 선택적으로 입력
    //Update 인터페이스는 그룹화를 위한 구분자 역할 / 실제로 해당 그룹을 사용하려면 유효성 검사 시점에만 @Validated(Update.class)와 같은 방식으로 적용

    private Boolean isDeleted = false;

    @Email
    @NotBlank(groups = Create.class)
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 80)
    private String password;

    @NotBlank
    @Size(max = 80)
    private String confirmPassword;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @AssertTrue(message = "비밀번호와 비밀번호 확인 값이 일치하지 않음")
    public Boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }
    //@AssertTrue는 유효성 검증을 위해 사용되는 애너테이션
    //해당 메서드가 true를 반환하는지 확인하는 역할
    //이 메서드는 boolean 값을 반환하는 메서드여야 함.
    //false를 반환하면 유효성 검증에 실패한 것
    //여기서는 false면 애너테이션에 지정된 메시지인 "비밀번호와 비밀번호 확인 값이 일치하지 않음"이 포함된 유효성 검사 실패가 발생
    //400 Bad Request 응답과 함께 오류 메시지가 전달

    //@NotBlank"를 쓰지 않고 "!=null" 이 대중적인 이유
    // "!=null"이 명시적으로 null 검사를 해주고 두 값을 비교하는 방식으로 대중화 개발 문화에서 선호함 (안전성 높임)
    //String str1 = null;  // null, 아무 값도 참조하지 않음
    //String str2 = "";    // 빈 문자열, 값은 있지만 비어있는 상태

}

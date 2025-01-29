package com.catch_me_study.catch_me_study.domain.user.controller;

// Service의 결과를 클라이언트로 전달
//클라이언트가 요청을 보내면 컨트롤러가 이를 처리하고 처리 결과를 응답하는 형태로 클라이언트에게 반환

import com.catch_me_study.catch_me_study.domain.user.dto.UserDto;
import com.catch_me_study.catch_me_study.domain.user.dto.UserDto.Create;
import com.catch_me_study.catch_me_study.domain.user.dto.UserDto.Update;
import com.catch_me_study.catch_me_study.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//기본적으로 컨트롤러 메서드에서 객체나 값만 반환하면 스프링이 HTTP 응답으로 변환
//이때 리스폰스엔티티를 사용하면 HTTP 상태 코드(200, 201 404) 등을 직접 반환할 수 있음
//ResponseEntity< >에서 <>사이에 있는건 그 데이터나 값을 포함하겠다는 것 예를 들어 <string>이라면 본문에 스트링으로 답을 하겠다.
//사용하지 않으면 기본적오로 200ok를 반ㅎ환함
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //컨트롤러가 반환할 때 json 로 클라이언트에게 전달
@RequestMapping("/users") //요거 뭘로 해야하는거지? // rest api는 명사를 복수형으로
@RequiredArgsConstructor //롬복 애너테이션으로 final이 붙은 필드를 생성자에 포함
public class UserController {

    private final UserService userService;

    //회원가입
    //POST는 리소스 생성, URL에 데이터 노출 X, 본문 통해 데이터 전달
    //ResponseEntity<String>> - HTTP응답을 나타내는 클래스, 클라이언트에게 어떤 데이터와 상태 코드를 반환할 것인가
    //아래 코드에서는 200 ok를 반환한 것
    //@RequestBody - 클라이언트와 서버는 데이터를 주고받을 때 보통 json 형식을 사용함 하지만 자바는 json 처리 불가 이때 애너테이션이 처리
    //Vaild - UserDto 에 정의된 애너테이션이 유효한가를 검증함 (ex 이메일이 비어잇는가)
//    @Vaild - 만약 유효성 검증이 실패했을 때에 대한 예외 처리 코드는 왜 없어도 되는건지?
//              -> 유효성 검사 실패는 MethodArgumentNotValidException 예외로 처리
//    이때 예외에 대한 처리 코드가 없어도 스프링에서는 자동으로 400 Bad Request 응답을 반환
//    예외를 다르게 처리하고 싶다면 예외 처리 코드 작성 필요

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody @Validated(Update.class) UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    //경로 변수로 사용자 ID를 전달받는다.

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id,
            @RequestBody @Validated(Create.class) UserDto userDto) {
        //@validated 그룹을 설정한 경우에는 @Vaild가 아니라 저렇게 해줘야함.
        //PathVariable id - URL 경로의 {id} 부분을 메서드의 매개변수로
        UserDto updateUser = userService.updateUser(id, userDto);
        //서비스에서 전달받은 ID에 해당하는 데이터 수정
        return ResponseEntity.ok(updateUser);
    }

    //비밀번호 확인 관련하여 - 컨트롤러에서 유효성 검사를 진행할 때 @Valid 어노테이션을 사용해주기 때문에, DTO의 검증이 통과하지 않으면 자동으로 예외가 발생


    //모든 사용자 조회
    @GetMapping //get은 리소스 조회에 사용됨
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers(); //모든 사용자 조회 결과를 리스트로 반환
        return ResponseEntity.ok(users);
    } //반환된 데이터를 json 형식으로 클라이언트에게 전달

    //특정 사용자 조회
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //회원 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("회원 삭제 완료");

    }

    //컨트롤러 메서드는 클라이언트와 소통되기 때문에 responseentity룰 죠로 사용함. 이걸 사용하면 상태 코드, 본문 등을 제어할 수 있기 때문
    //만약 void를 사용하면 클라이언트에게 아무런 응답 본문을 제공하기 어려움

}

package com.catch_me_study.catch_me_study.domain.user.controller;


// Service의 결과를 클라이언트로 전달

import com.catch_me_study.catch_me_study.domain.user.dto.UserDto;
import com.catch_me_study.catch_me_study.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러가 반환할 때 json 로 클라이언트에게 전달
@RequestMapping("/user") //요거 뭘로 해야하는거지?
@RequiredArgsConstructor //롬복 애너테이션으로 final이 붙은 필드를 생성자에 포함
public class UserController {

    private final UserService userService;

    //회원가입
    //POST는 리소스 생성, URL에 데이터 노출 X, 본문 통해 데이터 전달
    //ResponseEntity<String>> - HTTP응답을 나타내는 클래스, 클라이언트에게 어떤 데이터와 상태 코드를 반환할 것인가
    //아래 코드에서는 200 ok를 반환한 것
    //@RequestBody - 클라이언트와 서버는 데이터를 주고받을 때 보통 json 형식을 사용함 하지만 자바는 json 처리 불가 이때 애너테이션이 처리
    //Vaild - UserDto 에 정의된 애너테이션이 유효한가를 검증함 (ex 이메일이 비어잇는가)

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok("User Created successfully");

    }




}

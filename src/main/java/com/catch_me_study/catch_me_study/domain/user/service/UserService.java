//비즈니스 로직을 처리한다
//repository는 데이터베이스의 인터페이스를 제공하지 실제 비즈니스 로직 처리는 service에서 한다

package com.catch_me_study.catch_me_study.domain.user.service;

import com.catch_me_study.catch_me_study.domain.user.entity.UserEntity;
import com.catch_me_study.catch_me_study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    //private final UserRepository userRepository;

    //의존성 주입의 필요성
    //UserRepository userRepository = new UserRepository(); 이런식으로 직접 만들면 매번 객체를 새롭게 만들어야하고 비효율적임
    //뿐만 아니라 유지보수가 어려움 하나바뀌면 전체 수정
    //이때 @RequiredArgsConstructor와 private final 을 합치면 스프링이 알아서 객체를 생성해줌 - 의존성 주입


    //회원 목록 조회
    //List<UserEntity> userEntities = userRepository.findByIsDeletedFalse();
//회원 리스트를 담는ㄷ다. 회원 객체들만 리스트에 담는다
    //데이터베이스에서 회원 목록 가져오는 메서드가 findbyisdeletedfalse



}

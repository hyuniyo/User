//비즈니스 로직을 처리한다
//repository는 데이터베이스의 인터페이스를 제공하지 실제 비즈니스 로직 처리는 service에서 한다

package com.catch_me_study.catch_me_study.domain.user.service;

import com.catch_me_study.catch_me_study.domain.user.dto.UserDto;
import com.catch_me_study.catch_me_study.domain.user.entity.UserEntity;
import com.catch_me_study.catch_me_study.domain.user.mapper.UserMapper;
import com.catch_me_study.catch_me_study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//비즈니스 로직 처리 + Mapper로 변환 수행
//컨트롤러에서 전달받은 요청 데이터를 처리하거나 데이터베이스와 상호작용하여 필요한 로직을 수행한다.
//컨트롤러는 요청/응답을 처리하고 실제 로직은 서비스에서 처리된다

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //의존성 주입의 필요성
    //UserRepository userRepository = new UserRepository(); 이런식으로 직접 만들면 매번 객체를 새롭게 만들어야하고 비효율적임
    //뿐만 아니라 유지보수가 어려움 하나바뀌면 전체 수정
    //이때 @RequiredArgsConstructor와 private final 을 합치면 스프링이 알아서 객체를 생성해줌 - 의존성 주입

    //createUser - DTO를 Entity로 변환 후 저장소에 저장한다
    //디티오를 엔티티로 변환하는 이유는 데이터베이스와 상호작용하는 객체는 엔티티여야하기 때문

    public void createUser(UserDto userDto){
        UserEntity userEntity = userMapper.toEntity(userDto);
        userRepository.save(userEntity);
        //save - JPA의 기본 메서드로 엔티티를 데이터베이스에 저장한다
    }


    //회원 목록 조회
    //List<UserEntity> userEntities = userRepository.findByIsDeletedFalse();
//회원 리스트를 담는다. 회원 객체들만 리스트에 담는다
    //데이터베이스에서 회원 목록 가져오는 메서드가 findbyisdeletedfalse


}

//비즈니스 로직을 처리한다
//repository는 데이터베이스의 인터페이스를 제공하지 실제 비즈니스 로직 처리는 service에서 한다

package com.catch_me_study.catch_me_study.domain.user.service;

import com.catch_me_study.catch_me_study.domain.user.dto.UserDto;
import com.catch_me_study.catch_me_study.domain.user.entity.UserEntity;
import com.catch_me_study.catch_me_study.domain.user.mapper.UserMapper;
import com.catch_me_study.catch_me_study.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    //회원가입
    public void createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);
        userRepository.save(userEntity);
        //save - JPA의 기본 메서드로 엔티티를 데이터베이스에 저장한다
    }

    //회원정보 수정
    //ID로 사용자 조회한다. 없을 경우 아래 예외를 발생시킨다.
    public UserDto updateUser(String id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("회원 조회 실패"));

        userEntity.update(userDto.getEmail(), userDto.getPassword(), userDto.getName());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    //전체 회원 조회
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findByIsDeletedFalse();
        return userMapper.toDto(users);
    }

    //특정 회원 조회
    public UserDto getUserById(String id) {
        UserEntity userEntity = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("특정 회원 조회 실패"));
        return userMapper.toDto(userEntity);

    }

    //회원 삭제
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("회원 조회 실패"));

        userEntity.softDelete();

        userRepository.save(userEntity);
        //isDeleted = true로 설정된 데이터는 조회 쿼리에서 제외
        //데이터베이스에서 물리적으로 삭제하지 않고, 삭제된 상태를 나타내는 플래그만 변경하는 방법
        //실제 데이터를 보관하면서도 사용자에게는 삭제된 것처럼 보이게 처리
        //그래서 레포지토리가ㄷ ek isdeletedfasle 이런 네임인듯?
    }


}

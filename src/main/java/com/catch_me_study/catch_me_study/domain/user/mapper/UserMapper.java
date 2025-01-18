package com.catch_me_study.catch_me_study.domain.user.mapper;
//MapStruct 라는 JAVA 라이브러리 사용하여 매핑 자동화하는 Mapper 인터페이스

//Entity는 데이터베이스와의 직접적인 매핑 객체
//DTO(Data Transfer Object)는 클라이언트와 데이터를 주고받을 때 사용

//Entity를 클라이언트와 직접 주고받으면 보안/유지보수 문제가 생길 수 있으므로, 데이터를 변환하는 작업이 필요
//수작업으로 Entity ↔ DTO 변환 코드를 작성하는 것은 반복적이고 오류 가능성이 높음
//MapStruct는 이 과정을 자동화

//인터페이스에 메서드만 정의하면 컴파일 시점에 변환 코드가 생성
//런타임에 Reflection을 사용하지 않아 성능이 뛰어남 -> 무슨 말인지 모르겠음 ~!

import com.catch_me_study.catch_me_study.domain.user.dto.UserDto;
import com.catch_me_study.catch_me_study.domain.user.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

//관례적으로 변수명은 클래스명과 비슷하게 소문자로 시작하는 형태를 사용
@Mapper(componentModel = "spring")
public interface UserMapper {

    //Entity -> Dto
    UserDto toDto(UserEntity userEntity);

    //Dto -> Entity
    UserEntity toEntity(UserDto userDto);

    //Entity 리스트 -> Dto 리스트
    List<UserDto> toDto(List<UserEntity> userEntityList);


    //Dto 리스트 -> Entity 리스트
    List<UserEntity> toEntity(List<UserDto> userDtoList);
}

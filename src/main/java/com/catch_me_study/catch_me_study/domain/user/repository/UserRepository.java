package com.catch_me_study.catch_me_study.domain.user.repository;

import com.catch_me_study.catch_me_study.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//레포지토리의 역할은 데이터베이스와 상호작용(CRUD(Create, Read, Update, Delete))
import java.util.List;
import java.util.Optional;

//인터페이스로 레포지토리를 만드는 이유: 클래스로 만들면 자동구현기능을 못쓰고 CRUD 메서드를 수동으로 구현해야하지만
//인터페이스로 만들면 JPA 기능을 활용하여 개발 시간과 간결하게 코드를 작성할 수 있음
//그래서 인터페이스로 만들고 estends JpaRepository를 해주는듯
public interface UserRepository extends JpaRepository<UserEntity, String> {
    //UserEntity - 데이터베이스 테이블과 매핑되는 엔티티 클래스
    //String - UserEntity의 기본 키 타입 즉 id고 UUID로 관리되는데 UUID는 문자열 형식으로 많이 사용됨
    //JpaRepository 구문을 사용하면 Spring JPA가 기본적으로 CRUD 기능 제공함.

    List<UserEntity> findByIsDeletedFalse(); //isDeleted가 flase인 즉, 삭제되지 않은 사용자들 가져오는 메서드 LIst로 받았으니까 여러 사용자 정보를 받는다

    Optional<UserEntity> findByIdAndIsDeletedFalse(String id); //사용자가 삭제되지 않았고 그 주어진 id에 맞는 사용자가 존재할 때 그 사용자를 반환함
    //이때 Optional을 쓴 이유는 그 조건에 맞는 id 사용자가 없을 경우 null 포인터 예외를 방지하기 위해서임

}

package com.catch_me_study.catch_me_study.domain.board.repository;

import com.catch_me_study.catch_me_study.domain.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//인터페이스로 레포지토리를 만드는 이유: 클래스로 만들면 자동구현기능을 못쓰고 CRUD 메서드를 수동으로 구현해야하지만
//인터페이스로 만들면 JPA 기능을 활용하여 개발 시간과 간결하게 코드를 작성할 수 있음
//그래서 인터페이스로 만들고 estends JpaRepository를 해주는듯
public interface UserRepository extends JpaRepository<UserEntity, String> {
}

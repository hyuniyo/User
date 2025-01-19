package com.catch_me_study.catch_me_study.domain.user.entity;

import com.catch_me_study.catch_me_study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Entity // JPA에서 Entity 클래스 임을 나타냄
@Table(name = "user") //데이터베이스의 테이블명
@Getter // 롬복 라이브러리 제공 기능, 클래스 내 모든 필드에 대해 getter 메서드 생성
@SuperBuilder //롬복 기능 중 하나, 빌더 패턴을 적용하는데 사용됨.
@NoArgsConstructor //롬복 기능, 매개변수가 없는 기본 생성자를 자동으로 만들어줌
@AllArgsConstructor //매개변수가 모두 있는 생성자를 만들어줌 ex new UserEntity(id, email, password, name)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid") //UUID 전 세계적으로 유일한 식별자를 생성하는 방법, uuid라는 이름만으로는 UUID 생성 방식을 어떻게 처리해야할지 모르기 때문에 아래 코드를 추가로 기재해준 것
    @GenericGenerator(name = "uuid", strategy = "uuid") //Hibernate에서 제공, strategy = "uuid"는 Hibername가 기본적으로 제공하는 생성 방식
    @Column(insertable = false, updatable = false, nullable = false, length = 255) //인설터블 - 새로운 레코드가 삽입될 때 데이터베이스에 추가되지 않음, 업데이터블 - 기존 레코드가 수정될 때 변경되지 않음
    @Comment("고유 ID (UUID)")
    private String id;

    @Column(length = 100, nullable = false)
    @Comment("이메일 주소(아이디)")
    private String email;

    @Column(length = 80, nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(length = 50, nullable = false)
    @Comment("사용자 이름")
    private String name;

    //confirmPassword는 받지 않아도 되는 이유
    //passwordConfirm은 DTO에서만 검증하고, 엔티티에는 비밀번호만 포함
    //passwordConfirm은 엔티티의 필드가 아니므로 update 메서드에서 받을 필요가 없음
    //DTO에서 비밀번호와 비밀번호 확인을 비교한 후, 유효성 검증을 통과하면 엔티티에 비밀번호만 업데이트
    //passwordConfirm 값은 DB에 저장되는 값이 아니고, 비밀번호 검증을 위한 임시적인 값이기 때문입니다. 실제로 비밀번호를 저장하는 것은 password 필드

    public void update(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Column(nullable = false)
    private Boolean isDeleted = false;


    public void softDelete() {
        this.isDeleted = true;

    }
}

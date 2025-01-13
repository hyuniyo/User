package com.catch_me_study.catch_me_study.domain.board.entity;

import com.catch_me_study.catch_me_study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="board")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(insertable = false, updatable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public void delete() {
        this.isDeleted = true;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

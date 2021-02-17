package com.ohzzi.todolist.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 사용자의 이름, 이메일 등 정보를 저장하는 Entity 클래스
 */
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    /**
     * Enum 값을 DB에 저장할때 Enum 순서에 따라 int 로 저장하기 때문에(ORDINAL)
     * EnumType.STRING 을 지정해 주어 enum 이름이 DB에 저장되도록 한다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User update(String name, String email) {
        this.name = name;
        this.email = email;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

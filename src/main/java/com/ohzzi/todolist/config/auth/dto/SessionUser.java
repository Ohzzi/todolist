package com.ohzzi.todolist.config.auth.dto;

import com.ohzzi.todolist.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 세션에 사용자의 정보를 저장할 Dto 클래스<br>
 * User 클래스를 그대로 세션에 저장하지 않고 SessionUser 를 대신 사용<br>
 * User 클래스는 엔티티이기 때문에 다른 엔티티와 관계가 형성될 수 있음<br>
 * 따라서 User 클래스에 직렬화 기능을 추가하는 것이 아니라 직렬화 기능을 가진 세션 Dto 를 추가로 만듦
 */

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

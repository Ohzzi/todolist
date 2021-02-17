package com.ohzzi.todolist.config.auth;

import com.ohzzi.todolist.config.auth.dto.OAuthAttributes;
import com.ohzzi.todolist.config.auth.dto.SessionUser;
import com.ohzzi.todolist.domain.user.User;
import com.ohzzi.todolist.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * 소셜 로그인 이후 가져온 사용자의 정보(이름, 이메일)를 기반으로 가입 및 정보 수정, 세션 저장 등의 역할을 하는 클래스
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    /**
     * User 정보 불러오는 메소드
     * <ul><li>
     * registrationId: 로그인 진행 중인 서비스를 구분하는 코드(구글 로그인과 네이버 로그인 구분 등)
     * </li><li>
     * userNameAttributeName: OAuth2 로그인 진행시 키 값(= Primary Key)
     * </li><li>
     * OAuthAttributes attributes: OAuth2User 의 attribute 를 담을 클래스
     * </li><li>
     * SessionUser: 세션에 사용자 정보 정의하는 Dto 클래스
     * </li></ul>
     * DefaultOAuth2User 객체를 생성하여 반환
      */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    /**
     * User 정보가 업데이트 될 경우 연동하는 메소드<br>
     * findByEmail 메소드를 통해 유저 정보를 가져와서 수정한 후 다시 userRepository 에 저장
     */
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getEmail()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}

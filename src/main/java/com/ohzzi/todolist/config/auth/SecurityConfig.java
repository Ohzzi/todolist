package com.ohzzi.todolist.config.auth;

import com.ohzzi.todolist.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * OAuth2 라이브러리를 이용한 소셜 로그인 설정 코드
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthSuccessHandler("/todolist");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* h2-console 화면을 사용하기 위해 csrf 와 frameOption 을 disable 한다 */
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                /* URL 별 유저의 권한을 지정한다. */
                .authorizeRequests()
                .antMatchers("/todolist").authenticated()
                .antMatchers("/api/v1/**").hasRole(Role.
                USER.name())
                .anyRequest().permitAll()
                //.anyRequest().authenticated() // 설정된 값 이외의 나머지 URL 들(anyRequest)을 인증된 사용자에게만 허용(authenticated)
                .and()
                /* 로그아웃 성공시 이동할 주소를 설정 */
                .logout().logoutSuccessUrl("/")
                .and()
                /* OAuth2 로그인 기능에 대한 처리 */
                .oauth2Login() // OAuth2 로그인 기능에 대한 설정 옵션 시작점
                .successHandler(successHandler())
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 구현체 등록
    }
}

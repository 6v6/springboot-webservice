package com.injeong.book.springboot.config.auth;

import com.injeong.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Sercurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console화면을 사용하기 위해 해당 옵션들을 disble
                .and()
                .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점 antMathers 옵션을 사용할수 있음
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리 대상을 지정
                .anyRequest().authenticated() //설정된 값들 이외 나머지 URL을 나타냄, 모두 인증된 사용자들에게만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공시 /주소로 이동
                .and()
                .oauth2Login()//로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint()//로그인 성공 이후에 사용자 정보를 가져올때 설정 담당
                .userService(customOAuth2UserService); //로그인 성공시 후속 조치를 진행할 UserService인터페이스의 구현체 등록
    }
}
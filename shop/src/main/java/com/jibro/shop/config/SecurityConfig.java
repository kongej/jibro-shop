package com.jibro.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ljy
 * @since 2024.05.22
 * spring security config class
 * 참고: https://hou27.tistory.com/entry/Spring-Boot-Spring-Security-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0-%EC%95%94%ED%98%B8%ED%99%94
 * **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// passwordEncoder를 bean으로 등록하기
	// BCypt 알고리즘 이용
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 인증 및 인가에 대해 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // post 방식으로 값 전송 시 token 사용해야 하는 규약해제
			.authorizeRequests()
			// .antMatchers("/order/check").authenticated() // /order/check 요청만 인증 요구
            .anyRequest().permitAll(); // 다른 모든 요청은 인증 없이 접근 가능
	}
	
}

package com.injeong.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//메인 클래스 - 프로젝트 최상단
@SpringBootApplication //스프링 부트의 자동 설정, 스프링Bean 읽기와 생성을 자동으로 설정.
public class Application {
    public static void main(String[] args) {
        //내장 WAS 실행 --> 언제 어디서나 같은 환경에서 스프링 부트를 배포
        SpringApplication.run(Application.class, args);
    }
}

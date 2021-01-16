package com.injeong.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Entity클래스에는 절대 Setter메소드를 만들지 않음.
//DB와 실제 매칭되는 클래스 Entity클래스.
@Getter
@NoArgsConstructor //기본 생성자 자동추가 public Posts(){}
@Entity //JPA의 어노테이션
public class Posts extends BaseTimeEntity{

    @Id //Pk
    @GeneratedValue( strategy = GenerationType.IDENTITY) //Pk 생성규칙 auto_increment
    private Long id;

    @Column(length = 500, nullable = false) //꼭 선언안해도 컬럼으로 인식, 기본값 이외의 추가사항이 있는 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}

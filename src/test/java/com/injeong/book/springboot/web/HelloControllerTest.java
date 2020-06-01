package com.injeong.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //테스트를 진행할 때 SpringRunner 실행자 사용
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired //빈 주입
    private MockMvc mvc; //웹 API 테스트

    @Test
    public void hello_가_리턴된다() throws Exception{
        String hello = "hello";

        // '/hello' 주소로 get요청을 함
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //결과 검증 200인지 아닌지
                .andExpect(content().string(hello)); //결과가 hello값이 맞는지 검증.
    }

    @Test
    public void helloDto_가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        // '/hello' 주소로 get요청을 함
        mvc.perform(
                get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk()) //결과 검증 200인지 아닌지
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}

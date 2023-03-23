package com.cos.blog.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//스프링은 com.cos.blog 패키지 밑에 특정 어노테이션이 붙어 있는 클래스 파일을 new 해서 IoC 컨테이너에서 관리한다
public class BlogControllerTest {

    //http://localhost:8080/test/hello
    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>hello spring boot</h1>";
    }

}
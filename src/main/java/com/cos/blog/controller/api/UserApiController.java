package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private HttpSession session;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출됨 ");
        //실제로 DB에 insert를 하고 아래에서 return
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 상태코드를 직접 적는 것보다 HttpStatus를 이용하는 것이 안전함
    }

//    //스프링 시큐리티를 이용해서 로그인하도록
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user){
//        System.out.println("UserApiController : login 호출됨 ");
//        //실제로 DB에 insert를 하고 아래에서 return
//        user.setRole(RoleType.USER);
//        User principal = userService.로그인(user);
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 상태코드를 직접 적는 것보다 HttpStatus를 이용하는 것이 안전함
//    }
}

package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 없는 id입니다. ";
        }

        return id + "번 고객이 삭제 되었습니다";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){ //요청으로 들어온 Json 데이터를 자바 객체로 변환해줌 (MessageConverter Jackson 라이브러리 작동)
        System.out.println("id = " + id);
        System.out.println("password = " + requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
                 return new IllegalArgumentException("수정에 실패하였습니다." );

        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());


        //userRepository.save(user);
        /*더티 체킹

         */


        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){// 요청할 때 각각의 객체 명을 적어서 보내면 매개변수로 매핑돼서 잘 들어감

        System.out.println("username = " + user.getUsername());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다. ";
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id: "  + id);
            }
        });
        /*
        요청을 웹 브라우저로 하는데 자바 객체를 보내기 때문에 웹 브라우저로 갈 때 변환을 해야 함
        원해는 지선 라이브러리를 이용해서 json으로 변환했는데
        스프링부트는 MessageConverter가 응답시에 자동으로 작동함
        만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        user 오브젝트를 json으로 변환해서 브라우저에 던짐
짐        */
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> PageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);


        List<User> users = pagingUser.getContent(); //필요 없는 내용 빼고
        return users;
    }
}

/**
 * User user = userRepository.findById(id).orElseThrow(() -> {
 *                 return new IllegalArgumentException("해당 유저는 없습니다. id: "  + id);
 *             }
 *         });
 *         return user;
 *     }
 *
 *
 *  User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
// *             @Override
 *             public User get() {
 *                 return new User();
 *             }
 *         });
 *         return user;
 *     }
 */
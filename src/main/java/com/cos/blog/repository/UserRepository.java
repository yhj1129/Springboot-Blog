package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//자동으로 빈 등록됨 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    //Select * from user where username = 1?;
    Optional<User> findByUsername(String username);
}




//JPA 네이밍 쿼리
// Select * from user where username = ?1 and password = ?2; 쿼리 발동함 물음표에 매개변수 들어감
// User findByUsernameAndPassword(String username, String password);

//    @Query(value = "Select * from user where username = ?1 and password = ?2", nativeQuery = true)
//    User login(String username, String password);
package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다 PrincipalDetail이 타입으로
public class PrincipalDetail implements UserDetails {
    private User user; //콤포지션

    public PrincipalDetail(User user) {
        this.user = user;
    }

    //계정의 권한 목록을 return 함 타입이 Collection<? extends GrantedAuthority>
    //권한이 여러개 있으면 루프를 돌아야 하는데 우리는 하나임
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_" + user.getRole(); //규칙임 ROLE_USER 리턴됨
//            }
//        });
        collectors.add(() ->{return "ROLE_" + user.getRole(); });

        return collectors;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴한다 true : 만료 안됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않았는지 리턴한다 true : 잠기지 않음
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다 true : 만료 안됨
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되었는지 true : 활성화됨
    @Override
    public boolean isEnabled() {
        return true;
    }
}

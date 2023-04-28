package com.creamcheese.crackers.jwt;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.exception.CustomException.AccountNotFoundException;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

@Data
public class PrincipalDetails implements UserDetails {

    private Account account;

    public PrincipalDetails(Account account) {
        this.account = account;
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return null;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collection = new ArrayList<>();
////        collection.add(new GrantedAuthority() {
////            @Override
////            public String getAuthority() {
////                return account.getStatus();
////            }
////        });
//        return collection;
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return account.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public String getName() {
//        return account.getLoginId();
//    }
}

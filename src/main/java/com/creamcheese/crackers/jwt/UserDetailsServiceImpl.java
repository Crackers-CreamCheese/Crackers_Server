package com.creamcheese.crackers.jwt;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account account = accountRepository.findByLoginId(loginId);
        if(account!=null) {
            return new PrincipalDetails(account);
        }
        return null;
    }

}

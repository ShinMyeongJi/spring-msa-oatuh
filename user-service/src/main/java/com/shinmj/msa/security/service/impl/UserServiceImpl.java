package com.shinmj.msa.security.service.impl;

import com.shinmj.msa.security.entity.UserEntity;
import com.shinmj.msa.security.repository.UserRepository;
import com.shinmj.msa.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName :  com.shinmj.msa.security.service.impl
 * fileName : UserServiceImpl
 * author :  home
 * date : 22. 9. 12.
 * description :
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 가진 사용자가 없습니다."));

        return user;
    }
}

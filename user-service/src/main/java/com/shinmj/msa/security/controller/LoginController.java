package com.shinmj.msa.security.controller;

import com.shinmj.msa.security.dto.LoginRequest;
import com.shinmj.msa.security.dto.LoginResponse;
import com.shinmj.msa.security.entity.UserEntity;
import com.shinmj.msa.security.service.impl.UserServiceImpl;
import com.shinmj.msa.security.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName :  com.shinmj.msa.security.controller
 * fileName : LoginController
 * author :  shinmj
 * date : 22. 9. 14.
 * description :
 */

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final UserServiceImpl userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {

        try{
            Authentication authentication =
                    authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()
                    ));
        }catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserId());

        final String token = jwtTokenProvider.createJwtAccessToken(userDetails.getUsername());

        return ResponseEntity.ok(LoginResponse.builder().token(token).build());
    }
}

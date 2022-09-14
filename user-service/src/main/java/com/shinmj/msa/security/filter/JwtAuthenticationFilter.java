package com.shinmj.msa.security.filter;

import com.shinmj.msa.security.service.UserService;
import com.shinmj.msa.security.service.impl.UserServiceImpl;
import com.shinmj.msa.security.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * packageName :  com.shinmj.msa.security.filter
 * fileName : JwtAuthenticationFilter
 * author :  home
 * date : 22. 9. 12.
 * description :
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwtToken = getJwtFromRequest(request);

            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateJwtToken(jwtToken)){
                String userId = jwtTokenProvider.getUserId(jwtToken);
                log.info("token : {}", jwtToken);
                log.info("userId : {}", userId);

                UserDetails userDetails = userService.loadUserByUsername(userId);

                log.info("userDetails : {}", userDetails);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                         = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}

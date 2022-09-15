package com.shinmj.msa.security.filter;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * packageName :  com.shinmj.msa.security.filter
 * fileName : JwtAuthenticationFilter
 * author :  shinmj
 * date : 22. 9. 15.
 * description :
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {


    @Value("${token.secret}")
    private String secretKey;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                onError(exchange, "JWT token doesn't exist" ,HttpStatus.UNAUTHORIZED);
            }


            String token = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0).replace("Bearer ", "");
            if(!isJwtValid(token)){
                onError(exchange, "JWT token is invalid" ,HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        });
    }

    private boolean isJwtValid(String token) {
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            log.error("Invalid JWT signature : {}", e.getMessage());
            return false;
        }catch (MalformedJwtException e) {
            log.error("Invalid JWT token : {}", e.getMessage());
            return false;
        }catch(ExpiredJwtException e) {
            log.error("JWT token is expired : {}", e.getMessage());
            return false;
        }catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported : {}", e.getMessage());
            return false;
        }catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty : {}", e.getMessage());
            return false;
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }


    public static class Config {

    }
}

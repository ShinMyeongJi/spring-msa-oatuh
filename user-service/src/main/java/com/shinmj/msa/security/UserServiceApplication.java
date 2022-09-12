package com.shinmj.msa.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * packageName :  PACKAGE_NAME
 * fileName : UserServiceApplication
 * author :  shinmj
 * date : 22. 9. 8.
 * description :
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

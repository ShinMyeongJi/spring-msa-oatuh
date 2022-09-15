package com.shinmj.msa.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName :  com.shinmj.msa.security.controller
 * fileName : FirstServiceController
 * author :  shinmj
 * date : 22. 9. 15.
 * description :
 */
@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

    @GetMapping("/one")
    public String getFirst() {
        return "Hi, there. It's first service.";
    }
}

package com.shinmj.msa.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * packageName :  com.shinmj.msa.security.dto
 * fileName : LoginResponse
 * author :  home
 * date : 22. 9. 14.
 * description :
 */
@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String token;
}

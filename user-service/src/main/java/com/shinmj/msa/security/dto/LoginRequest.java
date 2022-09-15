package com.shinmj.msa.security.dto;

import lombok.Builder;
import lombok.Data;

/**
 * packageName :  com.shinmj.msa.security.dto
 * fileName : LoginRequest
 * author :  shinmj
 * date : 22. 9. 14.
 * description :
 */
@Data
@Builder
public class LoginRequest {
    private String userId;
    private String password;
}

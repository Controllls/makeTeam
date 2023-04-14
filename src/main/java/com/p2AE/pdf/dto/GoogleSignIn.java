package com.p2AE.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor // 테스트때만 사용
public class GoogleSignIn {

    private String refresh_token;
    private String access_token;
    private int expires_in;
    private String scope;
    private String token_type;


}

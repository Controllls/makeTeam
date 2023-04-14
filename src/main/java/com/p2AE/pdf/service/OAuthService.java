package com.p2AE.pdf.service;

import com.p2AE.pdf.config.SocialLoginType;
import com.p2AE.pdf.domain.GoogleOauth;
import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.dto.GetSocialOAuthRes;
import com.p2AE.pdf.dto.GoogleOAuthToken;
import com.p2AE.pdf.dto.GoogleSignIn;
import com.p2AE.pdf.dto.GoogleUser;
import com.p2AE.pdf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthService {
    private final GoogleOauth googleOauth;

    private final MemberRepository memberRepository;
    private final HttpServletResponse response;
//    JwtService jwtService = new JwtService();


    public void request(SocialLoginType socialLoginType) {
        String redirectURL;
        switch (socialLoginType) {
            case GOOGLE: {
                redirectURL = googleOauth.getOauthRedirectURL();
            }
            break;
//            case FACEBOOK: {
//                redirectURL = facebookOauth.getOauthRedirectURL();
//            } break;
//            case KAKAO: {
//                redirectURL = kakaoOauth.getOauthRedirectURL();
//            } break;
//            case NAVER: {
//                redirectURL = naverOauth.getOauthRedirectURL();
//            } break;
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
        }
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GetSocialOAuthRes oAuthLogin(SocialLoginType socialLoginType, String code) throws IOException {

        switch (socialLoginType) {
            case GOOGLE: {
                //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
                ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
                //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
                GoogleOAuthToken oAuthToken = googleOauth.getAccessToken(accessTokenResponse);



                //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
                ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
                //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
                GoogleUser googleUser = googleOauth.getUserInfo(userInfoResponse);
                String user_id=googleUser.getEmail();

//                return googleUser;

                //우리 서버의 db와 대조하여 해당 user가 존재하는 지 확인한다.
                List<Member> byName = memberRepository.findByEmail(user_id);
                if(byName != null){
                    //서버에 user가 존재하면 앞으로 회원 인가 처리를 위한 jwtToken을 발급한다.

                    //액세스 토큰과 jwtToken, 이외 정보들이 담 자바 객체를 다시 전송한다.
                    return new GetSocialOAuthRes(oAuthToken.getId_token(),byName.get(0).getId(),oAuthToken.getAccess_token(),oAuthToken.getToken_type());
                }
                else {
                    throw new IOException("BaseResponseStatus.ACCOUNT_DOESNT_EXISTS");
                }

            }
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
        }
    }



    public GoogleSignIn requestLoginUserInfo(SocialLoginType socialLoginType, String code) throws IOException {
        switch (socialLoginType) {
            case GOOGLE: {
                ResponseEntity<String> accessTokenResponse= googleOauth.requestAccessToken(code);
                return googleOauth.getAccessTokenAndRefreshToken(accessTokenResponse);
            }
//            case FACEBOOK: {
//                return facebookOauth.requestAccessToken(code);
//            }
//            case KAKAO: {
//                return kakaoOauth.requestAccessToken(code);
//            }
//            case NAVER: {
//                return naverOauth.requestAccessToken(code);
//            }
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
        }
    }
}
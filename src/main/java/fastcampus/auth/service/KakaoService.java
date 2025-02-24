package fastcampus.auth.service;

import fastcampus.auth.model.KakaoTokenResponseDto;
import fastcampus.auth.model.KakaoUserInfoResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoService {
    @Value("${kakao.client_id}")
    private String CLIENT_ID;
    @Value("${kakao.redirect_uri}")
    private String REDIRECT_URI;

    private final String KAKAO_AUTH_URL = "https://kauth.kakao.com";
    private final String KAKAO_USER_URL = "https://kapi.kakao.com";

    public KakaoUserInfoResponseDto getUserFromKakao(String accessToken){
        return WebClient.create(KAKAO_USER_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();
    }

    public String getAccessTokenFromKakao(String code){
        KakaoTokenResponseDto kakoTokenResponseDto =
                WebClient.create(KAKAO_AUTH_URL)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", CLIENT_ID)
                        .queryParam("redirect_uri", REDIRECT_URI)
                        .queryParam("code", code)
                        .build())
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        return kakoTokenResponseDto.getAccessToken();
    }

}

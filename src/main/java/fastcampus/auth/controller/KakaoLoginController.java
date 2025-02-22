package fastcampus.auth.controller;

import fastcampus.auth.model.KakaoUserInfoResponseDto;
import fastcampus.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/kakao/callback")
    public ResponseEntity callback(@RequestParam String code){
        log.info("code : {}", code);
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        log.info("accessToken : {}", accessToken);
        KakaoUserInfoResponseDto dto = kakaoService.getUserFromKakao(accessToken);
        log.info("nickName : {}", dto.getKakaoAccount().getProfile().getNickName());
        return new ResponseEntity(HttpStatus.OK);
    }
}

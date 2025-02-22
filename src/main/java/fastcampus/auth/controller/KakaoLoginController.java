package fastcampus.auth.controller;

import fastcampus.auth.model.KakaoUserInfoResponseDto;
import fastcampus.auth.repository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;

    @GetMapping("/kakao/callback")
    public ResponseEntity callback(@RequestParam String code){
        log.info("code : {}", code);
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        log.info("accessToken : {}", accessToken);
        KakaoUserInfoResponseDto dto = kakaoService.getUserFromKakao(accessToken);
        String nickName = dto.getKakaoAccount().getProfile().getNickName();
        if (employeeRepository.existsByKakaoNickName(nickName)) {
            return new ResponseEntity("환영합니다 " + nickName + "님", HttpStatus.OK);
        } else {
            return new ResponseEntity("등록된 사원이 아닙니다",HttpStatus.FORBIDDEN);
        }
    }
}

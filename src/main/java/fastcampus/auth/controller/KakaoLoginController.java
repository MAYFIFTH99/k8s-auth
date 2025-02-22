package fastcampus.auth.controller;

import fastcampus.auth.model.KakaoUserInfoResponseDto;
import fastcampus.auth.repository.EmployeeRepository;
import fastcampus.auth.service.KakaoService;
import fastcampus.auth.service.LoginService;
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

    private final LoginService loginService;

    @GetMapping("/kakao/callback")
    public ResponseEntity callback(@RequestParam String code){
        return loginService.login(code);
    }
}

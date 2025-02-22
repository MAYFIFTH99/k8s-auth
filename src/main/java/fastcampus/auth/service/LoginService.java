package fastcampus.auth.service;

import fastcampus.auth.model.KakaoUserInfoResponseDto;
import fastcampus.auth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final KakaoService kakaoService;
    private final EmployeeRepository employeeRepository;

    public ResponseEntity login(String code){
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto dto = kakaoService.getUserFromKakao(accessToken);
        String nickName = dto.getKakaoAccount().getProfile().getNickName();
        if (employeeRepository.existsByKakaoNickName(nickName)) {
            return new ResponseEntity("환영합니다 " + nickName + "님", HttpStatus.OK);
        } else {
            return new ResponseEntity("등록된 사원이 아닙니다",HttpStatus.FORBIDDEN);
        }
    }

}

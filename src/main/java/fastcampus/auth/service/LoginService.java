package fastcampus.auth.service;

import fastcampus.auth.model.KakaoUserInfoResponseDto;
import fastcampus.auth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final KakaoService kakaoService;
    private final EmployeeRepository employeeRepository;

    public ResponseEntity login(String code){
        String token = kakaoService.getAccessTokenFromKakao(code);
        return new ResponseEntity(token, HttpStatus.OK);
    }

    public ResponseEntity getKakaoUser(String token){

        KakaoUserInfoResponseDto dto = kakaoService.getUserFromKakao(token);
        String nickName = dto.getKakaoAccount().getProfile().getNickName();
        if (employeeRepository.existsByKakaoNickName(nickName)) {
            return new ResponseEntity("환영합니다 " + nickName + "님", HttpStatus.OK);
        } else {
            return new ResponseEntity("가입되지 않은 사용자입니다.", HttpStatus.FORBIDDEN);
        }

    }

}

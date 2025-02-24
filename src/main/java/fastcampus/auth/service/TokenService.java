package fastcampus.auth.service;

import fastcampus.auth.config.CustomRateLimiter;
import fastcampus.auth.dto.AppTokenResponseDto;
import fastcampus.auth.dto.ValidateTokenDto;
import fastcampus.auth.model.Api;
import fastcampus.auth.model.App;
import fastcampus.auth.repository.ApiRepository;
import fastcampus.auth.repository.AppRepository;
import fastcampus.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private final AppRepository appRepository;
    private final ApiRepository apiRepository;
    private final CustomRateLimiter customRateLimiter;

    public AppTokenResponseDto createAppToken(Long appId){
        App app = appRepository.getById(appId);
        String token = JwtUtil.createAppToken(app);

        return AppTokenResponseDto.builder()
                .token(token)
                .build();
    }

    public ResponseEntity<String> validateToken(ValidateTokenDto dto) {
        Api api = apiRepository.findByMethodAndPath(dto.getMethod(), dto.getPath());
        ResponseEntity<String> resp = JwtUtil.validateAppToken(dto, api);

        if(resp.getStatusCode().is2xxSuccessful()){
            Long appId = Long.valueOf(JwtUtil.parseSubject(dto.getToken()));
            if(!customRateLimiter.tryConsume(appId, api.getId())){
                log.error("Too many requests");
                return new ResponseEntity<>("too many requests", HttpStatus.TOO_MANY_REQUESTS);
            }
        }
        return resp;
    }
}

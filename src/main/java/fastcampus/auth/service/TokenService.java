package fastcampus.auth.service;

import fastcampus.auth.dto.AppTokenResponseDto;
import fastcampus.auth.dto.ValidateTokenDto;
import fastcampus.auth.model.Api;
import fastcampus.auth.model.App;
import fastcampus.auth.repository.ApiRepository;
import fastcampus.auth.repository.AppRepository;
import fastcampus.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AppRepository appRepository;
    private final ApiRepository apiRepository;

    public AppTokenResponseDto createAppToken(Long appId){
        App app = appRepository.getById(appId);
        String token = JwtUtil.createAppToken(app);

        return AppTokenResponseDto.builder()
                .token(token)
                .build();
    }

    public ResponseEntity<String> validateToken(ValidateTokenDto dto) {
        Api api = apiRepository.findByMethodAndPath(dto.getMethod(), dto.getPath());
        return JwtUtil.validateAppToken(dto, api);
    }
}

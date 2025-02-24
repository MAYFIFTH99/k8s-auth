package fastcampus.auth.service;

import fastcampus.auth.dto.AppTokenResponseDto;
import fastcampus.auth.model.App;
import fastcampus.auth.repository.AppRepository;
import fastcampus.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AppRepository appRepository;
    public AppTokenResponseDto createAppToken(Long appId){
        App app = appRepository.getById(appId);
        String token = JwtUtil.createAppToken(app);

        return AppTokenResponseDto.builder()
                .token(token)
                .build();
    }

}

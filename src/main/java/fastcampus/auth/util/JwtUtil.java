package fastcampus.auth.util;

import fastcampus.auth.dto.ValidateTokenDto;
import fastcampus.auth.model.Api;
import fastcampus.auth.model.App;
import fastcampus.auth.model.AppRole;
import fastcampus.auth.model.Employee;
import fastcampus.auth.model.EmployeeRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createAppToken(App app){
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + 1000 * 60 * 60);

        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "app");
        claims.put("roles",
                app.getAppRoles().stream().map(AppRole::getApi).map(Api::getId).collect(Collectors.toSet()));

        return Jwts.builder()
                .subject(String.valueOf(app.getId()))
                .claims(claims)
                .issuedAt(now)
                .expiration(expiredAt)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String createUserToken(Employee employee) {

        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + 1000 * 60 * 60);

        Map<String, Object> claims = new HashMap<>();
        claims.put("kakaoNickName", employee.getKakaoNickName());

        if(employee.getEmployeeRoles() != null && !employee.getEmployeeRoles().isEmpty()){
            claims.put("roles", employee.getEmployeeRoles().stream().map(EmployeeRole::getName).collect(
                    Collectors.toSet()));
        }else{
            claims.put("roles", Collections.emptySet());
        }

        return Jwts.builder()
                .subject(String.valueOf(employee.getId()))
                .claims(claims)
                .issuedAt(now)
                .expiration(expiredAt)
                .signWith(SECRET_KEY)
                .compact();
    }


    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String parseSubject(String token){
        return parseToken(token).getSubject();
    }


    public static ResponseEntity<String> validateAppToken(ValidateTokenDto dto, Api api) {
        Claims claims;
        try {
            claims = parseToken(dto.getToken());
        } catch (Exception e) {
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
        }

        Date now = new Date();
        if (claims.getExpiration().before(now)) {
            return new ResponseEntity<>("token expired",HttpStatus.UNAUTHORIZED );
        }
        if (!StringUtils.equals("app", claims.get("type").toString())) {
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
        }

        String roles = claims.get("roles").toString();
        if (roles.contains(api.getId().toString())) {
            return new ResponseEntity<>("권한이 존재합니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);

        }
    }
}

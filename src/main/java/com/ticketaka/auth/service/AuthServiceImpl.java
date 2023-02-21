package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.response.LoginResponseDto;
import com.ticketaka.auth.feign.MemberFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final RedisService redisService;
    private final MemberFeignClient memberFeignClient;
    private final JwtUtils jwtUtils;
    public ResponseEntity<String> login(LoginRequestDto dto){
        ResponseEntity<LoginResponseDto> login = memberFeignClient.login(dto);
        Long memberId = login.getBody().getMemberId();

        String accessToken = jwtUtils.generateAccessToken(memberId);
        String refreshToken = jwtUtils.generateRefreshToken();


        //4. refresh 토큰을 Redis 에 저장 key - refreshToken value- memberId(String)
        redisService.setValues(refreshToken,memberId);

        // 5. 이후 토큰값을 헤더에 담아서 반환
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Authorization",accessToken);
        headers.add("R-Authorization", refreshToken);

        return ResponseEntity.ok()
                .headers(headers)
                .body("Success");
    }
}

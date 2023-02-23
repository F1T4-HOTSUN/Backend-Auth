package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.StatusCode;
import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.feign.MemberFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final RedisService redisService;
    private final MemberFeignClient memberFeignClient;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<BaseResponse> login(LoginRequestDto dto){
        BaseResponse login = memberFeignClient.login(dto);
        if(login.getCode()==200){
            //LoginResponseDto data = (LoginResponseDto) login.getData();
            //log.info("memberId - {} ", data.getMemberId());


            LinkedHashMap map = (LinkedHashMap) login.getData();
            log.info("map + {} " , map);

            Long memberId = Long.valueOf((Integer) map.get("memberId"));
            String role = (String) map.get("role");

            log.info("memberId - {}", memberId);
            log.info("role - {} ", role);
            String accessToken = jwtUtils.generateAccessToken(memberId);
            String refreshToken = jwtUtils.generateRefreshToken();


            //4. refresh 토큰을 Redis 에 저장 key - refreshToken value- memberId(String)
            //redisService.setValues(refreshToken,memberId);
            // 5. 이후 토큰값을 헤더에 담아서 반환
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Authorization",accessToken);
            headers.add("R-Authorization", refreshToken);
            return ResponseEntity.status(login.getCode()).headers(headers).body(new BaseResponse(StatusCode.OK));
        }
        return ResponseEntity.status(login.getCode()).body(login);

    }
    @Override
    public BaseResponse signUp(@RequestBody SignupRequestDto dto){
        return memberFeignClient.signUp(dto);

    }
    // 이메일 중복 체크
    @Override
    @PostMapping("/checkDuplicateEmail")
    public BaseResponse checkDuplicateMember(@RequestBody Map<String,String> email){
        log.info("email Test {} ", email);
        return memberFeignClient.checkDuplicateMember(email);

    }
    @Override
    public BaseResponse logout(@RequestHeader Map<String, String> header){
        redisService.deleteValue(header.get("r-authorization"));
        return new BaseResponse(StatusCode.OK);
    }
    @Override
    public BaseResponse info(@RequestHeader Map<String, String> header){
        String memberId = jwtUtils.getMemberIdFromHeader(header);
        return memberFeignClient.getInfo(Long.valueOf(memberId));
    }
}

package com.ticketaka.auth.controller;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.InfoResponseDto;
import com.ticketaka.auth.feign.MemberFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import com.ticketaka.auth.security.service.RedisService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberFeignClient memberFeignClient;
    private final JwtUtils jwtUtils;

    private final RedisService redisService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return memberFeignClient.login(dto);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequestDto dto){
       return memberFeignClient.signUp(dto);
    }
    // 이메일 중복 체크
    @PostMapping("/checkDuplicateEmail")
    public ResponseEntity<String> checkDuplicateMember(@RequestBody Map<String,String> email){
        log.info("email Test {} ", email);
        try{
            memberFeignClient.checkDuplicateMember(email);
        }catch(FeignException e){
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복");
        }
        return memberFeignClient.checkDuplicateMember(email);
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(@RequestHeader Map<String, String> header){
        redisService.deleteValue(header.get("r-authorization"));
        return ResponseEntity.ok("logout");
    }
    @GetMapping(path="/info")
    public ResponseEntity<InfoResponseDto> info(@RequestHeader Map<String, String> header){
        log.info("실행;");
        String memberId = jwtUtils.getMemberIdFromHeader(header);
        log.info("testing memberId  :{} ",memberId);
        return memberFeignClient.getInfo(Long.valueOf(memberId));
    }
    @GetMapping(path = "")
    public String info(){
        return "Hello";
    }
}

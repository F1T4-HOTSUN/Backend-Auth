package com.ticketaka.auth.controller;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.InfoResponseDto;
import com.ticketaka.auth.feign.MemberFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return memberFeignClient.login(dto);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequestDto dto){
       return memberFeignClient.signUp(dto);
    }
    // 이메일 중복 체크
    @GetMapping("/signup")
    public String checkDuplicateMember(@RequestParam String email){
        return memberFeignClient.checkDuplicateMember(email);
    }

//    @PostMapping(path = "/logout",headers = "HEADER")
//    public ResponseEntity<String> logout(@RequestHeader Map<String, String> header){
//        return memberFeignClient.logout();
//    }
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

    @GetMapping(path = "/adult",headers = "HEADER")
    public String checkAdult(@RequestHeader Map<String, String> header){
        return "adult";
    }

}

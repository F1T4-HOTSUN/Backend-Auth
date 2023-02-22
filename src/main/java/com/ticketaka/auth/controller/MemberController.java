package com.ticketaka.auth.controller;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.service.MemberService;
import com.ticketaka.auth.util.ResponseUtils;
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

    private final MemberService memberService;
    private final ResponseUtils responseUtils;
    
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequestDto dto){
        return memberService.login(dto);
    }
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signUp(@RequestBody SignupRequestDto dto){
        return responseUtils.makeResponse(memberService.signUp(dto));
    }
    // 이메일 중복 체크
    @PostMapping("/checkDuplicateEmail")
    public ResponseEntity<BaseResponse> checkDuplicateMember(@RequestBody Map<String,String> email){

        return responseUtils.makeResponse(memberService.checkDuplicateMember(email));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<BaseResponse> logout(@RequestHeader Map<String, String> header){
        return responseUtils.makeResponse(memberService.logout(header));
    }
    @GetMapping(path="/info")
    public ResponseEntity<BaseResponse> info(@RequestHeader Map<String, String> header){
        return responseUtils.makeResponse(memberService.info(header));
    }

}

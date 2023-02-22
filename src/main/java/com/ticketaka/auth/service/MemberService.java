package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.dto.response.InfoResponseDto;
import com.ticketaka.auth.dto.response.LoginResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public interface MemberService {
    ResponseEntity<BaseResponse> login(LoginRequestDto dto);
    BaseResponse signUp(@RequestBody SignupRequestDto dto);
    // 이메일 중복 체크

    BaseResponse checkDuplicateMember(@RequestBody Map<String,String> email);

    BaseResponse logout(@RequestHeader Map<String, String> header);

    BaseResponse info(@RequestHeader Map<String, String> header);

}

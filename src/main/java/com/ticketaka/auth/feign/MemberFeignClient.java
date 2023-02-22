package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.dto.response.InfoResponseDto;
import com.ticketaka.auth.dto.response.LoginResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name="MemberFeignClient", url="${member.url}"+":${member.port}" ,path="/member")
public interface MemberFeignClient {

    @PostMapping("/login")
    BaseResponse login(@RequestBody LoginRequestDto dto);
    @PostMapping("/signup")
    BaseResponse signUp(@RequestBody SignupRequestDto dto);
    // 이메일 중복 체크
    @PostMapping("/checkDuplicateEmail")
    BaseResponse checkDuplicateMember(@RequestBody Map<String,String> email);

    @PostMapping(path = "/logout",headers = "HEADER")
    BaseResponse logout(@RequestHeader Map<String, String> header);

    @PostMapping  (path="/info")
    BaseResponse getInfo(@RequestBody Long memberId);


}

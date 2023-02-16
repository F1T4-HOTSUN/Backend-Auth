package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import com.ticketaka.auth.dto.request.SignupRequestDto;
import com.ticketaka.auth.dto.response.InfoResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name="MemberFeignClient", url="${member.url}"+":${member.port}" ,path="/member")
public interface MemberFeignClient {

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginRequestDto dto);
    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody SignupRequestDto dto);
    // 이메일 중복 체크
    @GetMapping("/login")
    String checkDuplicateMember(@RequestParam String email);

    @PostMapping(path = "/logout",headers = "HEADER")
    ResponseEntity<String> logout(@RequestHeader Map<String, String> header);

    @GetMapping  (path="/info")
    ResponseEntity<InfoResponseDto> getInfo(@RequestBody Long memberId);

    @GetMapping(path = "/adult",headers = "HEADER")
    String checkAdult(@RequestHeader Map<String, String> header);

}

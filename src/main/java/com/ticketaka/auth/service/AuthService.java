package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.request.LoginRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> login(LoginRequestDto dto);

}

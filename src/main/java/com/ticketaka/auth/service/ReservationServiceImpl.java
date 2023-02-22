package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.feign.ReservationFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationFeignClient reservationFeignClient;
    private final JwtUtils jwtUtils;

    @Override
    public BaseResponse reservationList(@RequestHeader Map<String, String> header) {
        String memberId = jwtUtils.getMemberIdFromHeader(header);
        return reservationFeignClient.reservationList(memberId);
    }
    @Override
    public BaseResponse reservationInfo(@PathVariable("rsv_id") Long reservationId) {
        return reservationFeignClient.reservationInfo(reservationId);
    }
    @Override
    public BaseResponse deleteReservation(@PathVariable("rsv_id") Long reservationId) {
        return reservationFeignClient.deleteReservation(reservationId);
    }
}

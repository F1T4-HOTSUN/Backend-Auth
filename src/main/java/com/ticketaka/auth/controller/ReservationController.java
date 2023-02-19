package com.ticketaka.auth.controller;

import com.ticketaka.auth.dto.request.reservation.ReservationDTO;
import com.ticketaka.auth.dto.response.ReservationListDTO;
import com.ticketaka.auth.feign.ReservationFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationFeignClient reservationFeignClient;
    private final JwtUtils jwtUtils;
    @PostMapping("/create")
    public ResponseEntity<String> reservation(@RequestHeader Map<String,String> header, @RequestBody ReservationDTO dto) {
        dto.setMember_id(Long.valueOf(jwtUtils.getMemberIdFromHeader(header)));
        return reservationFeignClient.reservation(dto);
    }
    // ??
    @GetMapping("/list")
    public ResponseEntity<List<ReservationListDTO>> reservationList(@RequestHeader Map<String, String> header) {
        String memberId = jwtUtils.getMemberIdFromHeader(header);
        return reservationFeignClient.reservationList(memberId);
    }

    @GetMapping("/list/{rsv_id}")
    public ResponseEntity<ReservationListDTO> reservationInfo(@PathVariable("rsv_id") Long reservationId) {
        return reservationFeignClient.reservationInfo(reservationId);
    }

    @DeleteMapping("/delete/{rsv_id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("rsv_id") Long reservationId) {
        return reservationFeignClient.deleteReservation(reservationId);
    }
}
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
//
//    private final ReservationFeignClient reservationFeignClient;
//    private final JwtUtils jwtUtils;
//    @PostMapping("/create")
//    public ResponseEntity<String> reservation(@RequestHeader Map<String,String> header, @RequestBody ReservationDTO dto) {
//        dto.setMember_id(Long.valueOf(jwtUtils.getMemberIdFromHeader(header)));
//        return reservationFeignClient.reservation(dto);
//    }
//    // ??
//    @GetMapping("/list")
//    public ResponseEntity<List<ReservationListDTO>> reservationList(@RequestBody Map<String, Long> memberMap) {
//        List<ReservationListDTO> reservationList = reservationService.getReservationList(memberMap.get("member_id"));
//        return ResponseEntity.ok(reservationList);
//    }
//
//    @GetMapping("/list/{rsv_id}")
//    public ResponseEntity<ReservationListDTO> reservationInfo(
//            @PathVariable("rsv_id") Long reservationId) {
//        ReservationListDTO reservationList = reservationService.getReservation(reservationId);
//        return ResponseEntity.ok(reservationList);
//    }
//
//    @DeleteMapping("/delete/{rsv_id}")
//    public ResponseEntity<String> deleteReservation(
//            @PathVariable("rsv_id") Long reservationId
//    ) {
//        return reservationService.deleteReservation(reservationId);
//    }
}
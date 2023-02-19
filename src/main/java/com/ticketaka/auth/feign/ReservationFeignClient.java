package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.request.reservation.ReservationDTO;
import com.ticketaka.auth.dto.response.ReservationListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name="ReservationFeignClient", url="${reservation.url}"+":${reservation.port}" ,path="/reservation")
public interface ReservationFeignClient {
    @PostMapping("/create")
    ResponseEntity<String> reservation(@RequestBody ReservationDTO dto);
    @GetMapping("/list")
    ResponseEntity<List<ReservationListDTO>> reservationList(@RequestBody String memberId);

    @GetMapping("/list/{rsv_id}")
    ResponseEntity<ReservationListDTO> reservationInfo(@PathVariable("rsv_id") Long reservationId);

    @DeleteMapping("/delete/{rsv_id}")
    ResponseEntity<String> deleteReservation(@PathVariable("rsv_id") Long reservationId);
}

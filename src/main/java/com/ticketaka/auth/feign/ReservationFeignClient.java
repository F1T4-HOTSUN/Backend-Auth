package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.dto.response.ReservationListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name="ReservationFeignClient", url="${reservation.url}"+":${reservation.port}" ,path="/reservation")
public interface ReservationFeignClient {
    @GetMapping("/list")
    BaseResponse reservationList(@RequestBody String memberId);

    @GetMapping("/list/{rsv_id}")
    BaseResponse reservationInfo(@PathVariable("rsv_id") Long reservationId);

    @DeleteMapping("/delete/{rsv_id}")
    BaseResponse deleteReservation(@PathVariable("rsv_id") Long reservationId);
}

package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="ReservationFeignClient", url="${reservation.url}"+":${reservation.port}" ,path="/reservation")
public interface ReservationFeignClient {
    @GetMapping("/lists/{member_id}")
    BaseResponse reservationList(@PathVariable("member_id") Long memberId);

    @GetMapping("/list/{rsv_id}")
    BaseResponse reservationInfo(@PathVariable("rsv_id") Long reservationId);

    @DeleteMapping("/delete/{rsv_id}")
    BaseResponse deleteReservation(@PathVariable("rsv_id") Long reservationId);
}

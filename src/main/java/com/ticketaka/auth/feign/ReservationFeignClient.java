package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.request.reservation.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name="ReservationFeignClient", url="${reservation.url}"+":${reservation.port}" ,path="/reservation")
public interface ReservationFeignClient {
    @PostMapping("/create")
   ResponseEntity<String> reservation(@RequestBody ReservationDTO dto);
}

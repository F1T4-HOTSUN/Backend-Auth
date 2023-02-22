package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.request.performance.ReservationRequest;
import com.ticketaka.auth.dto.request.performance.WaitingListRequest;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.feign.PerformanceFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

public interface PerformanceService {

    BaseResponse getPerformanceById(@RequestParam(value = "p") String performanceId);

    BaseResponse getPrfSessionById(@PathVariable(value = "id") int prfSessionId);

    BaseResponse checkReservation(@RequestHeader Map<String,String> header,
                                                  @RequestBody WaitingListRequest request);

    BaseResponse withdrawReservation(@RequestHeader Map<String,String> header,
                                                     @RequestBody WaitingListRequest request);
    BaseResponse createReservation(@RequestHeader Map<String,String> header,
                                                   @RequestBody ReservationRequest request);

    BaseResponse getPrfByKeyword(@RequestParam(name = "keyword") String keyword,
                                                 @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable);

    @GetMapping("/cat")
    BaseResponse getPrfByGenre(
            @RequestParam(name = "genre") String genre,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable);

}

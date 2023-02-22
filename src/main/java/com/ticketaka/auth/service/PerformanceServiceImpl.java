package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.request.performance.ReservationRequest;
import com.ticketaka.auth.dto.request.performance.WaitingListRequest;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.feign.PerformanceFeignClient;
import com.ticketaka.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
@Slf4j
public class PerformanceServiceImpl implements PerformanceService{
    private final PerformanceFeignClient performanceFeignClient;
    private final JwtUtils jwtUtils;


    public BaseResponse getPerformanceById(@RequestParam(value = "p") String performanceId) {
        return performanceFeignClient.getPerformanceById(performanceId);

        // return ResponseEntity.ok().body(performanceById);
    }

    public BaseResponse getPrfSessionById(@PathVariable(value = "id") int prfSessionId) {
        return performanceFeignClient.getPrfSessionById(prfSessionId);
    }


    public BaseResponse checkReservation(@RequestHeader Map<String,String> header, @RequestBody WaitingListRequest request) {
        request.setMemberId(jwtUtils.getMemberIdFromHeader(header));
        return performanceFeignClient.checkReservation(request);
    }

    public BaseResponse withdrawReservation(@RequestHeader Map<String,String> header, @RequestBody WaitingListRequest request) {
        request.setMemberId(jwtUtils.getMemberIdFromHeader(header));
        return performanceFeignClient.withdrawReservation(request);
    }

    public BaseResponse createReservation(@RequestHeader Map<String,String> header, @RequestBody ReservationRequest request) {
        request.setMemberId(jwtUtils.getMemberIdFromHeader(header));
        return performanceFeignClient.createReservation(request);
    }

    public BaseResponse getPrfByKeyword(@RequestParam(name = "keyword") String keyword,
                                                        @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {
        return performanceFeignClient.getPrfByKeyword(keyword, pageable);
    }


    public BaseResponse getPrfByGenre(
            @RequestParam(name = "genre") String genre,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {
        return performanceFeignClient.getPrfByGenre(genre, pageable);

    }

}

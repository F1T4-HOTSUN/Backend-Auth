package com.ticketaka.auth.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController

@RequestMapping("/performance")
@RequiredArgsConstructor
@Slf4j
public class PerformanceController {
    private final PerformanceFeignClient performanceFeignClient;
    private final JwtUtils jwtUtils;
    @GetMapping("")
    @JsonCreator
    public ResponseEntity<String> getPerformanceById(@RequestParam(value = "p") String performanceId) {
        String performanceById = performanceFeignClient.getPerformanceById(performanceId);
        log.info("반환받은값 - {}", performanceById);
        return ResponseEntity.ok().body(performanceById);
    }
    @GetMapping("/session/{id}")
    public ResponseEntity<BaseResponse> getPrfSessionById(@PathVariable(value = "id") int prfSessionId) {
        return performanceFeignClient.getPrfSessionById(prfSessionId);
    }

    @PostMapping("/check")
    public ResponseEntity<BaseResponse> checkReservation(@RequestHeader Map<String,String> header, @RequestBody WaitingListRequest request) {
        request.setMemberId(jwtUtils.getMemberIdFromHeader(header));
        return performanceFeignClient.checkReservation(request);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BaseResponse> withdrawReservation(@RequestHeader Map<String,String> header, @RequestBody WaitingListRequest request) {
        request.setMemberId(jwtUtils.getMemberIdFromHeader(header));
        return performanceFeignClient.withdrawReservation(request);
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createReservation(@RequestHeader Map<String,String> header, @RequestBody ReservationRequest request) {
        request.setMemberId(jwtUtils.getMemberIdFromHeader(header));
        return performanceFeignClient.createReservation(request);
    }
    @GetMapping("/search")
    public ResponseEntity<BaseResponse> getPrfByKeyword(@RequestParam(name = "keyword") String keyword,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {
        return performanceFeignClient.getPrfByKeyword(keyword, pageable);
    }

    @GetMapping("/cat")
    public ResponseEntity<BaseResponse> getPrfByGenre(
            @RequestParam(name = "genre") String genre,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {

        return performanceFeignClient.getPrfByGenre(genre, pageable);
    }
}

package com.ticketaka.auth.controller;

import com.ticketaka.auth.dto.request.performance.ReservationRequest;
import com.ticketaka.auth.dto.request.performance.WaitingListRequest;
import com.ticketaka.auth.dto.response.BaseResponse;
import com.ticketaka.auth.service.PerformanceService;
import com.ticketaka.auth.util.ResponseUtils;
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
    private final PerformanceService performanceService;
    private final ResponseUtils responseUtils;
    @GetMapping("")
    public ResponseEntity<BaseResponse> getPerformanceById(@RequestParam(value = "p") String performanceId) {
        return responseUtils.makeResponse(performanceService.getPerformanceById(performanceId));

       // return ResponseEntity.ok().body(performanceById);
    }
    @GetMapping("/session/{id}")
    public ResponseEntity<BaseResponse> getPrfSessionById(@PathVariable(value = "id") int prfSessionId) {
        return responseUtils.makeResponse(performanceService.getPrfSessionById(prfSessionId));
    }

    @PostMapping("/rsv/check")
    public ResponseEntity<BaseResponse> checkReservation(@RequestHeader Map<String,String> header, @RequestBody WaitingListRequest request) {
        return responseUtils.makeResponse(performanceService.checkReservation(header,request));
    }

    @PostMapping("rsv/withdraw")
    public ResponseEntity<BaseResponse> withdrawReservation(@RequestHeader Map<String,String> header, @RequestBody WaitingListRequest request) {
        return responseUtils.makeResponse(performanceService.withdrawReservation(header,request));
    }
    @PostMapping("/rsv/create")
    public ResponseEntity<BaseResponse> createReservation(@RequestHeader Map<String,String> header, @RequestBody ReservationRequest request) {
        return responseUtils.makeResponse(performanceService.createReservation(header,request));
    }
    @GetMapping("/search")
    public ResponseEntity<BaseResponse> getPrfByKeyword(@RequestParam(name = "keyword") String keyword,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {
        return responseUtils.makeResponse(performanceService.getPrfByKeyword(keyword, pageable));
    }

    @GetMapping("/cat")
    public ResponseEntity<BaseResponse> getPrfByGenre(
            @RequestParam(name = "genre") String genre,
            @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable) {
        return responseUtils.makeResponse(performanceService.getPrfByGenre(genre, pageable));
    }

}

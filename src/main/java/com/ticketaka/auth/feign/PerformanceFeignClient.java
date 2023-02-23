package com.ticketaka.auth.feign;

import com.ticketaka.auth.dto.request.performance.ReservationRequest;
import com.ticketaka.auth.dto.request.performance.WaitingListRequest;
import com.ticketaka.auth.dto.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@FeignClient(name="PerformanceFeignClient", url="${performance.url}"+":${performance.port}" ,path="/performance")
public interface PerformanceFeignClient {
    @GetMapping(value = "")
    BaseResponse getPerformanceById(@RequestParam(value = "p") String performanceId);
    @GetMapping("/session/{id}")
    BaseResponse getPrfSessionById(@PathVariable(value = "id") int prfSessionId);

    @PostMapping("/rsv/check")
    BaseResponse checkReservation(@RequestBody WaitingListRequest request);

    @PostMapping("/rsv/withdraw")
    BaseResponse withdrawReservation(@RequestBody WaitingListRequest request);

    @PostMapping("/rsv/create")
    BaseResponse createReservation(@RequestBody ReservationRequest request);

    @GetMapping("/search")
    BaseResponse getPrfByKeyword(@RequestParam(name = "keyword") String keyword, @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable);
    @GetMapping("/cat")
    BaseResponse getPrfByGenre(@RequestParam(name = "genre") String genre, @PageableDefault(size = 20, sort = "prfLoadedAt", direction = DESC) Pageable pageable);

}

package com.ticketaka.auth.service;

import com.ticketaka.auth.dto.response.BaseResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public interface ReservationService {
    BaseResponse reservationList(@RequestHeader Map<String, String> header);

    BaseResponse reservationInfo(@PathVariable("rsv_id") Long reservationId);

    BaseResponse deleteReservation(@PathVariable("rsv_id") Long reservationId);
}

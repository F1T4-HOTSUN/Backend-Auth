package com.ticketaka.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketaka.auth.dto.PerformanceStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//@JsonPropertyOrder({"code", "message", "data"})
public class BaseResponse<T> {
    private PerformanceStatusCode code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(PerformanceStatusCode code) {
        this.code = code;
    }
}
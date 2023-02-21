package com.ticketaka.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ticketaka.auth.dto.PerformanceStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@JsonPropertyOrder({"code", "message", "data"})
@NoArgsConstructor
public class BaseResponse<T> {
    private int code;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(PerformanceStatusCode statusCode) {
        this.code = statusCode.getCode();
        this.description = statusCode.getDescription();
    }

    public BaseResponse(PerformanceStatusCode statusCode,T data) {
        this.code = statusCode.getCode();
        this.description = statusCode.getDescription();
        this.data = data;
    }
}
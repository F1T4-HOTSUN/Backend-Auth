package com.ticketaka.auth.util;

import com.ticketaka.auth.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {
    public ResponseEntity<BaseResponse> makeResponse(BaseResponse baseResponse){
        return ResponseEntity.status(baseResponse.getCode()).body(baseResponse);
    }
}

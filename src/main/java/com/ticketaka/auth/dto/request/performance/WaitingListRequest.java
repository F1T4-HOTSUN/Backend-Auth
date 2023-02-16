package com.ticketaka.auth.dto.request.performance;


import lombok.*;

@Getter
@Setter
@Builder
public class WaitingListRequest {
    private String memberId;
    private int prfSessionId;
    private int count;

}
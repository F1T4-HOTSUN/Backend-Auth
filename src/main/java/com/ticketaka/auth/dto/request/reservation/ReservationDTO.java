package com.ticketaka.auth.dto.request.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ReservationDTO {
    private Long member_id;
    private String member_email;
    private String performance_id;
    private int reservation_ticket_count;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservation_date;
    private String reservation_time;
    private int reservation_price;
    private String reservation_poster;
    private String reservation_createAt;
}
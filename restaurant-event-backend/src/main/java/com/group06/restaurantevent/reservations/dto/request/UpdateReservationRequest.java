package com.group06.restaurantevent.reservations.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateReservationRequest {
    @FutureOrPresent
    private LocalDate reservationDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Min(1)
    private Integer guestCount;

    private String seatingPreference;
    private String specialRequest;
    private String contactName;
    @Size(max = 20)
    @Pattern(regexp = " *(?:0|\\+94) *[1-9](?: *[0-9]){8} *",
            message = "Enter a Sri Lankan phone number, e.g. 0771234567 or +94 77 123 4567")
    private String contactPhone;
}

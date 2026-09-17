package com.group06.restaurantevent.reservations.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateReservationRequest {

    @NotNull
    private Long tableId;

    @NotNull @FutureOrPresent(message = "Reservation date cannot be in the past")
    private LocalDate reservationDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull @Min(1)
    private Integer guestCount;

    private String seatingPreference;
    private String specialRequest;

    @NotBlank
    private String contactName;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = " *(?:0|\\+94) *[1-9](?: *[0-9]){8} *",
            message = "Enter a Sri Lankan phone number, e.g. 0771234567 or +94 77 123 4567")
    private String contactPhone;
}

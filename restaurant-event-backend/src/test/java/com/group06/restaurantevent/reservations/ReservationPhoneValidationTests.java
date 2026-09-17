package com.group06.restaurantevent.reservations;

import com.group06.restaurantevent.reservations.dto.request.CreateReservationRequest;
import com.group06.restaurantevent.reservations.dto.request.UpdateReservationRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ReservationPhoneValidationTests {
    private static final jakarta.validation.ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    @AfterAll
    static void closeFactory() {
        factory.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0771234567", "+94771234567", "+94 77 123 4567", "011 234 5678", " 0771234567 "})
    void acceptsSupportedNumbers(String phone) {
        assertTrue(validator.validateValue(CreateReservationRequest.class, "contactPhone", phone).isEmpty());
        assertTrue(validator.validateValue(UpdateReservationRequest.class, "contactPhone", phone).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "       ", "1234567", "077123456", "07712345678", "+940771234567", "0000000000", "abcdefghij", "+++++++", "+447712345678", "0771234567\n"})
    void rejectsInvalidNumbersForCreateAndUpdate(String phone) {
        assertFalse(validator.validateValue(CreateReservationRequest.class, "contactPhone", phone).isEmpty());
        assertFalse(validator.validateValue(UpdateReservationRequest.class, "contactPhone", phone).isEmpty());
    }

    @Test
    void phoneIsRequiredOnCreateButMayBeOmittedOnUpdate() {
        assertFalse(validator.validateValue(CreateReservationRequest.class, "contactPhone", (String) null).isEmpty());
        assertTrue(validator.validateValue(UpdateReservationRequest.class, "contactPhone", (String) null).isEmpty());
    }
}

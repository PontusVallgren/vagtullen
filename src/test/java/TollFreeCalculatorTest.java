package edu.lernia.labb4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TollFreeCalculatorTest {

    @Test
    @DisplayName("Toll during free hours")
    void checkNoTollCharges() {
        LocalDateTime date = LocalDateTime.of(2020, Month.JUNE, 30, 00, 00);
        assertEquals(0, TollFeeCalculator.getTollFeePerPassing(date));
    }

    @Test
    @DisplayName("Multiple passages, max payment per day")
    void checkMaxCharges() {
        LocalDateTime[] date = new LocalDateTime[5];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 30, 7, 00);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 30, 8, 30);
        date[2] = LocalDateTime.of(2020, Month.JUNE, 30, 9, 40);
        date[3] = LocalDateTime.of(2020, Month.JUNE, 30, 15, 30);
        date[4] = LocalDateTime.of(2020, Month.JUNE, 30, 17, 30);
        assertEquals(60, TollFeeCalculator.getTotalFeeCost(date));
    }

    @Test
    @DisplayName("Multiple passages, during one day")
    void checkMultipleChargesDuringOneDay() {
        LocalDateTime[] date = new LocalDateTime[3];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 30, 6, 00);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 30, 8, 30);
        date[2] = LocalDateTime.of(2020, Month.JUNE, 30, 18, 00);
        assertEquals(24, TollFeeCalculator.getTotalFeeCost(date));
    }

    @Test
    @DisplayName("No charges during weekends")
    void checkNoChargeWeekends() {
        LocalDateTime[] date = new LocalDateTime[2];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 27, 7, 00);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 28, 15, 30);
        assertEquals(0, TollFeeCalculator.getTotalFeeCost(date));
    }

    @Test
    @DisplayName("No charges during July")
    void checkNoChargeDuringJuly() {
        LocalDateTime[] date = new LocalDateTime[2];
        date[0] = LocalDateTime.of(2020, Month.JULY, 2, 7, 00);
        date[1] = LocalDateTime.of(2020, Month.JULY, 5, 15, 30);
        assertEquals(0, TollFeeCalculator.getTotalFeeCost(date));
    }

    @Test
    @DisplayName("Pass multiple tolls during 1 hour")
    void checkMultiplePassagesDuringOneHour() {
        LocalDateTime[] date = new LocalDateTime[4];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 30, 7, 00);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 30, 7, 30);
        date[2] = LocalDateTime.of(2020, Month.JUNE, 30, 7, 45);
        date[3] = LocalDateTime.of(2020, Month.JUNE, 30, 7, 55);
        assertEquals(18, TollFeeCalculator.getTotalFeeCost(date));
    }


}

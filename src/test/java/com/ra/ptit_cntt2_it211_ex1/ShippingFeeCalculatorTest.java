package com.ra.ptit_cntt2_it211_ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ShippingFeeCalculatorTest {

    private final ShippingFeeCalculator calculator = new ShippingFeeCalculator();

    @Test
    @DisplayName("Kịch bản 1: Cân nặng <= 1kg và khoảng cách < 10km")
    void testWeightLessThanOneKgAndShortDistance() {
        double fee = calculator.calculateFee(0.8, 5.0);
        assertThat(fee).isEqualTo(50000.0);

        double feeEdge = calculator.calculateFee(1.0, 9.9);
        assertThat(feeEdge).isEqualTo(50000.0);
    }

    @Test
    @DisplayName("Kịch bản 2: Cân nặng > 1kg (số nguyên) và khoảng cách từ 10km đến dưới 50km")
    void testIntegerWeightAndMediumDistance() {
        double fee = calculator.calculateFee(3.0, 20.0);
        assertThat(fee).isEqualTo(170000.0);
    }

    @Test
    @DisplayName("Kịch bản 3: Cân nặng số lẻ và khoảng cách >= 50km")
    void testDecimalWeightAndLongDistance() {

        double fee1 = calculator.calculateFee(1.5, 60.0);
        assertThat(fee1).isEqualTo(300000.0);
        double fee2 = calculator.calculateFee(2.3, 55.0);
        assertThat(fee2).isEqualTo(290000.0);
    }

    @Test
    @DisplayName("Kịch bản 4: Kiểm thử chính xác tại các điểm biên khoảng cách (10km và 50km)")
    void testExactDistanceBoundaries() {

        double feeAt10km = calculator.calculateFee(1.0, 10.0);
        assertThat(feeAt10km).isEqualTo(100000.0);
        double feeAt50km = calculator.calculateFee(1.0, 50.0);
        assertThat(feeAt50km).isEqualTo(250000.0);
    }

    @Test
    @DisplayName("Kịch bản 5: Đầu vào không hợp lệ (<= 0) phải ném ngoại lệ")
    void testInvalidInputThrowsException() {
        assertThatThrownBy(() -> calculator.calculateFee(0, 15.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Weight and distance must be positive");

        assertThatThrownBy(() -> calculator.calculateFee(2.0, -5.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Weight and distance must be positive");
    }
}
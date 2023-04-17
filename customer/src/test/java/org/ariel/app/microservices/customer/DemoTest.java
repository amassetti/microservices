package org.ariel.app.microservices.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DemoTest {

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        // given
        int a = 20;
        int b = 30;

        // when
        int result = underTest.add(a, b);

        // then
        int expected = 50;
        assertThat(result).isEqualTo(expected);

    }

    class Calculator {
        int add(int a, int b) {
            return a+b;
        }
    }
}

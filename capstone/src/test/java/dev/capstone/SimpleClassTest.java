package dev.capstone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleClassTest {

    @DisplayName("심플 테스트")
    @Test
    void simpleClassTest() {
        // given
        SimpleClass simpleClass = new SimpleClass();

        // when
        simpleClass.myFunc(1);

        // then
        Assertions.assertThat(simpleClass.getMyField()).isEqualTo(2);
    }
}

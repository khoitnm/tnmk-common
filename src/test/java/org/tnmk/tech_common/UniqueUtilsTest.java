package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class UniqueUtilsTest {

    @Test
    void checkDuplications() {
        int length = 14;
        int loopMax = 10000000;

        Set<String> generatedList = IntStream.range(0, loopMax)
            .parallel()
            .mapToObj(i -> {
//                try {
//                    Thread.sleep(0, 1);
//                } catch (InterruptedException e) {
//                    throw new IllegalStateException(e);
//                }
                return UniqueUtils.timeBasedUniqueString(length);
            })
            .collect(Collectors.toSet());

        log.info(String.join("\n", generatedList.stream().limit(10).toList()));
        long duplicationsCount = loopMax - generatedList.size();
        log.info("Duplication: " + duplicationsCount);
        Assertions.assertThat(duplicationsCount).isLessThan(5);
    }

    @ParameterizedTest
    @CsvSource(
        {
            //length    expectSuccess
            "1          , false",
            "9          , true",
            "12         , true"
        }
    )
    void timeBasedUniqueString_checkLengthValidation(int length, boolean expectSuccess) {
        try {
            String result = UniqueUtils.timeBasedUniqueString(length);
            log.info("Result: " + result);
            if (expectSuccess) {
                Assertions.assertThat(result).hasSize(length);
            } else {
                Assertions.fail("It should get error with length %s.".formatted(length));
            }
        } catch (RuntimeException ex) {
            if (expectSuccess) {
                Assertions.fail("It should success with length %s.".formatted(length));
            }
        }
    }
}

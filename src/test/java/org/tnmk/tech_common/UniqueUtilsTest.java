package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class UniqueUtilsTest {

    @Test
    void checkDuplications() {
        int length = 14;
        int loopMax = 20000000;

        List<UniqueUtils.TimeBaseUnique> generatedList = IntStream.range(0, loopMax)
            .parallel()
            .mapToObj(i -> UniqueUtils.timeBasedUniqueString(length))
            .toList();

        List<UniqueUtils.TimeBaseUnique> duplications = findDuplications(generatedList);

        long duplicationsCount = duplications.size();
        Assertions.assertThat(duplicationsCount).isLessThan(5);
        log.info("Duplications size: " + duplications.size());
        log.info("Duplications:\n " +formatUniqueResults(duplications));

        if (!duplications.isEmpty()) {
            UniqueUtils.TimeBaseUnique firstDuplication = duplications.get(0);
            List<UniqueUtils.TimeBaseUnique> itemsAtTheSameMoment = findItemsAtTheSameInstant(generatedList, firstDuplication.getInstant());
            log.info("itemsAtTheSameMoment:\n " +formatUniqueResults(itemsAtTheSameMoment));
        }
    }

    private String formatUniqueResults(List<UniqueUtils.TimeBaseUnique> uniqueResults) {
        return uniqueResults.stream()
            .map(UniqueUtils.TimeBaseUnique::toString)
            .collect(Collectors.joining("\n"));
    }

    private List<UniqueUtils.TimeBaseUnique> findItemsAtTheSameInstant(List<UniqueUtils.TimeBaseUnique> list, Instant instant) {
        return list.stream().filter(item -> item.getInstant().equals(instant)).toList();
    }

    private List<UniqueUtils.TimeBaseUnique> findDuplications(List<UniqueUtils.TimeBaseUnique> list) {
        List<UniqueUtils.TimeBaseUnique> duplications = new ArrayList<>();
        Map<String, UniqueUtils.TimeBaseUnique> uniqueStrings = new HashMap<>(list.size());
        for (UniqueUtils.TimeBaseUnique item : list) {
            UniqueUtils.TimeBaseUnique existing = uniqueStrings.put(item.getUniqueValue(), item);
            if (existing != null) {
                duplications.add(existing);
                duplications.add(item);
            }
        }
        return duplications;
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
            String result = UniqueUtils.timeBasedUniqueString(length).getUniqueValue();
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

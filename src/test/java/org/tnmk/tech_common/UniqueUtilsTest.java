package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
class UniqueUtilsTest {

    @Test
    void uniqueStr() {
        log.info(Base64NumberUtils.toBase64(4095));

        int loopMax = 10000000;
        Set<Instant> generatedList = new HashSet<>();

        for (int i = 0; i < loopMax; i++) {
            generatedList.add(Instant.now());
        }

        log.info(
            generatedList.stream()
                .map(instant -> {
                    long nano = NanoTimeUtils.getNanosecondsSinceEpoch(instant);
                    return instant + "_" + instant.toEpochMilli() + "_" + nano + "_" + Base64NumberUtils.toBase64(nano);
                })
                .collect(Collectors.joining("\n"))
        );
    }
}

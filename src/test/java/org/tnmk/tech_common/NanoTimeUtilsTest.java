package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
class NanoTimeUtilsTest {
    @Test
    void getNanosecondsSinceEpoch() {
        int loopMax = 10000;
        //Instant[] generatedList = new Instant[loopMax];
        Set<Instant> generatedList = new HashSet<>();

        for (int i = 0; i < loopMax; i++) {
            // generatedList[i] = Instant.now();
            generatedList.add(Instant.now());
        }

        log.info(
            //Arrays.stream(generatedList)
            generatedList.stream()
                .map(instant -> instant + "_" + instant.toEpochMilli() + "_" + NanoTimeUtils.getNanosecondsSinceEpoch(instant))
                .collect(Collectors.joining("\n"))
        );
    }
}

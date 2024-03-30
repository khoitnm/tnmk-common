package org.tnmk.tech_common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class NanoTimeUtilsTest {
    @Test
    void getNanosecondsSinceEpoch() {
        int loopMax = 10000;
        Instant[] generatedList = new Instant[loopMax];
//        Set<Instant> generatedList = new HashSet<>();

        for (int i = 0; i < loopMax; i++) {
             generatedList[i] = Instant.now();
//            generatedList.add(Instant.now());
        }

        log.info(
            Arrays.stream(generatedList)
//            generatedList.stream()
                .map(instant -> instant + "_" + instant.toEpochMilli() + "_" + NanoTimeUtils.getEpocNano(instant))
                .collect(Collectors.joining("\n"))
        );
    }

    @Test
    void getNanoseconds_forSomeSpecificDateTimes() {
        List<String> dateTimes = List.of(
            "2000-01-01T00:00:00.012345678Z"

            , "2024-01-01T00:00:00.012345678Z"
            , "2024-03-28T10:46:00.0123456Z"

            , "2030-01-01T00:00:00.012345678Z"
            , "2040-01-01T00:00:00.012345678Z"
            , "2050-01-01T00:00:00.012345678Z"
            , "2100-01-01T00:00:00.012345678Z"
            , "2150-01-01T00:00:00.012345678Z"
            , "2200-01-01T00:00:00.012345678Z"
            , "2262-01-01T00:00:00.012345678Z"
        );

        dateTimes.forEach(this::printNanoTime);
    }

    private void printNanoTime(String dateTime) {
        Instant instant = Instant.parse(dateTime);
        long nano = NanoTimeUtils.getEpocNano(instant);
        String base64 = Base64NumberUtils.toBase64(nano);
        log.info(instant + "_" + nano + "_" + base64);
    }
}

package org.tnmk.tech_common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public class UniqueUtils {
    /**
     * <b>The general idea:</b><br/>
     * This method works based on sequenced time, and in theory, precision at nanosecond level.
     * It means triggering this method at different nanosecond will return different values (and hence no duplication).
     * If it's triggered at the same time, we'll add some random value to reduced the duplication risk.
     * (but the risk can still happen). And this random value depends on the total length that we may have.
     * <p/>
     * Having said that, in reality, computers at this moment (2024/03/28) is still struggling to have nanosecond precision.
     * So, mostly we'll have the time precisions at milliseconds or a bit better than that, but not nanosecond.
     * In means what we described in the previous paragraph is not really correct at nanosecond,
     * but correct at millisecond (or a bit better than that, depending on the computer).<br/>
     * Please view more in {@link NanoTimeUtils#getEpocNano(Instant)}.
     * <p/>
     * And then we'll convert timestamp value ({@link Long} number) to base64 so that with the same amount of characters,
     * we can represent many more numbers.
     *
     * @param expectedLength should be at least 9 (because that's the length of epocNanosecond in base-64 format).
     *                       If the expectedLength less than that, the uniqueness would be significantly reduced.
     */
    @Valid
    public static TimeBaseUnique timeBasedUniqueString(@Min(9) int expectedLength) {
        Instant now = Instant.now();
        long epocNanosecond = NanoTimeUtils.getEpocNano(now);
        // In theory, epocNanosecond could be 1711559724093779212 when computer can really have nanosecond precision.
        // In reality, computer cannot have that, so epocNanosecond is usually just 1711559724093779200 (last 2 digits are just 00).
        // That's why we remove 2 last digits, and hence epocTimePrecision is 17115597240937792.
        long epocTimePrecision = epocNanosecond / 100;
        // base64 of 17115597240937792 will be 8zouFqi1A (9 chars).
        String epocTimeAsBase64 = Base64NumberUtils.toBase64(epocTimePrecision);

        String timeBaseUnique = epocTimeAsBase64;
        int randomLength = expectedLength - epocTimeAsBase64.length();
        if (randomLength > 0) {
            timeBaseUnique = epocTimeAsBase64 + RandomStringUtils.randomAscii(randomLength);
        }
        return TimeBaseUnique.builder().instant(now).uniqueValue(timeBaseUnique).build();
    }

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class TimeBaseUnique {
        private final Instant instant;
        private final String uniqueValue;

        public String toString() {
            return "Instants: %s, nano: %s, uniqueValue: %s".formatted(instant, NanoTimeUtils.getEpocNano(instant), uniqueValue);
        }
    }
}

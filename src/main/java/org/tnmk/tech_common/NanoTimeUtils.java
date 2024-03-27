package org.tnmk.tech_common;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.grammars.hql.HqlParser;

import java.time.Instant;

/**
 * -
 */
public class NanoTimeUtils {
    /**
     * Very good discussion is here:
     * - https://stackoverflow.com/questions/52077097/how-to-get-current-time-in-nano-seconds-in-java
     * - The source code in the above link: https://github.com/jenetics/jenetics/blob/master/jenetics/src/main/java/io/jenetics/util/NanoClock.java
     * <p/>
     * - As mentioned in the above document,
     * it is possible to get the current time to "better than microsecond" accuracy.
     * However, that's not guarantee to have exactly nanosecond precision.
     * - The values of seconds and nano depend on the accuracy of the local hardware clock.
     * - And even that doesn't guarantee uniqueness globally when the logic is run on may different computers.
     */
    public static long getNanosecondsSinceEpoch(Instant instant) {
        long seconds = instant.getEpochSecond();
        int nanoseconds = instant.getNano();
        long nanosecondsSinceEpoch = seconds * 1_000_000_000L + nanoseconds;
        return nanosecondsSinceEpoch;
    }
}

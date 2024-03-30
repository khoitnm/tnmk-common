package org.tnmk.tech_common;

import java.math.BigInteger;
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
     * <p/>
     * <b>Note: This method is only correct at maximum 2262-01-01T00:00:00.00Z</b>:<br/>
     * For an instant after that, this method will get number overflow, and hence the result will be negative.
     * If you really want to calculate for time after that,
     * write another method that use {@link BigInteger} instead of {@link Long}.
     */
    public static long getEpocNano(Instant instant) {
        long seconds = instant.getEpochSecond();
        int nanoseconds = instant.getNano();
        long epocNanoseconds = seconds * 1_000_000_000L + nanoseconds;
        return epocNanoseconds;
    }
}

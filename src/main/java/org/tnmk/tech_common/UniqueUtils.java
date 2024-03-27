package org.tnmk.tech_common;

import java.time.Instant;

public class UniqueUtils {
    /**
     * This method won't be able to unique totally,
     * but if this method is triggered at different nanosecond,
     * it will be definitely different.
     * TODO add some additional random depending on the limit size.
     */
    public static String timeBasedUniqueString() {
        long nanosecond = NanoTimeUtils.getNanosecondsSinceEpoch(Instant.now());
        String base64 = Base64NumberUtils.toBase64(nanosecond);
        return base64;
    }
}

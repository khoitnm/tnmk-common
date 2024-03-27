package org.tnmk.tech_common;

public class Base64NumberUtils {
    /**
     * This is the list of characters that will be used to represent a base-64 value.
     */
    private static final String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";


    /**
     * @param decimalNumber this method is used to convert decimal number (base-10) only.
     *                      Don't use it for any other kind of input value.
     */
    public static String toBase64(long decimalNumber) {
        StringBuilder base64 = new StringBuilder();

        while (decimalNumber > 0) {
            long remainder = decimalNumber % 64;
            base64.insert(0, base64Chars.charAt((int) remainder));
            decimalNumber /= 64;
        }

        return base64.toString();
    }
}

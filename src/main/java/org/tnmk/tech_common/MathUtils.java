package org.tnmk.tech_common;

public interface MathUtils {
  /**
   * Check whether the given number is a power of 2.
   * For example:
   * - isPowerOfTwo(16) = true because 2 ^ 4 = 16
   * - isPowerOfTwo(10) = false because there's no number that 2 ^ ? = 10
   */
  static boolean isPowerOfTwo(int number) {
    if (number <= 0) {
      return false;
    }
    return (number & (number - 1)) == 0;
  }
}

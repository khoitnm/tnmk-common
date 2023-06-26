package org.tnmk.tech_common.language;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.util.StopWatch;

@Slf4j
class SpecialAccentUtilsTest {
  @ParameterizedTest
  @CsvSource({
    //original                ,expectedConverted

    // Empty string
    "                         ,",

    // Special accent in lower case
    // "ę́,e",//This is actually 2 chars, and I still cannot handle this case properly, it would be converted to "ea" unexpectedly?!
    "é                        ,e",
    "ą́                        ,a",
    "åãáäâàāæąą́-çćčĉ-éëêèę-ĥ/ìíîïįį́/ǫ́/ų́ ,aaaaaaaaaa-cccc-eeeee-h/iiiiii/o/u",//lowercase
    "ÉÀÔÙÇÑßŽÅŞŠĆČ            ,EAOUCNssZASSCC",//uppercase
    "éÀôÙçÑß                  ,eAoUcNss",//mixing

    "Académica                ,Academica",
    "Borussia Mönchengladbach ,Borussia Monchengladbach",
    "Leixões                  ,Leixoes",

    // Special accent in upper case
    "Saint-Étienne            ,Saint-Etienne",

    // No speical accent
    "Borussia Monchengladbach ,Borussia Monchengladbach",
    "Leixoes                  ,Leixoes",
  })
  public void test_convertSpecialAccentChars(String originalName, String expectedReplacedName) {
    String replacedName = SpecialAccentUtils.replaceSpecialAccentChars(originalName);

    Assertions.assertThat(replacedName).isEqualTo(expectedReplacedName);
  }

  @ParameterizedTest
  @CsvSource({
    //original                ,expectedHasSpecialAccentChar
    "                         ,false",

    "é                        ,true",
    "Académica                ,true",
    "Académica                ,true",
    "Borussia Mönchengladbach ,true",
    "Leixões                  ,true",

    "Borussia Monchengladbach ,false",
    "Leixoes                  ,false",
    "Leixoes-ABC              ,false",
    "Leixoes'Angela 1. & 2.   ,false",

    "ų́   ,true",
    "ǫ́   ,true",
  })
  public void test_containSpecialAccentChars(String originalName, boolean expectedContainSpecialAccents) {
    boolean actualContainsSpecialAccents = SpecialAccentUtils.containsSpecialAccentChars(originalName);

    Assertions.assertThat(actualContainsSpecialAccents).isEqualTo(expectedContainSpecialAccents);
  }

  @Test
  public void performanceTest() {
    String input = "Saint-Étienne";
    int loop = 100000;
    test01(input, loop);
    test02(input, loop);
  }

  private void test01(String input, int loop){
    StopWatch stopWatch = new StopWatch("Option 1");
    stopWatch.start();
    for (int i = 0; i < loop; i++) {
      SpecialAccentUtils.replaceSpecialAccentChars(input);
    }
    stopWatch.stop();

    log.info("Runtime: " + stopWatch.getTotalTimeMillis() / 1000d);
  }

  private void test02(String input, int loop){
    StopWatch stopWatch = new StopWatch("Option 2");
    stopWatch.start();
    for (int i = 0; i < loop; i++) {
      SpecialAccentUtils.replaceSpecialAccentChars(input);
    }
    stopWatch.stop();

    log.info("Runtime: " + stopWatch.getTotalTimeMillis() / 1000d);
  }
}
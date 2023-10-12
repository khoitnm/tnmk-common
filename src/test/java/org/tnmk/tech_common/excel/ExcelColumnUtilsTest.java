package org.tnmk.tech_common.excel;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ExcelColumnUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {
        "0  ; A       ",
        "1  ; B       ",
        "25 ; Z       ",
        "26 ; AA      ",
        "27 ; AB      ",
        "52 ; BA      "
    }, delimiterString = ";")
    void toColumnLetter(int colIndex, String expectedColName) {
        String actualColName = ExcelColumnUtils.toColumnLetter(colIndex);
        
        Assertions.assertThat(actualColName).isEqualTo(expectedColName);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "A; 0",
        "B; 1",
        "Z; 25",
        "AA; 26",
        "AB; 27",
        "BA; 52"
    }, delimiterString = ";")
    void toColumnIndex(String colName, int expectedColIndex) {
        int actualIndex = ExcelColumnUtils.toColumnIndex(colName);

        Assertions.assertThat(actualIndex).isEqualTo(expectedColIndex);
    }
}

package org.tnmk.tech_common.excel;

import org.apache.poi.ss.util.CellReference;

public class ExcelColumnUtils {
    public static String toColumnLetter(int columnIndex) {
        String columnLetter = CellReference.convertNumToColString(columnIndex);
        return columnLetter;
    }

    public static int toColumnIndex(String columnName) {
        int index = 0;
        for (int i = 0; i < columnName.length(); i++) {
            char c = columnName.charAt(i);
            index = index * 26 + (c - 'A' + 1);
        }
        return index - 1; // Subtract 1 to make it 0-based
    }
}

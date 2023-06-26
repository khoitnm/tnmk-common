package org.tnmk.tech_common.excel;

import org.apache.poi.ss.util.CellReference;

public class ExcelColumnUtils {
    public static String toColumnLetter(int columnIndex) {
        String columnLetter = CellReference.convertNumToColString(columnIndex);
        return columnLetter;
    }
}

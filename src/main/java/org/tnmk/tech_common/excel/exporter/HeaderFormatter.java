package org.tnmk.tech_common.excel.exporter;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.tnmk.tech_common.excel.ExcelFormatUtils;

@Service
@RequiredArgsConstructor
public class HeaderFormatter {
    private final ExportLayout exportLayout;

    public void decorateHeadersColor(Sheet sheet) {
        int col = exportLayout.getHeaderStartCol();
        for (int rowIndex = 0; rowIndex < exportLayout.getHeaderRowsCount(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            for (ExportColumn column : exportLayout.getAllColumns()) {
                Cell cell = row.getCell(col);
                ExcelFormatUtils.setColor(cell, null, column.getHeaderBgColor());
                col++;
            }
        }

    }
}

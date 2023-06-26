package org.tnmk.tech_common.excel.exporter;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@RequiredArgsConstructor
public class ContentFormatter {
    private final ExportLayout exportLayout;
    /**
     * Format data type of content in the Sheet.
     *
     * @param sheet
     */
    public void formatContent(Sheet sheet) {
        int colIndex = exportLayout.getHeaderStartCol();
        for (ExportColumn column : exportLayout.getAllColumns()) {
            formatAllRowsInColumn(sheet, colIndex, column);
            colIndex++;
        }
    }


    private void formatAllRowsInColumn(Sheet sheet, int columnIndex, ExportColumn column) {
        if (StringUtils.isBlank(column.getDataFormat())) {
            return;
        }
        Workbook workbook = sheet.getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat(column.getDataFormat()));

        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            formatRow(sheet, rowIndex, columnIndex, cellStyle);
        }
    }

    private void formatRow(Sheet sheet, int rowIndex, int columnIndex, CellStyle cellStyle) {
        sheet.getRow(rowIndex).getCell(columnIndex).setCellStyle(cellStyle);
    }


}

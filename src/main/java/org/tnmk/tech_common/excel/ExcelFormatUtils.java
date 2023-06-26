package org.tnmk.tech_common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ExcelFormatUtils {
    public static void formatCell(Cell cell, String formatPattern) {
        Workbook workbook = cell.getRow().getSheet().getWorkbook();
        DataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        //        if (cellStyle == null) {
        //            cellStyle = workbook.createCellStyle();
        //        }
        cellStyle.setDataFormat(format.getFormat(formatPattern));
        cell.setCellStyle(cellStyle);
    }

    public static void formatNumber(Cell cell) {
        formatCell(cell, "#,##0.00");
    }

    public static void setColor(Cell cell, IndexedColors fontColor, IndexedColors backgroundColor) {
        //      Not sure why `cell.getCellStyle()` didn't work, so I just create it again from getWorkbook().createCellStyle();
        //        CellStyle cellStyle = cell.getCellStyle();
        //        if (cellStyle == null) {
        //            cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        //        }
        CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        if (fontColor != null) {
            Font font = cell.getSheet().getWorkbook().createFont();
            //            XSSFFont xssfFont = (XSSFFont)font;
            //            xssfFont.setColor(new XSSFColor(fontColorRBG, null));
            font.setColor(fontColor.getIndex());
            cellStyle.setFont(font);
        }

        if (backgroundColor != null) {
            cellStyle.setFillForegroundColor(backgroundColor.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        cell.setCellStyle(cellStyle);
    }

    public static void setColor(Cell cell, java.awt.Color fontColor, java.awt.Color backgroundColor) {
        XSSFCellStyle oldCellStyle = (XSSFCellStyle) cell.getCellStyle();
        XSSFCellStyle newCellStyle;
        if (oldCellStyle != null) {
            newCellStyle = oldCellStyle.copy();
        } else {
            newCellStyle = (XSSFCellStyle) cell.getSheet().getWorkbook().createCellStyle();
        }

        if (fontColor != null) {
            Font font = cell.getSheet().getWorkbook().createFont();
            XSSFFont xssfFont = (XSSFFont) font;
            xssfFont.setColor(ExcelColorUtils.toXSSFColor(fontColor));
            font.setColor(xssfFont.getColor());
            newCellStyle.setFont(font);
        }

        if (backgroundColor != null) {
            XSSFColor color = ExcelColorUtils.toXSSFColor(backgroundColor);
            newCellStyle.setFillForegroundColor(color);
            newCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        cell.setCellStyle(newCellStyle);
    }
}

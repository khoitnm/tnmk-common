package org.tnmk.tech_common.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.tnmk.tech_common.NumberUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public final class ExcelValueUtils {
    private ExcelValueUtils() {
        //Utils
    }

    public static Cell setValue(Sheet sheet, int rowIndex, int columnIndex, Object value, Class<?> valueType) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        setValue(cell, value, valueType);
        return cell;
    }

    private static void setValue(Cell cell, Object value, Class<?> valueType) {
        if (Number.class.isAssignableFrom(valueType)) {
            Number number = (Number) value;
            cell.setCellValue(number.doubleValue());
        } else if (Boolean.class.isAssignableFrom(valueType)) {
            cell.setCellValue((Boolean) value);
        } else if (String.class.isAssignableFrom(valueType)) {
            cell.setCellValue((String) value);
        } else if (Date.class.isAssignableFrom(valueType)) {
            cell.setCellValue((Date) value);
        } else if (LocalDate.class.isAssignableFrom(valueType)) {
            cell.setCellValue((LocalDate) value);
        } else if (LocalDateTime.class.isAssignableFrom(valueType)) {
            cell.setCellValue((LocalDateTime) value);
        } else if (Calendar.class.isAssignableFrom(valueType)) {
            cell.setCellValue((Calendar) value);
        } else {
            cell.setCellValue(String.valueOf(value));
        }

    }

    public static Cell setString(Sheet sheet, int rowIndex, int columnIndex, String string) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        cell.setCellValue(string);
        return cell;
    }

    public static void setNumber(Sheet sheet, int rowIndex, int columnIndex, Number value) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Double number = NumberUtils.toDoubleIfPossible(value);
        cell.setCellValue(number);
    }

    public static void setFormula(Sheet sheet, int rowIndex, int columnIndex, String formula) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        //        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula(formula);
    }
}

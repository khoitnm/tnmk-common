package org.tnmk.tech_common.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.tnmk.tech_common.NumberUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Version: 1.0.0
 */
public final class ExcelIOUtils {
    private ExcelIOUtils() {
        //Utilities
    }

    public static XSSFWorkbook readCsvAsXlsx(String csvAbsFileName, String deliminator) throws IOException {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet("sheet1");
        String currentLine = null;
        int rowIndex = 0;
        BufferedReader br = new BufferedReader(new FileReader(csvAbsFileName));
        while ((currentLine = br.readLine()) != null) {
            String strings[] = currentLine.split(deliminator);
            XSSFRow currentRow = sheet.createRow(rowIndex);
            for (int i = 0; i < strings.length; i++) {
                String string = strings[i];
                Double doubleValue = NumberUtils.toDoubleIfPossible(string);
                Cell cell = currentRow.createCell(i);
                if (doubleValue != null) {
                    cell.setCellValue(doubleValue);
                } else {
                    cell.setCellValue(string);
                }
            }
            rowIndex++;
        }
        return workBook;

    }

    /**
     * * @param csvFileAddress
     *
     * @param xlsxAbsFileName
     * @param deliminator     usually a comma character.
     * @return
     */
    public static XSSFWorkbook csvToXlsx(String csvAbsFileName, String deliminator, String xlsxAbsFileName) throws IOException {
        XSSFWorkbook workBook = readCsvAsXlsx(csvAbsFileName, deliminator);
        writeToFile(workBook, xlsxAbsFileName);
        return workBook;
    }

    public static void writeToFile(Workbook workBook, String absFileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(absFileName);
        workBook.write(fileOutputStream);
        fileOutputStream.close();
    }
}

package org.tnmk.tech_common.excel;

import org.apache.poi.xssf.usermodel.XSSFColor;
import org.tnmk.tech_common.ColorUtils;

import java.awt.*;

public class ExcelColorUtils {
    public static XSSFColor toXSSFColor(Color color) {
        return new XSSFColor(ColorUtils.toRgbArray(color), null);
    }
}

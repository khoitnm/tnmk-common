package org.tnmk.tech_common.excel.exporter;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

@Data
public class ExportColumn {
    private final String valueExpression;
    private final String columnDisplayName;

    private final String dataFormat;

    private final Color headerBgColor;

    public String getColumnDisplayNameOrDefault() {
        if (StringUtils.isNotBlank(columnDisplayName))
            return columnDisplayName;

        String[] expressionParts = valueExpression.split("\\.");
        return expressionParts[expressionParts.length - 1];
    }

}

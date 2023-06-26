package org.tnmk.tech_common.excel.exporter;

import java.util.List;

public interface ExportLayout {
    List<ExportColumn> getAllColumns();

    int findColumnByDisplayName(String displayName);

    int validateExistColumnByDisplayName(String displayName);

    int getHeaderRowsCount();

    int getHeaderStartCol();
}

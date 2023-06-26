package org.tnmk.tech_common.csv;

public interface CsvMapper<T> {
    T parseLine(String line);
}

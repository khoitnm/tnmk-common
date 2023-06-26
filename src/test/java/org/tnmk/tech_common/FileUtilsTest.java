package org.tnmk.tech_common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tnmk.tech_common.file.FileUtils;

import java.io.File;

class FileUtilsTest {

  @ParameterizedTest
  @ValueSource(strings = {"sample_folder", "sample_folder/", "/sample_folder", "/sample_folder/"})
  void getFolderFromClasspath(String folderClassPath) {
    // WHEN
    File folder = FileUtils.getFolderFromClasspath(folderClassPath);

    // THEN
    Assertions.assertTrue(folder.exists());
    Assertions.assertTrue(folder.isDirectory());
  }
}
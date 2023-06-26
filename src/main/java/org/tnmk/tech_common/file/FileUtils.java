package org.tnmk.tech_common.file;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

  public static byte[] loadFileFromClasspath(String classpathFileLocation) throws FileNotFoundRuntimeException {
    try (InputStream inputStream = FileUtils.class.getResourceAsStream(classpathFileLocation)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("File not found file: " + classpathFileLocation);
      }
      return IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
      throw new FileNotFoundRuntimeException("Read file error" + classpathFileLocation, e);
    }
  }

  public static InputStream loadInputStreamFileInClassPath(String classpathFileLocation) {
    InputStream inputStream = FileUtils.class.getResourceAsStream(classpathFileLocation);
    return inputStream;
  }

  /**
   * @param path a relative path in classpath. E.g. "images/email/logo.png"
   *             From Class, the path is relative to the package of the class unless you include a leading slash.
   *             So if you don't want to use the current package, include a slash like this: "/SomeTextFile.txt"
   * @return
   * @throws FileNotFoundRuntimeException
   */
  public static String loadTextFileInClassPath(final String path) throws FileNotFoundRuntimeException {
    try {
      final InputStream inputStream = validateExistInputStreamFromClassPath(path);
      return org.apache.commons.io.IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    } catch (final IOException e) {
      final String msg = String.format("Cannot load String from '%s'", path);
      throw new FileNotFoundRuntimeException(msg, e);
    }
  }

  private static InputStream validateExistInputStreamFromClassPath(final String path) throws FileNotFoundRuntimeException {
    final InputStream inputStream = loadInputStreamFileInClassPath(path);
    if (inputStream == null) {
      throw new FileNotFoundRuntimeException(String.format("Not found file from '%s'", path));
    }
    return inputStream;
  }

  /**
   * @param path the path could be relative path or absolute path.
   * @return
   */
  public static FileInputStream loadInputStreamSystemFile(String path) throws FileNotFoundRuntimeException {
    try {
      return new FileInputStream(path);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundRuntimeException(String.format("Cannot load InputStream from file '%s'", path), e);
    }
  }

  public static File getFolderFromClasspath(String folderClassPath) throws FileNotFoundRuntimeException {
    String folderClassPathWithoutSlashPrefix = removeSlashPrefixInPath(folderClassPath);

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL url = classLoader.getResource(folderClassPathWithoutSlashPrefix);
    if (url == null) {
      throw new FileNotFoundRuntimeException("Cannot get folder URL of '" + folderClassPath + "' on classpath.");
    }

    // FIXME In Windows, the url.getFile() will be '/C:/xxx', hence we have to remove slashPrefix.
    //  Not sure about Linux/Mac???
    String filePath = removeSlashPrefixInPath(url.getFile());
    Path path = Paths.get(filePath);
    File folder = path.toFile();

    if (!folder.exists()) {
      throw new FileNotFoundRuntimeException("Cannot getFolderFromClassPath '" + folderClassPath + "'. " +
        "Folder '" + path + "' doesn't exist on file system.");
    }

    return folder;
  }

  private static String removeSlashPrefixInPath(String path) {
    String pathWithoutSlashPrefix = path.startsWith("/") ?
      path.substring(1) : path;
    return pathWithoutSlashPrefix;
  }
}
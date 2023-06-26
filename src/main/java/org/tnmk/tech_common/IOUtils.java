package org.tnmk.tech_common;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


/**
 * version: 1.0.1
 * 2018/04/04
 *
 * @author khoi.tran on 7/5/17.
 */
public final class IOUtils {
    private static final String PATH_PREFIX_FOR_CLASSPATH = "classpath:";

    private IOUtils() {
        //Hide the public constructor.
    }

    /**
     * @param path a relative path in classpath. E.g. "images/email/logo.png"
     *             From Class, the path is relative to the package of the class unless you include a leading slash.
     *             So if you don't want to use the current package, include a slash like this: "/SomeTextFile.txt"
     * @return
     */
    public static String loadTextFileInClassPath(final String path, String chartSet) throws IOException {
        final InputStream inputStream = validateExistInputStreamFromClassPath(path);
        if (chartSet == null) {
            chartSet = "UTF-8";
        }
        return org.apache.commons.io.IOUtils.toString(inputStream, Charset.forName(chartSet));
    }

    /**
     * @param path a relative path in classpath. E.g. "images/email/logo.png"
     *             From Class, the path is relative to the package of the class unless you include a leading slash.
     *             So if you don't want to use the current package, include a slash like this: "/SomeTextFile.txt"
     * @return
     */
    public static byte[] loadBinaryFileInClassPath(final String path) throws IOException {
        final InputStream inputStream = validateExistInputStreamFromClassPath(path);
        return org.apache.commons.io.IOUtils.toByteArray(inputStream);
    }

    private static InputStream validateExistInputStreamFromClassPath(final String path) throws IOException {
        final InputStream inputStream = loadInputStreamFileInClassPath(path);
        if (inputStream == null) {
            throw new IOException(String.format("Not found file from '%s'", path));
        }
        return inputStream;
    }

    /**
     * @param path view more in {@link #loadBinaryFileInClassPath(String)}
     * @return
     */
    public static InputStream loadInputStreamFileInClassPath(final String path) {
        return IOUtils.class.getResourceAsStream(path);
    }

    public static void writeTextToFile(String absolutePath, String content) throws IOException {
        Files.write(Paths.get(absolutePath), content.getBytes());
    }

    public static byte[] readBytesFromSystemFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    public static String loadTextFileInSystem(final String path) throws IOException {
        byte[] bytes = readBytesFromSystemFile(path);
        return new String(bytes);
    }

    public static List<String> loadTextLinesInSystemFile(final String path) throws IOException {
        File file = new File(path);
        return FileUtils.readLines(file, Charset.defaultCharset());
    }

    public static List<String> loadTextLinesInClasspathFile(final String classPath) throws IOException {
        String filePath = PATH_PREFIX_FOR_CLASSPATH + classPath;
        File file = new File(filePath);
        return FileUtils.readLines(file, Charset.defaultCharset());
    }

    public static File createParentFolderIfNecessary(String filePath) throws IOException {
        File destinationFile = new File(filePath);

        String parentPath = destinationFile.getParent();
        return createFolderIfNecessary(parentPath);
    }

    /**
     * @param folderPath
     * @return If folder already exists or has just created, return the folder.
     * If there's a file with the same name, return null.
     * Else, throw Exception.
     */
    public static File createFolderIfNecessary(String folderPath) throws IOException {
        File file = new File(folderPath);
        if (!file.exists()) {
            FileUtils.forceMkdir(file);
        } else if (!file.isDirectory()) {
            return null;
        }
        return file;
    }

    /**
     * @param path the path could be relative path or absolute path.
     * @return
     */
    public static FileInputStream loadInputStreamSystemFile(String path) throws FileNotFoundException {
        return new FileInputStream(new File(path));
    }

    /**
     * @param filePath - For the file from system: just put relative or absolute path.
     *                 - For the file from classpath: must have pre-fix `classpath:`
     * @return
     */
    public static List<String> loadTextLinesInFile(String filePath, String chartSet) throws IOException {
        if (filePath.startsWith(PATH_PREFIX_FOR_CLASSPATH)) {
            String classPath = filePath.substring(PATH_PREFIX_FOR_CLASSPATH.length());
            String text = loadTextFileInClassPath(classPath, chartSet);
            return text.lines().collect(Collectors.toList());
        } else {
            return loadTextLinesInSystemFile(filePath);
        }
    }
}

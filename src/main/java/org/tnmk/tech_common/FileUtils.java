package org.tnmk.tech_common;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * version: 1.0.2
 * 2018/04/04
 *
 * @author khoi.tran
 */
public final class FileUtils {
    private FileUtils() {
        //Utils
    }

    public static String getFileExtension(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }

    /**
     * @param filePath
     * @return file base name (removed extension and directories' path)
     * For example:
     * filePath = "C:/pathA/pathB/FileBaseNameC.csv"
     * return: "FileBaseNameC"
     */
    public static String getFileBaseName(String filePath) {
        return FilenameUtils.getBaseName(filePath);
    }

    public static boolean isTextFile(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            return false;
        }
        FileInputStream in = new FileInputStream(f);
        int size = in.available();
        if (size > 1000) {
            size = 1000;
        }
        byte[] data = new byte[size];
        in.read(data);
        in.close();
        String s = new String(data, "ISO-8859-1");
        String s2 = s.replaceAll(
                "[a-zA-Z0-9ßöäü\\.\\*!\"§\\$\\%&/()=\\?@~'#:,;\\" +
                        "+><\\|\\[\\]\\{\\}\\^°²³\\\\ \\n\\r\\t_\\-`´âêîô" +
                        "ÂÊÔÎáéíóàèìòÁÉÍÓÀÈÌÒ©‰¢£¥€±¿»«¼½¾™ª]", "");
        // will delete all text signs

        double d = (double) (s.length() - s2.length()) / (double) (s.length());
        // percentage of text signs in the text
        return d > 0.95;

    }

    public static boolean isBinaryFileWithProbeContentType(File file) throws IOException {
        String type = Files.probeContentType(file.toPath());
        if (type == null) {
            //type couldn't be determined, assume binary
            return true;
        } else if (type.startsWith("text")) {
            return false;
        } else {
            //type isn't text
            return true;
        }
    }

    /**
     * Remove '\' or '/' at the end of the path.
     *
     * @param path
     * @return
     */
    public static String normalizePath(String path) {
        String normalizedPath = path;
        if (path.endsWith("/") || path.endsWith("\\")) {
            normalizedPath = path.substring(0, path.length() - 1);
        }
        return normalizedPath;
    }

    public static void toUTF8(String filename, String sourceEncoding, String targetEncoding) throws IOException {
        File file = new File(filename);
        String content;
        if (StringUtils.isNotBlank(sourceEncoding)) {
            content = org.apache.commons.io.FileUtils.readFileToString(file, sourceEncoding);
        } else {
            content = org.apache.commons.io.FileUtils.readFileToString(file, Charset.defaultCharset());
        }
        org.apache.commons.io.FileUtils.write(file, content, targetEncoding);
    }
}

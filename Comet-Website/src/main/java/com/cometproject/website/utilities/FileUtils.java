package com.cometproject.website.utilities;

import java.io.FileInputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static String getContents(String file) {
        FileInputStream fileInputStream = null;
        StringWriter stringWriter = new StringWriter();

        try {
            fileInputStream = new FileInputStream(file);

            IOUtil.copy(fileInputStream, stringWriter, "UTF-8");
        } catch(Exception e) {
            LOGGER.error("Error while loading file", e);
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch(Exception e) {
                LOGGER.warn("Failed to close stream", e);
            }
        }

        return stringWriter.toString();
    }
}

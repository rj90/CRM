package org.pw.rafalj.crm.sql.util;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class FileGenerator {
    public static void generateFile(Path directory, String filePath, List<List<String>> lists){
        try {
            for(int i = 1; i <= lists.size(); i++) {
                String filename = prepareFileName(filePath, i);
                if(!Files.exists(directory))
                    Files.createDirectories(directory);
                if (!Files.exists(directory.resolve(filename))) {
                    Files.createFile(directory.resolve(filename));
                }
                    Files.write(directory.resolve(filename), lists.get(i - 1));

            }
        } catch (IOException e) {
            new RuntimeException(e);
        }
    }

    private static String prepareFileName(String filePath, int i) {
        String filename = filePath.substring(0, filePath.lastIndexOf('.'));
        String extension = filePath.substring(filePath.lastIndexOf('.'));

        return filename + "_" + i + extension;
    }

    public static void clearDir(Path directory) {
        try {
            FileUtils.cleanDirectory(directory.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

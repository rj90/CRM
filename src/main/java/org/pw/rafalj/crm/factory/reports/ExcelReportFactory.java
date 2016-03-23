package org.pw.rafalj.crm.factory.reports;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Created by Rav on 2016-03-23.
 */
public class ExcelReportFactory {
    public static void generateFile(String reportName, List<String> columnNames, List<Map<String, Object>> values) throws Exception {
        WritableWorkbook workbook = Workbook.createWorkbook(Files.createFile(Paths.get(generateReportName(reportName))).toFile());
        System.out.println(Paths.get(generateReportName(reportName)).toAbsolutePath());
        WritableSheet sheet = workbook.createSheet(reportName, 0);
        for(int i = 0; i < columnNames.size(); i++)
            sheet.addCell(new Label(i, 0, columnNames.get(i)));

        for(int i = 0; i < values.size(); i++){
            for(Map.Entry<String, Object> entry : values.get(i).entrySet()) {
                sheet.addCell(new Label(columnNames.indexOf(entry.getKey()), i + 1, entry.getValue().toString()));
            }
        }

        workbook.write();
        workbook.close();
    }

    private static String generateReportName(String reportName) {
        return reportName;
    }

}

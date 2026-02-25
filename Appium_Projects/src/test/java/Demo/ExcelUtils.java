package Demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static String filePath;

    public static void loadExcel(String path, String sheetName) throws Exception {
        filePath = path;
        FileInputStream fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static String getCell(int row, int col) {
        return sheet.getRow(row).getCell(col).toString();
    }

    public static void setCell(int row, int col, String value) {
        Row r = sheet.getRow(row);
        if (r == null) r = sheet.createRow(row);

        Cell c = r.getCell(col);
        if (c == null) c = r.createCell(col);

        c.setCellValue(value);
    }

    public static void saveExcel() throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }
}

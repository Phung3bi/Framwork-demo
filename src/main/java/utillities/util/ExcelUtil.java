package utillities.util;

import com.google.common.io.Files;
import core.Keyword;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil extends Keyword {

    private static final Logger log = Logger.getLogger(ExcelUtil.class);
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow currentRow;
    public static XSSFRow headerRow;
    public static XSSFCell cell;
    public static XSSFFormulaEvaluator cellEvaluator;


    public static void createExcelFile(String filePath) throws IOException {
        String ext = Files.getFileExtension(filePath);
        clearFileExists(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        Workbook workbook = ext.equalsIgnoreCase("xlsx") ? new XSSFWorkbook() : new HSSFWorkbook();
        workbook.createSheet("default");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void clearFileExists(String filePath) throws IOException {
        String ext = FilenameUtils.getExtension(filePath);
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static Workbook createWorkbook(String filePath) {
        return Files.getFileExtension(filePath).equalsIgnoreCase("xlsx") ? new XSSFWorkbook() : new HSSFWorkbook();
    }


    public static Workbook getWorkbook(String filePath) throws IOException {
        File xlFile = new File(filePath);
        String ext = Files.getFileExtension(filePath);
        FileInputStream fis = new FileInputStream(xlFile);
        Workbook wb = ext.equalsIgnoreCase("xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis);
        fis.close();
        return wb;
    }


    public static void saveWorkbook(String filePath, Workbook workbook) throws IOException {
        workbook.getCreationHelper().createFormulaEvaluator().clearAllCachedResultValues();
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void saveWorkBookWithData() {

    }


    public static void copyExcelFile(String currentfilePath, String newFilePath) throws IOException {
        Workbook wb = getWorkbook(currentfilePath);
        saveWorkbook(newFilePath, wb);

    }

    public static void getExcelSheet(String filePath, String SheetName) throws Exception {
        try {
            workbook = (XSSFWorkbook) getWorkbook(filePath);
            sheet = workbook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

    public static Sheet getExcelSheet(Workbook wbs, String sheetName) throws Exception {
        return wbs.getSheet(sheetName);
    }

    static Cell getCellByIndex(Sheet sheet, int rowIdx, int colIdx) {
        Row row = sheet.getRow(rowIdx);
        Cell cell = row.getCell(colIdx);
        return cell;
    }

    static Cell getCellByAddress(Sheet sheet, String cellAddress) {
        CellReference cr = new CellReference(cellAddress);
        Row row = sheet.getRow(cr.getRow());
        Cell cell = row.getCell(cr.getCol());
        return cell;
    }


    static List<String> getListSheetNames(Workbook workbook) {
        List<String> sheetNames = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetNames.add(workbook.getSheetName(i));
        }
        return sheetNames;
    }

    public static String getCellData(int RowNum, int ColNum, Sheet sheet) throws Exception {
        try {
            Cell xcell = sheet.getRow(RowNum).getCell(ColNum);
            String CellData = "";
            CellValue cellValue = cellEvaluator.evaluate(xcell);
            switch (xcell.getCellType()) {
                case STRING:
                    CellData = xcell.getStringCellValue();
                    break;
                case NUMERIC:
                    CellData = xcell.getStringCellValue();
                    break;
                case BLANK:
                    break;
                case ERROR:
                    break;
                case FORMULA:
                    if (cell.getCachedFormulaResultType().toString().equals("NUMERIC")) {
                        CellData = String.valueOf(cell.getRawValue());
                    } else {
                        CellData = String.valueOf(cellValue.getStringValue());
                    }
                    break;
                case BOOLEAN:
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }


    public static void setValueToCellByIndex(Sheet sheet, int rowIndex, int colIndex, String value) {
        if (colIndex < 0 || rowIndex < 0) {
            return;
        }
        Row row = sheet.getRow(rowIndex);
        if (row == null) row = sheet.createRow(rowIndex);
        Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        setValueToCell(cell, value);
    }

    public static void WriteDataToListRowByColumnName(String fileExcel, String sheetName, String columnName, String value) {
        try {
            List<HashMap<String, String>> dataRow = null;
            Workbook wb = ExcelUtil.getWorkbook(fileExcel);
            Sheet sheet = ExcelUtil.getExcelSheet(wb, sheetName);
            dataRow = ExcelUtil.MapExcelData(fileExcel, sheetName);
            for (int i = 1; i <= dataRow.size(); i++) {
                setValueToCellByIndex(sheet, i, ExcelUtil.GetColumnIndexByName(sheet, columnName), value);
            }
            ExcelUtil.saveWorkbook(fileExcel, wb);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void setValueToCell(Cell cellTemp, String value) {
        if (cellTemp == null || value == null) {
            return;
        }
        String type = value.getClass().getSimpleName();
        switch (type) {
            case "String":
                if (String.valueOf(value).equalsIgnoreCase("")) {
                    cellTemp.setCellValue("");
                } else if (String.valueOf(value).substring(0, 1).equals("=")) {
                    cellTemp.setCellType(CellType.FORMULA);
                    cellTemp.setCellFormula(String.valueOf(value).substring(1));
                } else {
                    cellTemp.setCellType(CellType.STRING);
                    cellTemp.setCellValue(String.valueOf(value));
                }
                break;
            case "Boolean":
                cellTemp.setCellType(CellType.BOOLEAN);
                cellTemp.setCellValue(Boolean.valueOf(value));
                break;
            case "Integer":
            case "Long":
            case "Float":
            case "Double":
            case "BigDecimal":
                cellTemp.setCellType(CellType.NUMERIC);
                cellTemp.setCellValue(Double.valueOf(value));
                break;
            case "Date":
                cellTemp.setCellType(CellType.NUMERIC);
                Workbook workbook = cellTemp.getSheet().getWorkbook();
                short dateFormat = workbook.createDataFormat().getFormat("dd/MM/yyyy");
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setDataFormat(dateFormat);
                cellTemp.setCellStyle(cellStyle);
                cellTemp.setCellValue(value);
                break;
            default:
                cellTemp.setCellType(CellType.STRING);
                cellTemp.setCellValue(value.toString());
                break;
        }
    }

    public static List<HashMap<String, String>> MapExcelData(String linkExcelFile, String sheetName) {
        List<HashMap<String, String>> mydata = new ArrayList<HashMap<String, String>>();
        try {
            // define hashmap
            HashMap<String, String> currentHash;
            // check excel file
            getExcelSheet(linkExcelFile, sheetName);
            // check evaluator
            cellEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellEvaluator.evaluateAll();
            //get row header
            headerRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                currentRow = sheet.getRow(i);
                currentHash = new HashMap<>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    cell = currentRow.getCell(j);
                    CellValue cellValue = cellEvaluator.evaluate(cell);
                    // check & map data
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                currentHash.put(headerRow.getCell(j).getStringCellValue(), cellValue.getStringValue());
                                break;
                            case NUMERIC:
                                String cellNumber = String.valueOf(cellValue.getNumberValue()).replace(".0", "");
                                currentHash.put(headerRow.getCell(j).getStringCellValue(), cellNumber);
                                break;
                            case BLANK:
                                break;
                            case ERROR:
                                break;
                            case FORMULA:
                                if (cell.getCachedFormulaResultType().toString().equals("NUMERIC")) {
                                    currentHash.put(headerRow.getCell(j).getStringCellValue(), String.valueOf(cell.getRawValue()).replace(".0", ""));
                                } else {
                                    currentHash.put(headerRow.getCell(j).getStringCellValue(), String.valueOf(cellValue.getStringValue()));
                                }
                                break;
                            case BOOLEAN:
                                break;
                        }
                    } else {
                        currentHash.put(headerRow.getCell(j).getStringCellValue(), "");
                    }
                }
                mydata.add(currentHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydata;
    }

    public static int getRowIndexByCellContent(String filePath, String sheetName, String string) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = sheet.iterator();
        CellAddress columnNumber = null;

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    String text = cell.getStringCellValue();
                    if (string.equals(text)) {
                        columnNumber = cell.getAddress();
                        break;
                    }
                }
            }
        }
        workbook.close();
        return columnNumber.getRow();
    }

    public static void writeDataToExcel(int rowcount, int columncount, String filepath, String Sheetname, String value) {
        try {
            WorkbookUtil ulti = null;
            FileInputStream input = new FileInputStream(filepath);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sh = wb.getSheet(Sheetname);
            XSSFRow row = sh.getRow(rowcount);
            FileOutputStream webdata = new FileOutputStream(filepath);
            row.createCell(columncount).setCellValue(value);
            wb.write(webdata);
            input.close();
            webdata.flush();
            webdata.close();
        } catch (Exception e) {

        }
    }

    public static HashMap<String, Integer> mapExcelHeader(String linkExcelFile, String sheetName) {
        HashMap<String, Integer> mydata = new HashMap<String, Integer>();
        try {
            getExcelSheet(linkExcelFile, sheetName);
            headerRow = sheet.getRow(0);
            for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                cell = headerRow.getCell(j);
                // check & map data
                mydata.put(headerRow.getCell(j).getStringCellValue(), cell.getColumnIndex());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydata;
    }

    public static HashMap<String, Integer> mapExcelHeader(Sheet _sheet) {
        HashMap<String, Integer> mydata = new HashMap<String, Integer>();
        try {
            Row _headerRow = _sheet.getRow(0);
            Cell _cell;
            for (int j = 0; j < _headerRow.getPhysicalNumberOfCells(); j++) {
                _cell = _headerRow.getCell(j);
                // check & map data
                mydata.put(_headerRow.getCell(j).getStringCellValue(), _cell.getColumnIndex());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydata;
    }


    public static int GetColumnIndexByName(String linkExcelFile, String sheetName, String ColumnName) {
        HashMap<String, Integer> dataHeader = null;
        try {
            dataHeader = ExcelUtil.mapExcelHeader(linkExcelFile, sheetName);
        } catch (Exception ex) {
            return -1;
        }
        return dataHeader.get(ColumnName);
    }

    public static int GetColumnIndexByName(Sheet sheet, String ColumnName) {
        int columnIndex = -1;
        try {
            Row currentRow = sheet.getRow(0);
            for (int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++) {
                cell = headerRow.getCell(i);
                if (cell.getStringCellValue().equals(ColumnName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
        return columnIndex;
    }

    public static HashMap<String, String> getCurrentRow(String fileExcel, String sheetDataName, int rowIndex) {
        HashMap<String, String> currentRowResult = null;
        try {
            List<HashMap<String, String>> dataRows = null;
            dataRows = MapExcelData(fileExcel, sheetDataName);
            currentRowResult = dataRows.get(rowIndex - 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return currentRowResult;
    }
}
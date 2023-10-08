package pages;

import core.Common;
import core.GlobalConstants;
import core.Keyword;
import languges.Vietnamese;
import org.apache.log4j.Logger;
import utillities.util.ExcelUtil;

import java.util.HashMap;
import java.util.Map;

public class TemplatePage extends Keyword {
    private static final Logger log = Logger.getLogger(TemplatePage.class);



    protected HashMap<String, String> getTestCaseData(String fileExcel, String sheetTestCaseName, String sheetTestDataName, String testcaseID, String rowIndex) {
        HashMap<String, String> map = null;
        try {
            int currentRow = ExcelUtil.getRowIndexByCellContent(fileExcel, sheetTestCaseName, testcaseID);
            Map<String, String> testcaseDataRow = null;
            testcaseDataRow = ExcelUtil.getCurrentRow(fileExcel, sheetTestCaseName, currentRow);
            if (!testcaseDataRow.get(Vietnamese.status).equals(Vietnamese.status1)) {
                // 01. GET SHEET DATA
                map = ExcelUtil.getCurrentRow(fileExcel, sheetTestDataName, Integer.parseInt(rowIndex));
            } else {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }



    public HashMap<String, String> getMapData(String sheetDataName, String testcaseID, String rowIndex) {
        HashMap<String, String> mapCurrentData = null;
        try {
            String fileExcel = Common.getResourcesFolder() + GlobalConstants.LINK_DEMOQA_PAGE;
            mapCurrentData = getTestCaseData(fileExcel, Vietnamese.status, sheetDataName, testcaseID, rowIndex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapCurrentData;
    }




}

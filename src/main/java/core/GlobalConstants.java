package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GlobalConstants {

    // DU LIEU DUNG CHO CHO FRAMEWORK
    // URL/SERVER/DB/USER/PASSWORD
    // LINK FOLDER / SETTING
    // DATA NOT CHANGE WHEN RUN SCRIPT

    public static String LINK_DEMOQA_PAGE = "";
    //public static String DEMO_PROJECT= "";
    public static String DEMO_QA= "";
    public static final int SELECT_ITEM_BY_INDEX = 1;
    public static final int SELECT_ITEM_BY_VALUE = 2;
    public static final int SELECT_ITEM_BY_TEXT = 3;
//--------
    //public static String LINK_PROJECT_PAGE = "";

//---------
    public static String PROJECT_ENVIRONMENT = "";
    public static String DEMOQA_ENVIRONMENT = "";

//-------
    public static HashMap<String, String> _maptestData = null;
    public static List<HashMap<String, String>> _listMapTestData = null;
    //public static List<String> _ResultListOTACompare = new ArrayList<>();


    public static byte[] screenshot = null;
    public static List<byte[]> listScreenshot = new ArrayList<byte[]>();

   // public static String FILE_NAME_PROPERTIES_CIHMS = "";

    // =========== HANDLE SYSTEM LOG =================
    public static String SYSTEM_LOG_EXCEPTION = "";
    public static boolean SYSTEM_WEBDRIVER_INITIALIZED = false;
    public static boolean SYSTEM_SITE_CLOUDTICK_INITIALIZED = false;
    public static boolean SYSTEM_SITE_TAP_INITIALIZED = false;


    // =========== HANDLE GRAFANA LOG =================
    public static List<String> JIRA_ISSUE_LIST_STEP = new ArrayList<>();


    //============BOOKING PARAM=============================

    public static final int WAIT_EXPLICIT = 45;
    public static final int WAIT_IMPLICIT = 30;
    public static final int WAIT_FLUENT_MINISECONDS = 5;

    public static final int WAIT_OVERRIDE_LONG_TIMEOUT = 20;
    public static final int WAIT_OVERRIDE_SHORT_TIMEOUT = 10;

    //public static final String KEY_BOOKING_RESERVATIONCODE = "cihms_portal_reservationCode";
  // public static final String LINK_FILE_TEMPLATE_PROPERTY = "data/TEMPLATE_IMPORT_PROPERTY.xlsx";
   //public static final String KEY_CIHMS_OTA_NAME = "OTA_NAME";
    //public static final String DATABASE_ENV_PRODUCTON = "prod";
    public static final String KEY_DEMOQA_APPLICATION = "DEMOQA";
}

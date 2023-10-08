package pagesDynamicLocator;

public class BasePageLocator {

    //================WEB DYNAMIC LOCATOR


    //DYNAMIC SELECT
    public static final String DYNAMIC_SELECT_BY_NAME = "//select[@name='%s']";

    //=========DYNAMIC TEXTBOX
    //public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";

    //=========DYNAMIC TEXTAREA
    //public static final String DYNAMIC_TEXTAREA_BY_NAME = "//textarea[@name='%s']";

    //=========DYNAMIC BUTTON
   // public static final String DYNAMIC_BUTTON_BY_VALUE = "//button[@value='%s']";

    //=========DYNAMIC RADIO BUTTON
    //public static final String DYNAMIC_RADIO_BUTTON_BY_VALUE = "//input[@value='%s']";

    //=========DYNAMIC ITEM
    public static final String DYNAMIC_ITEM_CONTAINS_TEXT = "//a[@id='%s']";

    //=========DYNAMIC ITEM CONTAINS
    public static final String DYNAMIC_DIV_CONTAINS_TEXT = "//div[contains(text(),'%s')]";

    //=========DYNAMIC DATATABLE
   // public static final String DYNAMIC_COLUMN_CONTAINS_TEXT = "//th[contains(text(),'%s')]";


}

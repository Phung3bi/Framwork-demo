package utillities.util;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

public class JsonParser {

    private JsonParser(){
    }

    public static <T> T getValueformFile(File jsonFile, String jsonPath) throws IOException {
        return JsonPath.parse(jsonFile).read("$." + jsonPath);
    }

    public static <T> T getValueFromJson(String jsonString, String jsonPath) {
        return JsonPath.parse(jsonString).read("$." + jsonPath);
    }

}

package utillities.util;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.UUID;

public class StringUtil {

    private static final Logger log = Logger.getLogger(StringUtil.class);

    public static String GetSubString(String sourceString, String stringRemove) {
        int index = sourceString.indexOf(stringRemove);
        return sourceString.substring(index).trim();
    }

    public static String GetRandomString(int numberCharacter) {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string

        for (int i = 0; i < numberCharacter; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    public static String GetLeftSubString(String sourceString, int index) {
        return sourceString.substring(0, index).trim();
    }


    public static String getDynamicString(String dynamicString, String... values) {
        dynamicString = String.format(dynamicString, (Object[]) values);
        return dynamicString;
    }


    public static String getPathByOS(String path) {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            path = path.replace("/", "\\");
        } else {
            path = path.replace("\\", "/");
        }
        return path;
    }

    public static String getUUID() {
        UUID _uuid = UUID.randomUUID();
        return _uuid.toString();
    }

    public static String generateRandomNumberAndConvertToString(int start, int end) {
        Random random = new Random();
        int a = random.nextInt(end - start + 1) + start;
        String No = String.valueOf(a);
        return No;
    }

}

package utillities;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

public class DateTimeUtil {


    private static final Logger log = Logger.getLogger(DateTimeUtil.class);

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss_SSS");
        String systime = sdf.format(new Date());
        systime = systime.replace(":", "");
        systime = systime.replace("-", "");
        return systime;
    }

    public static String getTimeWithFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String systime = sdf.format(new Date());
//        systime = systime.replace(":", "");
//        systime = systime.replace("-", "");
        return systime;
    }

    //Get The Current Day
    public static String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        return todayStr;
    }

    //Get The Current Day plus days. You can change this method based on your needs.
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
        int dayOfWeekPlus = currentDate.getDayOfWeek().plus(days).getValue();
        return Integer.toString(dayOfWeekPlus);
    }


    public static String getCurrentDateWithFormat(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getCurrentDatePlusWithFormart(int days, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, days);
        Date currentDatePlusOne = c.getTime();
        return dateFormat.format(currentDatePlusOne);
    }

    public static String getDateWithFormat(String date, String patternDate, String patternDateConvert) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDate);
        Date myDate = simpleDateFormat.parse(date);
        simpleDateFormat = new SimpleDateFormat(patternDateConvert);
        String dateConvert = simpleDateFormat.format(myDate);
        return dateConvert;

    }

    public static String getDateAfterNumberDays(String date, int days, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date myDate = simpleDateFormat.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        c.add(Calendar.DATE, days);
        Date currentDatePlusOne = c.getTime();
        return simpleDateFormat.format(currentDatePlusOne);
    }

    public static Date getDateFromString(String dateInString) throws ParseException {
        String dateString = dateInString;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dateString);
        return date;
    }


    // PHUNG
    public static String getNewDate(int date) {
        LocalDate dateRedeemed = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String newDate = dateRedeemed.plusDays(date).format(formatter);
        System.out.println("ngay su dung duoc chon la:" + newDate);
        return newDate;
    }


    // PHUNG
    public static String getNewDateFull(int date) {
        LocalDate dateRedeemed = LocalDate.now();
        DateTimeFormatter formatterfulldate = DateTimeFormatter.ofPattern("d/M/yyyy");
        String newDate = dateRedeemed.plusDays(date).format(formatterfulldate);
        System.out.println("Ngày sử dụng được chọn là: " + newDate);
        return newDate;
    }


    // HAO
    public static String getNewYearFull(int year) {
        LocalDate YearRedeemed = LocalDate.now();
        DateTimeFormatter formatterfullyear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String newYear = YearRedeemed.plusYears(year).format(formatterfullyear);
        System.out.println("Năm kết thúc được chọn là: " + newYear);
        return newYear;
    }


    public static class PropertiesUtil {
        private static final Logger log = Logger.getLogger(PropertiesUtil.class);


        public static void saveDataToPropertyFile(String filePath, String propertyKey, String propertyValue) throws IOException {
            Properties prop = new Properties();
            File file = new File(filePath);

            FileInputStream fileIn = null;
            FileOutputStream fileOut = null;

            try {
                fileIn = new FileInputStream(file);
                prop.load(fileIn);
                prop.setProperty(propertyKey, propertyValue);
                fileOut = new FileOutputStream(file);
                prop.store(fileOut, "");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}

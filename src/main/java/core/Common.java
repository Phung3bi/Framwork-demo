package core;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;


public class Common {
    private static final Logger log = Logger.getLogger(Common.class);
    public static String sDirPath = System.getProperty("user.dir");
    public static final String resourcesFolder = sDirPath + "/src/test/resources/";

    public static Properties props = new Properties();

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss_SSS");
        String systime = sdf.format(new Date());
        systime = systime.replace(":", "");
        systime = systime.replace("-", "");
        return systime;
    }

    public static String getResourcesFolder() {
        return resourcesFolder;
    }

    public static String getDirectoryFolder() {
        return sDirPath + "/";
    }

    public static void sendEmailReport(String title, int pass, int fail, List<String> passScenarios, List<String> failScenarios) throws Exception {
        final String username = "lekimphungbb@gmail.com";
        final String password = "Phung@1234@#";
        String content = null;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
//
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("v.vunph@cloudhms.net"));
            message.setSubject("Automation Report " + title);

            BodyPart messageBodyPart = new MimeBodyPart();
            content = "Please see the automated report in the attached file \n";
            content = content + " *** Scenario number pass: " + pass;
            for (String passScenario : passScenarios) {
                content = content + "\n" + " ==== " + passScenario + "\n";
            }
            content = content + "\n\n";
            content = content + " *** Scenario number fail: " + fail;
            for (String failScenario : failScenarios) {
                content = content + "\n" + " ==== " + failScenario + "\n";
            }
            messageBodyPart.setText(content);

//            MimeBodyPart attachedPart = new MimeBodyPart();
//            String fileName = title + ".zip";
//            String srcFile = System.getProperty("user.dir") + "\\reports\\" + fileName;
//            attachedPart.attachFile(srcFile, "application/zip", "base64");
//            attachedPart.addBodyPart(messageBodyPart);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
//            multipart.addBodyPart(attachedPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sending email done !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String convertDirectory(String directory) {
        directory = directory.replace("/", "\\");
        return directory;
    }

    public static void saveToPropertiesFile(String strFileName, String strKey, String strValue) throws Exception {
        Properties prop = new Properties();
        FileInputStream in = new FileInputStream(resourcesFolder + strFileName);
        FileOutputStream out = new FileOutputStream(resourcesFolder + "temp.properties");

        prop.load(in);
        Set<Object> set = prop.keySet();
        boolean flag = false;
        for (Object o : set)
            if (String.valueOf(o).equalsIgnoreCase(strKey)) {
                flag = true;
                prop.setProperty(strKey, strValue);
            }
        if (!flag) {
            prop.setProperty(strKey, strValue);
        }
        prop.store(out, "This file is to save the value");
        in.close();
        out.close();
        // Delete file
        if (Files.deleteIfExists(Paths.get(resourcesFolder + strFileName))) {
            //System.out.println("File is deleted !");
        } else {
            System.out.println("Sorry, unable to delete the file.");
        }
        // Change filename
        Path source = Paths.get(resourcesFolder + "temp.properties");
        Files.move(source, source.resolveSibling(strFileName));
    }

    public static String getData(String fileName, String fld) throws Exception {

        String path = resourcesFolder + fileName;
        String code = "";
        try {
            FileInputStream inputStream = new FileInputStream(path);
            props.load(inputStream);
            code = props.getProperty(fld);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}


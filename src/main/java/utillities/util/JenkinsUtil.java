//package utillities.util;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.apache.log4j.Logger;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//public class JenkinsUtil {
//   private static final Logger log = Logger.getLogger(JenkinsUtil.class);
//
//    // Create HttpClient
//    static HttpClient httpClient = HttpClient.newHttpClient();
//
//    // Set API endpoint URL
//    public static URI uri;
//
//    // Encode the username and password for basic authentication
//    static String username = "admin";
//    static String password = "CloudHMS@2022";
//    static String credentials = username + ":" + password;
//    static String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
//
//    static String jenkinURL = "http://35.213.134.118:8080/";
//
//    static String jenkinsCucumberReportURL = "cucumber-html-reports_11c01312-621d-3ac0-9300-75b3ad7e6462";
//
//    // ================================
//
//
//    public static String GetNextJobBuildNumber(String projectName) {
//        String resultKey = "";
//        uri = URI.create(jenkinURL + "job/" + projectName + "/api/json?tree=nextBuildNumber");
//
//        try {
//            // Create the request body JSON string
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Basic " + encodedCredentials)
//                    .build();
//            // Send the HTTP request
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            // Get the response body
//            String responseBody = response.body();
//            // Print the response body
////            System.out.println(responseBody);
//            Gson gson = new Gson();
//            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
//            if (jsonObject.has("nextBuildNumber")) {
//                resultKey = jsonObject.get("nextBuildNumber").getAsString();
//                System.out.println("nextBuildNumber: " + resultKey);
//            } else {
//                System.out.println("nextBuildNumber not found in JSON data");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resultKey;
//    }
//
//    public static String GetCurrentBuildNumner(String projectName) {
//        String resultKey = "";
//        uri = URI.create(jenkinURL + "job/" + projectName + "/lastBuild/api/json");
//
//        try {
//            // Create the request body JSON string
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Basic " + encodedCredentials)
//                    .build();
//            // Send the HTTP request
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            String responseBody = response.body();
//
//            Gson gson = new Gson();
//            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
//            if (jsonObject.has("number")) {
//                resultKey = jsonObject.get("number").getAsString();
//            } else {
//                System.out.println("nextBuildNumber not found in JSON data");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resultKey;
//    }
//
//    public static String GetLinkReportTag(String projectName, int currentBuildNumber, String tagName) {
//        String linkReportTag = "";
//        for (int i = 1; i <= 10; i++) {
//            int previousBuildNumber = currentBuildNumber - i;
//            try {
//                uri = URI.create(jenkinURL + "job/" + projectName + "/" + previousBuildNumber + "/" + jenkinsCucumberReportURL + "/overview-tags.html");
//                // Create the request body JSON string
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(uri)
//                        .header("Content-Type", "application/json")
//                        .header("Authorization", "Basic " + encodedCredentials)
//                        .build();
//                // Send the HTTP request
//                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                // Get the response body
//                String responseBody = response.body();
//                // if response code is 200, then get tag link
//                String tag = "";
//                if (response.statusCode() == 200) {
//                    // Print the response body
//                    Document doc = Jsoup.parse(responseBody);
//                    Elements rows = doc.select("tr");
//                    for (Element row : rows) {
//                        Element tagNameTemp = row.selectFirst("td.tagname");
//                        if (tagNameTemp != null) {
//                            tag = tagNameTemp.text();
//                            if (tag.equals(tagName)) {
//                                Element link = tagNameTemp.selectFirst("a");
//                                String href = link.attr("href");
//                                linkReportTag = jenkinURL + "job/" + projectName + "/" + currentBuildNumber + "/" + jenkinsCucumberReportURL + "/" + href;
//                                log.info("linkReportTag: " + linkReportTag);
//                                break;
//                            }
//                        }
//                    }
//                }
//                if (!linkReportTag.equals("")) {
//                    break;
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return linkReportTag;
//    }
//
//}

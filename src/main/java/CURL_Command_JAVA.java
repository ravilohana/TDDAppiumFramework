

import java.io.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CURL_Command_JAVA {
    public static void main(String[] args) throws IOException {
        String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
        //ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
       // String path = System.getProperty("user.dir") + "\\src\\main\\java\\sample.txt";
       // System.out.println(path);
       // processBuilder.directory(new File(path));
        Process process = Runtime.getRuntime().exec(command);
        InputStream inputStream = process.getInputStream();
        String results = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        System.out.println(results);
        System.out.println("====================================");









    }








}

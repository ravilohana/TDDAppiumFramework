

import java.io.*;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CURL_Command_JAVA {
    public static void main(String[] args) throws IOException {
//        String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
//        //ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//       // String path = System.getProperty("user.dir") + "\\src\\main\\java\\sample.txt";
//       // System.out.println(path);
//       // processBuilder.directory(new File(path));
//        Process process = Runtime.getRuntime().exec(command);
//        InputStream inputStream = process.getInputStream();
//        String results = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
//        System.out.println(results);
//        System.out.println("====================================");
//

//        ArrayList list1 = new ArrayList<>();
//        list1.add("Sauce Labs Backpack");
//        list1.add("Sauce Labs Bike Light");
//        list1.add("Sauce Labs Fleece Jacket");
//        list1.add("Sauce Labs Bolt T-Shirt");
//        list1.add("Sauce Labs Onesie");
//        list1.add("Test.allTheThings() T-Shirt");
//
//        System.out.println(list1.size());
//        Collections.sort(list1);
//        System.out.println("==========: "+list1);

        List<String> actualProductPrice = Arrays.asList("$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99");

         Collections.reverse(actualProductPrice);

        System.out.println(actualProductPrice);


        List<String> actualProductList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt");

        Collections.reverse(actualProductList);
        System.out.println(actualProductList);


//        [$49.99, $29.99, $15.99, $15.99, $9.99, $7.99]
//        [Test.allTheThings() T-Shirt, Sauce Labs Onesie, Sauce Labs Fleece Jacket, Sauce Labs Bolt T-Shirt, Sauce Labs Bike Light, Sauce Labs Backpack]


        String fs = File.separator;
        System.out.println(fs);
        String fileSeparator = FileSystems.getDefault().getSeparator();
        System.out.println(fileSeparator);

        String fsp = File.pathSeparator;
        System.out.println(fsp);

        String separator = System.getProperty("file.separator");
        System.out.println(separator);

    }








}

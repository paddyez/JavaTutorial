package org.paddy.http;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HTTP_Header {
    static final String url = "https://google.de";
    public static void parseHTTPheader () {
        Map<String, List<String>> headerFields;
        try {
            URLConnection connection = new URL(url).openConnection();
            headerFields = connection.getHeaderFields();
            List<String> cookiesL = headerFields.entrySet().stream()
                    .filter(k -> k.getKey() != null && k.getKey().equalsIgnoreCase("Set-Cookie"))
                    .map(Map.Entry::getValue)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            for(String cs : cookiesL) {
                System.out.println("== Cookie ==\n");
                Arrays.stream(cs.split("; ")).forEach(System.out::println);
                System.out.println("");
            }
            /*
            cookiesL.stream()
                    .map(s -> Arrays.asList(s.split("; ")))
                    .flatMap(List::stream)
                    .forEach(System.out::println);
            cookiesL.stream()
                    .map(cookieEntry -> Arrays.stream(cookieEntry.split("; ")));
            */
        }
        catch (MalformedURLException mfURLe) {
            System.err.println(mfURLe);
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}

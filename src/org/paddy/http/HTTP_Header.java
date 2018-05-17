package org.paddy.http;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class HTTP_Header {
    public static void parseHTTPheader () {
        Map<String, List<String>> headerFields;
        String url = "https://google.de";
        try {
            URLConnection connection = new URL(url).openConnection();
            headerFields = connection.getHeaderFields();
            System.out.println("\n== Cookie ==\n");
            String result = headerFields.entrySet().stream()
                    .filter(k -> k.getKey() != null && k.getKey().equalsIgnoreCase("Set-Cookie"))
                    .map(Map.Entry::getValue)
                    .flatMap(List::stream)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
        catch (MalformedURLException mfURLe) {
            System.err.println(mfURLe);
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}

import org.paddy.generics.GenericTypes;
import org.paddy.http.HTTP_Header;
import org.paddy.streams.CollectionManipulation;
import org.paddy.streams.Transactions;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        new CollectionManipulation();
        HTTP_Header.parseHTTPheader();
        new Transactions();
        System.out.println("== Generics ==");
        GenericTypes<Integer, String> gtNS = new GenericTypes<>();
        gtNS.setT("Teststring");
        System.out.println(gtNS.getT());
        gtNS.setN(Arrays.asList(1, 2 , 3, 4, 5, 6, 7, 8, 9, 10));
        int sum = gtNS.getN().stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}

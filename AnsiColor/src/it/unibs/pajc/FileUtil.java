import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static void printFile(String fileName, String pattern) {
        int count = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = in.readLine()) != null) {
                // TODO creare stringa row_id
                System.out.println(line);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}

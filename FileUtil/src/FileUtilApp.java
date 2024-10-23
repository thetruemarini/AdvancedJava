import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilApp {
    public static void main(String[] args) {
        printFile("./src/FileUtilApp.java");

    }

    public static void printFile(String fileName) {
        int count = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                count++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
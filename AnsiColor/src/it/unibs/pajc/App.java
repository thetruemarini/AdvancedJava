public class App {
    public static void main(String[] args) throws Exception {
        
        Colorizer.ANSI_BLUE.print("questo testo sarà blu\n");
        Colorizer.ANSI_RED.print("questo testo sarà rosso\n");

        FileUtil.printFile("./src/FileUtilApp.java", "u");
    }
}
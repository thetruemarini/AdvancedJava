package it.unibs.pajc;

public class Colorizer {

    public static final Colorizer ANSI_RESET  = new Colorizer ("\u001B[0m"); // RESET
    public static final Colorizer ANSI_BLACK  = new Colorizer ("\033[0;30m");   // BLACK
    public static final Colorizer ANSI_RED    = new Colorizer ("\033[0;31m");     // RED
    public static final Colorizer ANSI_GREEN  = new Colorizer ("\033[0;32m"); // GREEN
    public static final Colorizer ANSI_YELLOW = new Colorizer ("\033[0;33m");  // YELLOW
    public static final Colorizer ANSI_BLUE   = new Colorizer ("\033[0;34m");    // BLUE
    public static final Colorizer ANSI_PURPLE = new Colorizer ("\033[0;35m"); // PURPLE
    public static final Colorizer ANSI_CYAN   = new Colorizer ("\033[0;36m");    // CYAN
    public static final Colorizer ANSI_WHITE  = new Colorizer ("\033[0;37m");   // WHITE

    private final String ansi_code;

    public Colorizer(String ansi_code) {
        this.ansi_code = ansi_code;
    }

    public String colorize(String string) {
        return this.ansi_code + string + ANSI_RESET;
    }

    public void print(String s){
        System.out.print(colorize(s));
    }

    public String toString(){ return ansi_code; } 
}

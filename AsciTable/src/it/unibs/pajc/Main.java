package AsciTable.src.it.unibs.pajc;

public class Main {
    public static void main(String[] args) {

        int i = 42;

        // System.out.println("%x", i); // %x conferisce il numero in esadecimali
        // System.out.println("%c", i); // %c conferisce il carattere asci corrispondente

        printAsciTable();
    }

    public static void printAsciTable(){

        for(int j=0; j<128; j++){
            System.out.printf("%c", j);
            if (j%16 == 0 && j!=0) System.out.printf("\n%d\t", j);
        }
    }
}


import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class InputReader {

    private static ArrayList<InputStream> inputStreams = new ArrayList<>();

    // scanner för att läsa indata från strömmen
    private Scanner scanner;

    // konstruktor med inputstream parameter
    public InputReader(InputStream inputStream) {

        // kasta undantag om instans av inputreader redan skapats med samma inputstream
        if (inputStreams.contains(inputStream)) {
            throw new IllegalStateException("Error: an instance already exists for this inputStream!");
        }

        // scanner-objekt för att läsa från givna strömmen
        this.scanner = new Scanner(inputStream);
        inputStreams.add(inputStream);
    }

    // Konstruktor utan parameter, som använder system.in som parameter
    public InputReader() {
        this(System.in);
    }

    // metoden läser in ett heltal
    public int readInt(String prompt) {
        System.out.print(prompt + "> ");
        int result = scanner.nextInt();

        scanner.nextLine();
        return result;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt + "> ");
        double result = scanner.nextDouble();

        // Rensa bufferten för att udvika problem med nästa inläsning
        scanner.nextLine();
        return result;
    }

    public String readString(String prompt) {
        System.out.print(prompt + "> ");
        // vänta tills ny rad finns tillgänglig + returnera inlästa strängen
        while (!scanner.hasNextLine()) {
        }

        return scanner.nextLine();
    }

    // metod för att stänga scanner-objektet och stänga insputstream
    public void close() {
        scanner.close();
    }
}
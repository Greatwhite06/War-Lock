package brocode.warlock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.lang.String;

public class VirusTypes {
    private enum type {TROJAN, WORMS, RANSOMWARE}
    private static File virusFile;
    private static String trojanRead;
    private static String wormsRead;
    private static String ransomWareRead;

    public VirusTypes(){

    }

    public static String trojan() throws IOException {
        trojanRead = readFile("core/assets/virusDescriptions/TROJANDS.txt", Charset.defaultCharset());
        return trojanRead;
    }

    public static String worms() throws IOException {
        //virusFile = new File("core/assets/virusDescriptions/WORMSDS.txt");
        wormsRead = readFile("core/assets/virusDescriptions/WORMSDS.txt", Charset.defaultCharset());
        return wormsRead;
    }

    public static String ransomWare() throws IOException {
        ransomWareRead = readFile("core/assets/virusDescriptions/RANSOMWAREDS.txt", Charset.defaultCharset());
        return ransomWareRead;
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private static void printOut(){
        System.out.println(trojanRead);
    }
}

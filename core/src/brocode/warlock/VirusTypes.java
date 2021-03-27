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



    //reads TROJANDS.txt file by EACH LINE and returns it as a string
    public static String trojanR() throws IOException {
        trojanRead = readFile("core/assets/virusDescriptions/TROJANDS.txt", Charset.defaultCharset());
        return trojanRead;
    }
    //reads WORMSDS.txt file by EACH LINE and returns it as a string
    public static String wormsR() throws IOException {
        //virusFile = new File("core/assets/virusDescriptions/WORMSDS.txt");
        wormsRead = readFile("core/assets/virusDescriptions/WORMSDS.txt", Charset.defaultCharset());
        return wormsRead;
    }
    //reads RANSOMEWAREDS.txt file by EACH LINE and returns it as a string
    public static String ransomWareR() throws IOException {
        ransomWareRead = readFile("core/assets/virusDescriptions/RANSOMWAREDS.txt", Charset.defaultCharset());
        return ransomWareRead;
    }
    //Reads file lines as bytes and stops at a charSet code (In this case its endline).
    //it then uses the array of bytes to make strings from new lines.
    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}

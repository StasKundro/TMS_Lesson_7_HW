import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес файла document.txt");
        String str1 = scanner.nextLine();
        String str2 = str1.replace("document", "result");
        String str3 = str1.replace("document","wrong");

        try (FileOutputStream result = new FileOutputStream(str2);
             FileOutputStream wrong = new FileOutputStream(str3)) {
            String str = "";
            Scanner in = new Scanner(new File(str1));
            while (in.hasNext()) {
                str += in.nextLine() + "\r\n";
            }
            in.close();

            String[] x = str.split("\n");
            for (int z = 0; z < x.length; z++) {
                if (x[z].startsWith("docnum") || x[z].startsWith("kontract")) {
                    String[] y = x[z].split(" ");
                    if (y[1].length() == 16) {
                        Files.write(Paths.get(str2), x[z].getBytes(), StandardOpenOption.APPEND);
                    } else {
                        Files.write(Paths.get(str3), x[z].getBytes(), StandardOpenOption.APPEND);
                    }
                } else {
                    Files.write(Paths.get(str3), x[z].getBytes(), StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
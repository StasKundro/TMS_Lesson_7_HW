import java.io.File;
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
        String str3 = str1.replace("document", "wrong");

        try {
            String str = "";
            Scanner in = new Scanner(new File(str1));
            while (in.hasNext()) {
                str += in.nextLine() + "\r\n";
            }
            in.close();

            String[] x = str.split("\n");
            for (int z = 0; z < x.length; z++) {
                if (x[z].startsWith("docnum") || x[z].startsWith("kontract")) {
                    if(x[z].length() == 16) {
                        Files.write(Paths.get(str2), x[z].getBytes(), StandardOpenOption.APPEND);
                    } else {
                        String wrongLength = "не верная длина\n";
                        Files.write(Paths.get(str3), x[z].getBytes(), StandardOpenOption.APPEND);
                        Files.write(Paths.get(str3), wrongLength.getBytes(), StandardOpenOption.APPEND);
                    }
                } else {
                    String wrongDoc = "не верный документ\n";
                    Files.write(Paths.get(str3), x[z].getBytes(), StandardOpenOption.APPEND);
                    Files.write(Paths.get(str3), wrongDoc.getBytes(), StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
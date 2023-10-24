package Information;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileAccessor {
    final public static String FOLDER = "Storage";
    public static void createDirectories(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path);
    }
    public static boolean createFile(String filePath) throws IOException {
        File file = new File(filePath);
        return file.createNewFile();
    }
    public static void rewriteFile(String filePath, List<String> input) throws FileNotFoundException {
        File file = new File(filePath);
        PrintWriter fileOut = new PrintWriter(file);
        for (String entry : input) {
            fileOut.println(entry);
        }
        fileOut.close();
    }
    public static void rewriteFile(String filePath, String input) throws FileNotFoundException {
        File file = new File(filePath);
        PrintWriter fileOut = new PrintWriter(file);
        fileOut.println(input);
        fileOut.close();
    }
    public static String getFileLine(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner input = new Scanner(file);
        return input.nextLine();
    }
    public static List getFileList(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner input = new Scanner(file);
        List<String> output = new ArrayList<>();
        while (input.hasNextLine()) {
            output.add(input.nextLine());
        }
        input.close();
        return output;
    }
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }
    public static String createFilePathString(String parent, String child) {
        return parent + "/" + child;
    }
    public static String createFilePathString(String child) {
        return createFilePathString(FOLDER, child);
    }
}

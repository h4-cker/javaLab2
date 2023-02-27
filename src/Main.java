import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter pathname: ");
        File file = new File(scanner.nextLine());
        String filename = file.getName();
        String path = file.getPath().substring(0, file.getPath().length() - file.getName().length());
        if (!file.exists()) {
            System.out.printf("File: \"%s\" does not exist in directory: \"%s\"\n", filename, path);
            throw new FileNotFoundException();
        } else if (!file.canRead()) {
            System.out.printf("Unable to read the contents of the file: \"%s\"\n", file.getPath());
            throw new FileNotFoundException();
        }
        int upperEn = 0, lowerEn = 0;
        try (FileReader fileReader = new FileReader(file)) {
            for (int i = 0; i < file.length(); i++) {
                char s = (char) fileReader.read();
                if (Character.isUpperCase(s) & (s > 64 & s < 91)) {
                    upperEn++;
                } else if (Character.isLowerCase(s) & (s > 96 & s < 123)) {
                    lowerEn++;
                }
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File was not found");
        }
        System.out.println("Enter pathname: ");
        File newFile = new File(scanner.nextLine());
        if (!newFile.exists()) {
            if (newFile.createNewFile()) {
                System.out.printf("New file: \"%s\" created\n", newFile.getName());
            }
        }
        try (FileWriter fileWriter = new FileWriter(newFile)) {
            fileWriter.write("\nUpper - ");
            fileWriter.write(Integer.toString(upperEn));
            fileWriter.write("\nLower - ");
            fileWriter.write(Integer.toString(lowerEn));
        } catch (IOException e) {
            throw new FileNotFoundException("File was not found");
        }
    }
}

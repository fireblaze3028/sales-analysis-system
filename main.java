import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        // Initalise Variables
        int[] frequency = new int[9]; // 9 indexes for each number (1, 2, 3, 4, etc)
        // Get the file
        File file = getFile();
        Scanner reader = new Scanner(file);

        // Get the frequency of each number in the file
        frequency = populateFrequency(file);

        // Close the scanner
        reader.close();
    }
    public static int[] populateFrequency(File file) {

    }
    public static File getFile() {
        Scanner reader = new Scanner(System.in);
        // File location
        String fileLocation = "./sales.csv";
        // Open the file
        File file = new File(fileLocation);
        // While the file isn't in the folder
        while (!file.exists()) {
            System.out.println("You seem to not have the file in the same folder as Main.java. Please specify the file:");
            fileLocation = reader.nextLine();
            file = new File(fileLocation);
        }
        // Close the scanner
        reader.close();
        return file;
    }
}
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

        for (int i: frequency) {
            System.out.print(i + ",");
        }

        // Close the scanner
        reader.close();
    }
    public static int[] populateFrequency(File file) throws Exception {
        // Initialise Scanner
        Scanner reader = new Scanner(file);
        int temp; // Used for temporary storage

        String line; // Used for each line of the csv file

        reader.nextLine(); // This is to skip the title of the csv file

        // Each index is a number i.e 0th index is #1, 1st index is #2, so on
        int[] frequency = new int[9];

        // While there is a next line
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            line = line.split(",")[1]; // Since we know the first value is

            // For each number in the line
            temp = Integer.parseInt(line.charAt(0) + ""); // Need "" to automatically convert to string

            // Since we aren't checking for numbers that are 0, we don't need it
            if (temp != 0) {
                frequency[temp - 1]++;
            }
        }

        reader.close(); // Close the scanner
        return frequency;
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
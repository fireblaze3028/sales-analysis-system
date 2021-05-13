/*
 * Date: May 6, 2021
 * Names: Marc and Nawkito
 * Teacher: Mr. Ho
 * Description: System to analyze sales data and determine if it is fradulent or not.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

/**
 * @author Marc and Nawkito
 * @version 1.0
 */
public class AnalysisSystem {
    public static void main(String[] args) throws Exception {
        // Initalise Variables
        int[] frequency = new int[9]; // 9 indexes for each number (1, 2, 3, 4, etc), 0th index is for number 1
        double[] frequencyPercentage = new double[9]; // Used for graphing
        // Get the file
        File file = getFile();

        // Get the frequency of each number in the file
        frequency = populateFrequency(file);

        // Get the sum of frequency
        int frequencySum = getSum(frequency);

        // Get the percentages to use for graphing
        frequencyPercentage = getPercentage(frequency, frequencySum);

        // Check to see if there's fraud
        checkFraud(frequency, frequencySum);

        // Export the data to a csv file
        exportData(frequency, frequencySum, frequencyPercentage);

    }

    /**
     * Gets the percentage of any array
     * 
     * @param arr - an array of type int
     * @param sum - the sum of the array
     * @return The percentage of each index in an array
     */
    public static double[] getPercentage(int[] arr, int sum) {
        // Initalise Variables
        double[] frequencyPercentage = new double[9];

        // For each index, get percentage and round to 2 digits
        for (int i = 0; i < arr.length; i++) {
            frequencyPercentage[i] = round(100.0 * arr[i] / sum, 2);
        }

        return frequencyPercentage;
    }

    /**
     * Exports the data from the array into a csv file.
     * 
     * @param freq - the array for the frequency of each number
     * @param sum - the sum of all frequencies in each number
     * @param freqPercentage - the array for the frequency of each number displayed in percentages
     * @throws Exception If the file is invalid
     */
    public static void exportData(int[] freq, int sum, double[] freqPercentage) throws Exception {
        // Initialise Variables
        File export = new File("export.csv");
        String text = "";
        String delimiter = ",";
        FileWriter writer = new FileWriter("export.csv");

        // Create file if it doesn't exist already
        if (!export.exists()) {
            export.createNewFile();
        }

        // First line of the csv file will be these titles
        writer.write("Digit,Frequency,Percentage");

        for (int i = 0; i < freq.length; i++) {
            text += "\n";
            // Digit
            text += i + 1;
            text += delimiter;
            // Frequency
            text += freq[i];
            text += delimiter;
            // Percentage
            text += freqPercentage[i];
            text += "%";
        }
        // Write the data to file
        writer.write(text);
        // Close the writer
        writer.close();
    }

    /**
     * Rounds a number by how many digits you enter.
     * @param num - the number you want to round
     * @param roundBy - the amount of digits you want to round by.
     * For example, if you enter 2 as the number of digits, 323.253251 will become 323.25.
     * @return The rounded number.
     */
    public static double round(double num, int roundBy) {
        double temp = num * Math.pow(10, roundBy);
        temp = Math.round(temp);
        return temp / Math.pow(10, roundBy);
    }

    /**
     * Checks the first digit frequency to see if there is fraud or not.
     * 
     * @param freq - the array that contains the frequency of each number
     * @param sum - the sum of every leading digit
     */
    public static void checkFraud(int[] freq, int sum) {
        // If the frequency of one is between 0.29 - 0.32, fraud probably did not occur
        if (1.0 * freq[0] / sum > 0.32 || 1.0 * freq[0] / sum < 0.29) {
            System.out.println("Fraud may have occured.");
        }
        else {
            System.out.println("Fraud probably did not occur.");
        }
    }

    /**
     * Takes an array and returns the sum of each element.
     * 
     * @param arr - an array of type integer
     * @return The sum of every element in the array
     */
    public static int getSum(int[] arr) {
        int sum = 0; // Initialise Variable

        // For each integer in the array, add
        for (int i: arr) {
            sum += i;
        }
        return sum;
    }

    /**
     * Populates the frequency of the leading digit for every area.
     * 
     * @param file - the csv file that contains the sales
     * @return An array that gives the frequency of each digit
     * @throws FileNotFoundException If there is no file that is given 
     */
    public static int[] populateFrequency(File file) throws FileNotFoundException {
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
    
    /**
     * Gets the csv file from the folder.
     * 
     * @return The file that was specified
     */
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
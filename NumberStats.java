import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class NumberStats {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("Book1.csv"))) {
        
            String line = br.readLine();
            if (line  == null){
                System.out.println("Data file is empty.");
                return;
            }

            String[] numberStrings = line.split(",");
            int[] numbers = new int[numberStrings.length];

            for(int i = 0; i < numberStrings.length; i++){
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }

            int sum = Arrays.stream(numbers).sum();
            int lowest = Arrays.stream(numbers).min().orElse(0);
            int highest = Arrays.stream(numbers).max().orElse(0);
            double average = Arrays.stream(numbers).average().orElse(0.0);
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("stats.txt"))){
                bw.write("The sum of the numbers is: " + sum);
                bw.newLine();
                bw.write("The lowest number is: " + lowest);
                bw.newLine();
                bw.write("The highest number is: " + highest);
                bw.newLine();
                bw.write("The average of the numbers is: " + average);
            }

        } catch(IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
                System.err.println("Error: The file contains non-numeric data. " + e.getMessage());
        }
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class task2_StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Student Grade Calculator ---");

        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = 0;
        try {
            numberOfSubjects = scanner.nextInt();
            if (numberOfSubjects <= 0) {
                System.out.println("Number of subjects must be positive. Exiting.");
                scanner.close();
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a whole number for subjects. Exiting.");
            scanner.close();
            return;
        }

        int totalMarks = 0;
        for (int i = 1; i <= numberOfSubjects; i++) {
            int marks;
            while (true) {
                System.out.printf("Enter marks for Subject %d (out of 100): ", i);
                try {
                    marks = scanner.nextInt();
                    if (marks < 0 || marks > 100) {
                        System.out.println("Marks must be between 0 and 100. Please try again.");
                    } else {
                        totalMarks += marks;
                        break; // Exit the inner while loop if marks are valid
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a whole number for marks.");
                    scanner.next(); // Consume the invalid input
                }
            }
        }

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        // Grade Calculation
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display Results
        System.out.println("\n--- Results ---");
        System.out.printf("Total Marks: %d%n", totalMarks);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.printf("Grade: %s%n", grade);

        scanner.close();
    }
}
import java.util.Random;
import java.util.Scanner;

public class task1_GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRoundsWon = 0;

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("\n--- Welcome to Guess the Number! ---");

            int lowerBound = 1;
            int upperBound = 100;
            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int maxAttempts = 7; // You can adjust this
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.printf("I have generated a number between %d and %d. You have %d attempts.%n", lowerBound, upperBound, maxAttempts);

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess;
                try {
                    userGuess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                    continue; // Skip the rest of the loop and ask for input again
                }

                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.printf("Congratulations! You guessed the number %d in %d attempts!%n", numberToGuess, attempts);
                    guessedCorrectly = true;
                    totalRoundsWon++;
                } else if (userGuess < numberToGuess) {
                    System.out.printf("Too low! Attempts remaining: %d%n", maxAttempts - attempts);
                } else {
                    System.out.printf("Too high! Attempts remaining: %d%n", maxAttempts - attempts);
                }
            }

            if (!guessedCorrectly) {
                System.out.printf("Sorry, you ran out of attempts. The number was %d.%n", numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgainResponse = scanner.nextLine().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
        }

        System.out.printf("\n--- Game Over! You won %d round(s). ---%n", totalRoundsWon);
        scanner.close();
    }
}
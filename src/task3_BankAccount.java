import java.util.InputMismatchException;
import java.util.Scanner;

// 4. Create a class to represent the user's bank account, which stores the account balance.
class BankAccount {
    private double balance;

    // Constructor to initialize the account with an initial balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
        }
    }

    // 3. Implement method for checkBalance()
    public double getBalance() {
        return balance;
    }

    // 3. Implement method for deposit(amount)
    public void deposit(double amount) {
        // 6. Validate user input (positive amount)
        if (amount > 0) {
            balance += amount;
            // 7. Display appropriate messages
            System.out.printf("Successfully deposited %.2f. New balance: %.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // 3. Implement method for withdraw(amount)
    public boolean withdraw(double amount) {
        // 6. Validate user input (positive amount and sufficient balance)
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Current balance: " + balance);
            return false;
        }
        balance -= amount;
        // 7. Display appropriate messages
        System.out.printf("Successfully withdrew %.2f. New balance: %.2f%n", amount, balance);
        return true;
    }
}

// 1. Create a class to represent the ATM machine.
class ATM {
    // 5. Connect the ATM class with the user's bank account class
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    // 2. Design the user interface for the ATM, including options
    public void displayMenu() {
        System.out.println("\n--- Welcome to the ATM ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Main ATM operation loop
    public void run() {
        int choice;
        do {
            displayMenu();
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        handleDeposit();
                        break;
                    case 3:
                        handleWithdrawal();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                // 6. Validate user input (non-numeric input)
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input to prevent infinite loop
                choice = 0; // Set choice to 0 to re-display menu
            }
        } while (choice != 4);

        scanner.close(); // Close the scanner when the ATM session ends
    }

    // 3. Implement method for checkBalance()
    private void checkBalance() {
        // 7. Display appropriate messages
        System.out.printf("Your current balance is: %.2f%n", userAccount.getBalance());
    }

    // Helper method to handle deposit input and call BankAccount deposit
    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        try {
            double amount = scanner.nextDouble();
            userAccount.deposit(amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next(); // Consume the invalid input
        }
    }

    // Helper method to handle withdrawal input and call BankAccount withdraw
    private void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = scanner.nextDouble();
            userAccount.withdraw(amount); // withdraw method handles its own messages
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next(); // Consume the invalid input
        }
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(1000.00); // Starting with $1000

        // Create an ATM instance connected to the bank account
        ATM atm = new ATM(account);

        // Run the ATM simulation
        atm.run();
    }
}

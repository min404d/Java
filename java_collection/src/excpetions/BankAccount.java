package excpetions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class overbalance extends Exception {
    public overbalance(String message) {
        super(message);
    }
}

public class BankAccount {
    private String accid;
    private String username;
    private double balance;

    public BankAccount(String accid, String username, double currentbal) {
        this.accid = accid;
        this.username = username;
        this.balance = currentbal;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) throws overbalance {
        if (amount > balance) {
            throw new overbalance("Insufficient balance for withdrawal.");
        } else {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        }
    }

    public void checkbal() {
        System.out.println("Current balance: " + balance);
    }

    public void accfromfile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("bank.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                this.accid = details[0];
                this.username = details[1];
                this.balance = Double.parseDouble(details[2]);
            }
            System.out.println("Account loaded from file:");
            System.out.println("Account ID : " + accid);
            System.out.println("Account User : " + username);
            System.out.println("Balance : " + balance);
        }
    }

    public static void calculateInterest(String input) throws NumberFormatException {
        double interestRate = Double.parseDouble(input);
        System.out.println("Interest rate: " + interestRate);
    }

    public void bankfunction() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Select an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Load Account from File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        try {
                            withdraw(withdrawAmount);
                        } catch (overbalance e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        checkbal();
                        break;
                    case 4:
						/*
						 * System.out.print("Enter filename to load account data: "); String filename =
						 * scanner.next();
						 */
                        try {
                            accfromfile();
                        } catch (IOException e) {
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Looking for bank.txt in: " + System.getProperty("user.dir"));

                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        } while (choice != 5);
        scanner.close();
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", "John Doe", 1000.0);
        try {
            account.bankfunction();
            calculateInterest("abc");
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Transaction Complete");
        }
    }
}

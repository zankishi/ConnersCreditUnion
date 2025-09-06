// Main class for running the Credit Union Program
import java.util.Scanner;
import java.util.ArrayList;

public class ConnersCreditUnion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>(); // List to hold all accounts

        System.out.println("Welcome to Conner's Credit Union");

        while (true) {
            // Main Menu
            System.out.println("\nMain Menu");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Choose an option: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input.trim()); // Parse choice safely
            } catch (NumberFormatException e) {
                System.out.println("Please enter 1, 2, or 3.");
                continue; //Goes back to the top of the loop
            }

            if (choice== 1) {
                // Create Account
                String username;
                while (true) {
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    boolean taken = false;
                    for (Account acc : accounts) {
                        if (username.equals(acc.getUsername())) {
                            taken = true;
                            break;
                        }
                    }

                    if (taken) {
                        System.out.print("Please select a username that is not taken.");
                    } else {
                        break;
                    }
                }
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                Account newAcc = new Account(username, password);
                accounts.add(newAcc);
                System.out.println("Account successfully created for user: " + newAcc.getDisplayUsername());
            } else if (choice ==2) {
                //Login
                System.out.print("Enter username: ");
                String enteredUser = scanner.nextLine();
                System.out.print("Enter password: ");
                String enteredPass = scanner.nextLine();
                Account loggedIn = null;
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(enteredUser) && acc.checkPassword(enteredPass)) {
                        loggedIn = acc;
                        break;
                    }
                }
                if (loggedIn != null) {
                    System.out.println("Login successful! Welcome, " + loggedIn.getDisplayUsername());
                    while (true) {
                        // Account Menu
                        System.out.println("\nAccount Menu");
                        System.out.println("1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Check Balance");
                        System.out.println("4. View Transaction History");
                        System.out.println("5. Logout");
                        System.out.println("Choose an option ");
                        String accInput = scanner.nextLine();
                        int accChoice;
                        try {
                            accChoice = Integer.parseInt(accInput.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter 1-5");
                            continue;
                        }

                        if (accChoice == 1) {
                            // Deposit
                            System.out.println("Enter deposit amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            if (loggedIn.deposit(amount)) {
                                System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, loggedIn.getBalance());
                            } else {
                                System.out.println("Deposit failed. Please enter a positive amount.");
                            }
                        } else if (accChoice == 2) {
                            // Withdraw
                            System.out.println("Enter withdrawal amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            if (loggedIn.withdraw(amount)) {
                                System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, loggedIn.getBalance());
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                        } else if (accChoice == 3) {
                            // Check Balance
                            System.out.printf("Current balance: $%.2f%n", loggedIn.getBalance());
                        } else if (accChoice == 4) {
                            // View transaction history
                            System.out.println("Transaction History");
                            for (String record : loggedIn.getTransactions()) {
                                System.out.println("- " + record);
                            }
                        } else if (accChoice == 5) {
                            System.out.println("Logging out...");
                            break;
                        } else {
                            System.out.println("Please enter 1-5.");
                        }
                    }
                } else {
                    System.out.println("Login failed. Invalid username or password.");
                }
            } else if (choice == 3) {
                // Exit program
                System.out.println("Goodbye!");
                break; // Exits the initial while loop
            } else {
                System.out.println("Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}
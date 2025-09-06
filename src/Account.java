// Class representing a bank account
import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor (runs when a new Account is made)
    public Account(String username, String password) {
        this.username = username.trim().toLowerCase();
        this.password = password;
        this.balance = 0.00;
        this.transactions = new ArrayList<>();
    }

    // Simple getter for Username (Stored in lowercase)
    public String getUsername() {
        return username;
    }
    // Display username with first letter capitalized
    public String getDisplayUsername() {
        if (username == null || username.isEmpty()) {
            return username;
        }
        return username.substring(0,1).toUpperCase() + username.substring(1);
    }

    // Password check
    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    // Balance check
    public double getBalance() {
        return balance;
    }

    // Add to balance and record the deposit
    public boolean deposit(double amount) {
        if (amount <= 0) {
            transactions.add(String.format("Failed deposit of $%.2f", amount));
            return false;
        }
        balance += amount;
        transactions.add(String.format("Deposited $%.2f", amount));
        return true;
    }

    // Withdraw attempt with validation and transaction record
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            transactions.add(String.format("Failed withdrawal of $%.2f", amount));
            return false;
        }
        if (amount <= balance) {
            balance -= amount;
            transactions.add(String.format("Withdrew $%.2f", amount));
            return true;
        } else {
            transactions.add(String.format("Failed withdrawal of $%.2f", amount));
            return false;
        }
    }

    // Get full transaction history
    public ArrayList<String> getTransactions() {
        return transactions;
    }
}
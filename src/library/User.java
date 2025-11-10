/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: User class with enhanced constructors and access control.
            
            **WEEK 3 UPDATES:**
            - Multiple constructors
            - Proper access specifiers
            - Input validation
   -------------------------------------------------------------- */

package library;

/**
 * Represents a user of the library system.
 * Demonstrates composition (User "has a" Shelf).
 */
public class User {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private fields - encapsulation
    private final String name;
    private double wallet;
    private final Shelf shelf;
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 1: Full constructor with wallet balance
    /**
     * Full constructor creating a user with starting balance.
     * Validates input parameters.
     */
    public User(String name, double startingBalance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        this.name = name.trim();
        this.wallet = Math.max(0, startingBalance);  // No negative balance
        this.shelf = new Shelf(this);
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 2: Default constructor with zero balance
    /**
     * Constructor with default zero balance.
     * Demonstrates constructor chaining.
     */
    public User(String name) {
        this(name, 0.0);  // Chain to full constructor
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public methods for wallet management
    
    public double getWallet() {
        return wallet;
    }
    
    /**
     * Add funds to wallet.
     * @param amount Amount to add (must be positive)
     */
    public void addFunds(double amount) {
        if (amount > 0) {
            this.wallet += amount;
        }
    }
    
    /**
     * Debit funds from wallet.
     * **WEEK 3: ACCESS SPECIFIERS - package-private**
     * Only Library class can debit funds.
     */
    void debit(double amount) {
        if (amount > 0 && amount <= wallet) {
            wallet -= amount;
        }
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public accessor methods
    
    public Shelf getShelf() {
        return shelf;
    }

    public String getName() {
        return name;
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Package-private helper methods (used by Library)
    
    void addBookToShelf(Book b) {
        shelf.addBook(b);
    }

    Book removeBookFromShelf(int bookId) {
        return shelf.removeBook(bookId);
    }

    Book findBookOnShelf(int bookId) {
        return shelf.findBook(bookId);
    }
    
    @Override
    public String toString() {
        return String.format("User: %s, Wallet: $%.2f", name, wallet);
    }
}

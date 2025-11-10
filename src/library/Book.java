/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Base class for all book types. Now implements Borrowable
            interface to allow books to be borrowed from the library.
            
            Demonstrates:
            - **Inheritance** (derived classes extend this)
            - **Interface implementation** (implements Borrowable)
   -------------------------------------------------------------- */

package library;

/**
 * Base class that holds the common attributes of any book.
 * 
 * **WEEK 2: Now implements Borrowable interface**
 */
public class Book implements Borrowable {
    // ---------- static ID generator ----------
    protected static int NEXT_ID = 1;

    // ---------- instance fields ----------
    protected final int id;          // unique identifier
    protected String title;
    protected String author;
    protected double price;
    protected String genre;          // e.g., "Science Fiction"
    protected double rating;         // 0.0 – 5.0 stars
    
    // ---------- borrowable state ----------
    private boolean borrowed;
    private String borrower;
    private int borrowPeriodDays;

    /**
     * Constructor for the base Book.
     */
    public Book(String title, String author, double price,
                String genre, double rating) {
        this.id = NEXT_ID++;
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
        this.rating = rating;
        this.borrowed = false;
        this.borrower = null;
        this.borrowPeriodDays = 14;  // default 2 weeks
    }

    /** Returns a short description – overridden by children to add type. */
    public String getType() {
        return "Generic Book";
    }

    /** Human-readable representation used by the UI. */
    @Override
    public String toString() {
        String status = borrowed ? "[BORROWED]" : "[AVAILABLE]";
        return String.format("%-3d %-25s %-20s %-12s $%-7.2f %-6.1f %-10s %s",
                id, title, author, genre, price, rating, getType(), status);
    }

    // -----------------------------------------------------------------
    // Borrowable Interface Implementation
    // **WEEK 2: Demonstrates interface implementation**
    // -----------------------------------------------------------------
    
    @Override
    public boolean borrow(String userName) {
        if (borrowed) {
            return false;
        }
        this.borrowed = true;
        this.borrower = userName;
        return true;
    }
    
    @Override
    public boolean returnItem() {
        if (!borrowed) {
            return false;
        }
        this.borrowed = false;
        this.borrower = null;
        return true;
    }
    
    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
    
    @Override
    public String getBorrower() {
        return borrower;
    }
    
    @Override
    public int getBorrowPeriodDays() {
        return borrowPeriodDays;
    }
    
    protected void setBorrowPeriod(int days) {
        this.borrowPeriodDays = days;
    }

    // -----------------------------------------------------------------
    // Getters / setters (only the ones we need for the demo)
    // -----------------------------------------------------------------
    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
}

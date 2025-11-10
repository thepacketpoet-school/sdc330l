/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Abstract base class for all media items in the library.
            
            **WEEK 3 REQUIREMENTS:**
            - Demonstrates ABSTRACTION with abstract class and methods
            - Demonstrates CONSTRUCTORS with multiple constructor types
            - Demonstrates ACCESS SPECIFIERS (private, protected, public)
   -------------------------------------------------------------- */

package library;

public abstract class Media implements Borrowable {
    
    private static int nextId = 1;

    public static void setNextId(int id) {
        nextId = id;
    }
    
    private final int id;
    private String title;
    private String creator;
    private double price;
    private double rating;
    
    private boolean borrowed;
    private String borrower;
    private int borrowPeriodDays;
    
    // Regular constructor - auto-increments ID
    protected Media(String title, String creator, double price, double rating) {
        this.id = nextId++;
        this.title = title;
        this.creator = creator;
        this.price = price;
        this.rating = rating;
        this.borrowed = false;
        this.borrower = null;
        this.borrowPeriodDays = 14;
    }
    
    // **WEEK 4: DATABASE CONSTRUCTOR**
    // Special constructor for loading from database - uses existing ID
    protected Media(int existingId, String title, String creator, double price, double rating) {
        this.id = existingId;  // Use database ID, don't increment
        this.title = title;
        this.creator = creator;
        this.price = price;
        this.rating = rating;
        this.borrowed = false;
        this.borrower = null;
        this.borrowPeriodDays = 14;
    }
    
    protected Media(String title, String creator, double price) {
        this(title, creator, price, 0.0);
    }
    
    protected Media(String title, String creator) {
        this(title, creator, 0.0, 0.0);
    }
    
    public abstract String getMediaType();
    public abstract String getTypeSpecificInfo();
    public abstract double calculateLateFee(int daysLate);
    
    protected void setBorrowPeriod(int days) {
        this.borrowPeriodDays = days;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        String status = borrowed ? "[BORROWED]" : "[AVAILABLE]";
        return String.format("%-3d %-25s %-20s $%-7.2f %-6.1f %-10s %s",
                id, title, creator, price, rating, getMediaType(), status);
    }
    
    @Override
    public boolean borrow(String userName) {
        if (borrowed) return false;
        this.borrowed = true;
        this.borrower = userName;
        return true;
    }
    
    @Override
    public boolean returnItem() {
        if (!borrowed) return false;
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
}
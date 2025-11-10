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

/**
 * Abstract base class for all media items (books, magazines, DVDs, etc.)
 * 
 * **WEEK 3: ABSTRACTION**
 * This abstract class provides common functionality for all media types
 * while requiring derived classes to implement type-specific behavior.
 * 
 * Benefits of this abstraction:
 * - Enforces consistent structure across all media types
 * - Reduces code duplication (ID generation, common attributes)
 * - Makes it easy to add new media types (magazines, DVDs, etc.)
 * - Allows polymorphic handling of different media types
 */
public abstract class Media implements Borrowable {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Static fields for ID generation - private for encapsulation
    private static int nextId = 1;
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Instance fields - private with protected getters for inheritance
    private final int id;
    private String title;
    private String creator;  // Author, artist, director, etc.
    private double price;
    private double rating;
    
    // Borrowable state - private for encapsulation
    private boolean borrowed;
    private String borrower;
    private int borrowPeriodDays;
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 1: Full constructor with all parameters
    /**
     * Full constructor for creating a media item with all details.
     * This is the primary constructor that initializes all fields.
     */
    protected Media(String title, String creator, double price, double rating) {
        this.id = nextId++;
        this.title = title;
        this.creator = creator;
        this.price = price;
        this.rating = rating;
        this.borrowed = false;
        this.borrower = null;
        this.borrowPeriodDays = 14;  // default
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 2: Partial constructor (no rating) - uses constructor chaining
    /**
     * Partial constructor that defaults rating to 0.0.
     * Demonstrates constructor chaining by calling the full constructor.
     */
    protected Media(String title, String creator, double price) {
        this(title, creator, price, 0.0);  // Chain to full constructor
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 3: Minimal constructor - uses constructor chaining
    /**
     * Minimal constructor with just title and creator.
     * Demonstrates constructor chaining and default values.
     */
    protected Media(String title, String creator) {
        this(title, creator, 0.0, 0.0);  // Chain to full constructor
    }
    
    // **WEEK 3: ABSTRACTION**
    // Abstract methods that derived classes MUST implement
    
    /**
     * Get the type of media (Book, Magazine, DVD, etc.)
     * Each derived class must provide its own implementation.
     */
    public abstract String getMediaType();
    
    /**
     * Get a formatted string with type-specific details.
     * Each derived class provides its own formatting.
     */
    public abstract String getTypeSpecificInfo();
    
    /**
     * Calculate late fees based on media type.
     * Different media types may have different fee structures.
     */
    public abstract double calculateLateFee(int daysLate);
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Protected methods - accessible to derived classes but not public
    
    protected void setBorrowPeriod(int days) {
        this.borrowPeriodDays = days;
    }
    
    protected int getId() {
        return id;
    }
    
    protected String getTitle() {
        return title;
    }
    
    protected String getCreator() {
        return creator;
    }
    
    protected double getPrice() {
        return price;
    }
    
    protected double getRating() {
        return rating;
    }
    
    // Public setters for properties that can be modified
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Template method pattern - provides common formatting logic
    @Override
    public String toString() {
        String status = borrowed ? "[BORROWED]" : "[AVAILABLE]";
        return String.format("%-3d %-25s %-20s $%-7.2f %-6.1f %-10s %s",
                id, title, creator, price, rating, getMediaType(), status);
    }
    
    // **WEEK 3: BORROWABLE INTERFACE IMPLEMENTATION**
    // Implementing interface methods with proper access control
    
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
}

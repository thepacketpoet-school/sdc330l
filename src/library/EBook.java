/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: EBook class extends Book with digital-specific features.
            
            **WEEK 3 UPDATES:**
            - Enhanced constructors with validation
            - Proper access specifiers
            - Implements abstract methods
   -------------------------------------------------------------- */

package library;

/**
 * An E-Book adds file-format information to Book.
 * 
 * **WEEK 3: Demonstrates inheritance hierarchy**
 * Media → Book → EBook (three-level inheritance)
 */
public class EBook extends Book {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private fields - encapsulation
    private String format;   // e.g., "PDF", "EPUB"
    private double fileSize; // MB
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 1: Full constructor with all EBook details
    /**
     * Full constructor for creating an e-book with all details.
     * Includes validation for format and file size.
     */
    public EBook(String title, String author, double price,
                 String genre, double rating, int pageCount,
                 String format, double fileSize) {
        super(title, author, price, genre, rating, pageCount);
        this.format = (format != null && !format.isEmpty()) ? format : "PDF";
        this.fileSize = (fileSize > 0) ? fileSize : 0.0;
        setBorrowPeriod(21);  // E-books: 3 weeks
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 2: Without page count - constructor chaining
    /**
     * Constructor without page count (common for e-books).
     * Demonstrates constructor chaining.
     */
    public EBook(String title, String author, double price,
                 String genre, double rating,
                 String format, double fileSize) {
        this(title, author, price, genre, rating, 0, format, fileSize);
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 3: Minimal constructor
    /**
     * Minimal constructor with defaults.
     */
    public EBook(String title, String author, String format) {
        this(title, author, 0.0, "General", 0.0, 0, format, 0.0);
    }

    @Override
    public String getMediaType() {
        return "E-Book";
    }
    
    @Override
    public String getTypeSpecificInfo() {
        return String.format("%s, Format: %s, Size: %.1f MB", 
                           super.getTypeSpecificInfo(), format, fileSize);
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // E-books: $0.25 per day (cheaper than physical)
        return daysLate * 0.25;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (%s, %.1f MB)", format, fileSize);
    }
    
    /**
     * **WEEK 2: Polymorphism Demonstration**
     * E-books have a different borrow period than physical books.
     */
    @Override
    public int getBorrowPeriodDays() {
        return 21;  // E-books: 3 weeks
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public getters for e-book specific fields
    public String getFormat() {
        return format;
    }
    
    public double getFileSize() {
        return fileSize;
    }
}

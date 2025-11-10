/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Book class now extends abstract Media class.
            
            **WEEK 3 UPDATES:**
            - Now extends abstract Media instead of being base class
            - Implements abstract methods from Media
            - Enhanced constructors with validation
            - Proper access specifiers
   -------------------------------------------------------------- */

package library;

/**
 * Book class representing physical and digital books.
 * 
 * **WEEK 3: Extends abstract Media class**
 * Inherits common media functionality and implements book-specific behavior.
 */
public class Book extends Media {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private fields - only accessible within this class
    private String genre;
    private int pageCount;
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 1: Full constructor with all book details
    /**
     * Full constructor for creating a book with all details.
     * Validates input and initializes all fields.
     */
    public Book(String title, String author, double price, String genre, 
                double rating, int pageCount) {
        super(title, author, price, rating);  // Call Media constructor
        this.genre = (genre != null && !genre.isEmpty()) ? genre : "General";
        this.pageCount = (pageCount > 0) ? pageCount : 0;
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 2: Without page count - constructor chaining
    /**
     * Constructor without page count.
     * Demonstrates constructor chaining.
     */
    public Book(String title, String author, double price, String genre, double rating) {
        this(title, author, price, genre, rating, 0);
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 3: Minimal constructor - constructor chaining
    /**
     * Minimal constructor with just title and author.
     * Uses default values for other fields.
     */
    public Book(String title, String author) {
        this(title, author, 0.0, "General", 0.0, 0);
    }
    
    // **WEEK 3: ABSTRACTION**
    // Implementation of abstract methods from Media
    
    @Override
    public String getMediaType() {
        return "Book";
    }
    
    @Override
    public String getTypeSpecificInfo() {
        return String.format("Genre: %s, Pages: %d", genre, pageCount);
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Books: $0.50 per day late
        return daysLate * 0.50;
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public getters for book-specific fields
    public String getGenre() {
        return genre;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    // Override toString to include book-specific info
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s]", genre);
    }
}

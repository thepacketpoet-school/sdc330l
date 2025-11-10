/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Audiobook class extends Book with audio-specific features.
            
            **WEEK 3 UPDATES:**
            - Enhanced constructors with validation
            - Proper access specifiers
            - Implements abstract methods
   -------------------------------------------------------------- */

package library;

/**
 * An Audiobook adds duration and narrator information to Book.
 * 
 * **WEEK 3: Demonstrates inheritance hierarchy**
 * Media → Book → Audiobook (three-level inheritance)
 */
public class Audiobook extends Book {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private fields for encapsulation
    private int durationMinutes;
    private String narrator;
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 1: Full constructor with all audiobook details
    /**
     * Full constructor for creating an audiobook with all details.
     * Validates duration and narrator information.
     */
    public Audiobook(String title, String author, double price,
                     String genre, double rating, int pageCount,
                     int durationMinutes, String narrator) {
        super(title, author, price, genre, rating, pageCount);
        this.durationMinutes = (durationMinutes > 0) ? durationMinutes : 0;
        this.narrator = (narrator != null && !narrator.isEmpty()) ? narrator : "Unknown";
        setBorrowPeriod(30);  // Audiobooks: 30 days
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 2: Without page count - constructor chaining
    /**
     * Constructor without page count (not relevant for audiobooks).
     * Demonstrates constructor chaining.
     */
    public Audiobook(String title, String author, double price,
                     String genre, double rating,
                     int durationMinutes, String narrator) {
        this(title, author, price, genre, rating, 0, durationMinutes, narrator);
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 3: Minimal constructor
    /**
     * Minimal constructor with just essentials.
     */
    public Audiobook(String title, String author, int durationMinutes) {
        this(title, author, 0.0, "General", 0.0, 0, durationMinutes, "Unknown");
    }

    @Override
    public String getMediaType() {
        return "Audiobook";
    }
    
    @Override
    public String getTypeSpecificInfo() {
        return String.format("%s, Duration: %d min, Narrator: %s", 
                           super.getTypeSpecificInfo(), durationMinutes, narrator);
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Audiobooks: $0.75 per day (more expensive due to licensing)
        return daysLate * 0.75;
    }

    @Override
    public String toString() {
        return super.toString() + 
               String.format(" (%d min, narrated by %s)", durationMinutes, narrator);
    }
    
    /**
     * **WEEK 2: Polymorphism Demonstration**
     * Audiobooks have the longest borrow period due to their length.
     */
    @Override
    public int getBorrowPeriodDays() {
        return 30;  // Audiobooks: 30 days
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public getters for audiobook-specific fields
    public int getDurationMinutes() {
        return durationMinutes;
    }
    
    public String getNarrator() {
        return narrator;
    }
}

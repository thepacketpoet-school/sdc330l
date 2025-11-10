/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Concrete subclass of Book representing an audiobook.
            
            Demonstrates:
            - **Inheritance** (extends Book)
            - **Polymorphism** (overrides getBorrowPeriodDays)
   -------------------------------------------------------------- */

package library;

/**
 * An Audiobook adds duration and narrator information.
 * 
 * **WEEK 2: Demonstrates polymorphism by extending borrow period**
 * Audiobooks can be borrowed for longer (30 days vs 14 days)
 */
public class Audiobook extends Book {
    private final int durationMinutes;
    private final String narrator;

    public Audiobook(String title, String author, double price,
                     String genre, double rating,
                     int durationMinutes, String narrator) {
        super(title, author, price, genre, rating);
        this.durationMinutes = durationMinutes;
        this.narrator = narrator;
        // Audiobooks have longest borrow period
        setBorrowPeriod(30);  // 30 days
    }

    @Override
    public String getType() {
        return "Audiobook";
    }

    @Override
    public String toString() {
        // Append narrator & duration to the base representation
        return super.toString() + String.format(" (%d min, narrated by %s)",
                durationMinutes, narrator);
    }
    
    /**
     * **WEEK 2: Polymorphism Demonstration**
     * Audiobooks have the longest borrow period due to their length.
     * This method is inherited from Borrowable interface but 
     * returns a different value based on the book type.
     */
    @Override
    public int getBorrowPeriodDays() {
        return 30;  // Audiobooks: 30 days
    }
}

/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Concrete subclass of Book representing an E-book.
            
            Demonstrates:
            - **Inheritance** (extends Book)
            - **Polymorphism** (overrides getBorrowPeriodDays)
   -------------------------------------------------------------- */

package library;

/**
 * An E-Book adds file-format information.
 * 
 * **WEEK 2: Demonstrates polymorphism by extending borrow period**
 * E-books can be borrowed for longer (21 days vs 14 days)
 */
public class EBook extends Book {
    private final String format;   // e.g., "PDF", "EPUB"
    private final double fileSize; // MB

    public EBook(String title, String author, double price,
                 String genre, double rating,
                 String format, double fileSize) {
        super(title, author, price, genre, rating);
        this.format = format;
        this.fileSize = fileSize;
        // E-books have longer borrow period
        setBorrowPeriod(21);  // 3 weeks
    }

    @Override
    public String getType() {
        return "E-Book";
    }

    @Override
    public String toString() {
        // Append format info to the base representation
        return super.toString() + String.format(" (%s, %.1f MB)", format, fileSize);
    }
    
    /**
     * **WEEK 2: Polymorphism Demonstration**
     * E-books have a different borrow period than physical books.
     * This method is inherited from Borrowable interface but 
     * returns a different value based on the book type.
     */
    @Override
    public int getBorrowPeriodDays() {
        return 21;  // E-books: 3 weeks
    }
}

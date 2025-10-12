/* --------------------------------------------------------------
   Assignment: Project Part 1
   Author: Haley Archer
   Date: 12 Oct 2025
   Purpose: Concrete subclass of Book representing an E-book.
            Demonstrates **inheritance** (extends Book).
   -------------------------------------------------------------- */

package library;

/**
 * An E‑Book adds file‑format information.
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
    }

    @Override
    public String getType() {
        return "E‑Book";
    }

    @Override
    public String toString() {
        // Append format info to the base representation
        return super.toString() + String.format(" (%s, %.1f MB)", format, fileSize);
    }
}
/* --------------------------------------------------------------
   Assignment: Project Part 1
   Author: Haley Archer
   Date: 12 Oct 2025
   Purpose: Concrete subclass of Book representing an audiobook.
            Demonstrates **inheritance** (extends Book).
   -------------------------------------------------------------- */

package library;

/**
 * An Audiobook adds duration and narrator information.
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
    }

    @Override
    public String getType() {
        return "Audiobook";
    }

    @Override
    public String toString() {
        // Append narrator & duration to the base representation
        return super.toString() + String.format(" (%d min, narrated by %s)",
                durationMinutes, narrator);
    }
}
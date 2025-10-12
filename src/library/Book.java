/* --------------------------------------------------------------
   Assignment: Project Part 1
   Author: Haley Archer
   Date: 12 Oct 2025
   Purpose: Base class for all book types.
            Demonstrates **inheritance** – derived classes extend this.
   -------------------------------------------------------------- */

package library;

/**
 * Base class that holds the common attributes of any book.
 */
public class Book {
    // ---------- static ID generator ----------
    protected static int NEXT_ID = 1;

    // ---------- instance fields ----------
    protected final int id;          // unique identifier
    protected String title;
    protected String author;
    protected double price;
    protected String genre;         // e.g., "Science Fiction"
    protected double rating;        // 0.0 – 5.0 stars

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
    }

    /** Returns a short description – overridden by children to add type. */
    public String getType() {
        return "Generic Book";
    }

    /** Human‑readable representation used by the UI. */
    @Override
    public String toString() {
        return String.format("%-3d %-25s %-20s %-12s $%-7.2f %-6.1f %-10s",
                id, title, author, genre, price, rating, getType());
    }

    // -----------------------------------------------------------------
    // Getters / setters (only the ones we need for the demo)
    // -----------------------------------------------------------------
    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
}
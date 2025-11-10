/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Simple representation of a user. Holds a **Shelf** object,
            which demonstrates **composition** (User "has a" Shelf).
   -------------------------------------------------------------- */

package library;

/**
 * Represents a user of the system.
 */
public class User {
    private final String name;
    private double wallet;          // money available for purchases
    private final Shelf shelf;      // composition – a user owns a shelf

    public User(String name, double startingBalance) {
        this.name = name;
        this.wallet = startingBalance;
        this.shelf = new Shelf(this);   // composition relationship
    }

    // -----------------------------------------------------------------
    // Wallet helpers
    // -----------------------------------------------------------------
    public double getWallet() {
        return wallet;
    }

    public void debit(double amount) {
        wallet -= amount;
    }

    // -----------------------------------------------------------------
    // Shelf accessor
    // -----------------------------------------------------------------
    public Shelf getShelf() {
        return shelf;
    }

    // -----------------------------------------------------------------
    // Misc helpers used by Library
    // -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void addBookToShelf(Book b) {
        shelf.addBook(b);
    }

    public Book removeBookFromShelf(int bookId) {
        return shelf.removeBook(bookId);
    }

    public Book findBookOnShelf(int bookId) {
        return shelf.findBook(bookId);
    }
}

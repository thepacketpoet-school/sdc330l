/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Shelf class with proper access control.
            
            **WEEK 3 UPDATES:**
            - Proper access specifiers
            - Private helper methods
   -------------------------------------------------------------- */

package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of books owned by a user.
 * Demonstrates composition (Shelf "has many" Books).
 */
public class Shelf {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private fields for encapsulation
    private final User owner;
    private final List<Book> ownedBooks;
    
    // **WEEK 3: CONSTRUCTORS**
    /**
     * Constructor creates a shelf for a specific user.
     * Validates that owner is not null.
     */
    public Shelf(User owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Shelf must have an owner");
        }
        this.owner = owner;
        this.ownedBooks = new ArrayList<>();
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Package-private methods (used by User and Library classes)
    
    /**
     * Add a book to the shelf.
     * Package-private - only User class should add books.
     */
    void addBook(Book b) {
        if (b != null && !ownedBooks.contains(b)) {
            ownedBooks.add(b);
        }
    }
    
    /**
     * Remove a book by ID.
     * Package-private - only User class should remove books.
     */
    Book removeBook(int bookId) {
        for (int i = 0; i < ownedBooks.size(); i++) {
            if (ownedBooks.get(i).getId() == bookId) {
                return ownedBooks.remove(i);
            }
        }
        return null;
    }
    
    /**
     * Find a book on the shelf without removing it.
     * Package-private.
     */
    Book findBook(int bookId) {
        for (Book book : ownedBooks) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public methods for display
    
    /**
     * List all owned books in a tabular format.
     * Public because users need to view their library.
     */
    public void listOwnedBooks() {
        System.out.println("\n--- Your Library ---");
        if (ownedBooks.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        System.out.println(
                "ID  Title                     Author               Genre        Price   Rating  Type       Status");
        System.out.println(
                "----------------------------------------------------------------------------------------------------");
        for (Book b : ownedBooks) {
            System.out.println(b);
        }
        System.out.printf("\nTotal books: %d\n", ownedBooks.size());
    }
    
    /**
     * Get the count of owned books.
     * Public accessor.
     */
    public int getBookCount() {
        return ownedBooks.size();
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private helper method
    
    /**
     * Private helper to check if shelf is full.
     * Not exposed publicly - internal logic only.
     */
    private boolean isFull() {
        return ownedBooks.size() >= 100;  // Max 100 books per shelf
    }
}

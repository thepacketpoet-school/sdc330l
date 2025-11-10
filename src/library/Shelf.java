/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Represents a collection of books owned by a single user.
            Demonstrates **composition** – Shelf "has many" Book objects.
   -------------------------------------------------------------- */

package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple container for a user's owned books.
 */
public class Shelf {
    private final User owner;               // back-reference (optional)
    private final List<Book> ownedBooks = new ArrayList<>();

    public Shelf(User owner) {
        this.owner = owner;
    }

    /** Add a book to the shelf. */
    public void addBook(Book b) {
        ownedBooks.add(b);
    }

    /** Remove a book by ID; returns the removed book or null. */
    public Book removeBook(int bookId) {
        for (int i = 0; i < ownedBooks.size(); i++) {
            if (ownedBooks.get(i).getId() == bookId) {
                return ownedBooks.remove(i);
            }
        }
        return null;
    }

    /** Find a book on the shelf without removing it. */
    public Book findBook(int bookId) {
        return ownedBooks.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst()
                .orElse(null);
    }

    /** List all owned books in a tabular format. */
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
    }
}

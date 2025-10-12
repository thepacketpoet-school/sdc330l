/* --------------------------------------------------------------
   Assignment: Project Part 1
   Author: Haley Archer
   Date: 12 Oct 2025
   Purpose: Central manager that holds the catalogue of all books.
            Demonstrates **composition** – Library “has many” Book objects.
   -------------------------------------------------------------- */

package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the master list of books and implements purchase / share logic.
 */
public class Library {
    private final List<Book> catalogue = new ArrayList<>(); // composition

    /** Add a book to the catalogue (used during seeding). */
    public void addBook(Book b) {
        catalogue.add(b);
    }

    /** Print the whole catalogue in a tabular format. */
    public void listCatalog() {
        System.out.println("\n--- Book Catalogue ---");
        System.out.println(
                "ID  Title                     Author               Genre        Price   Rating  Type");
        System.out.println(
                "--------------------------------------------------------------------------------");
        for (Book b : catalogue) {
            System.out.println(b);
        }
    }

    /** Purchase a book for a given user. */
    public void purchase(User user, int bookId) {
        Book target = findInCatalogue(bookId);
        if (target == null) {
            System.out.println("Book not found in catalogue.");
            return;
        }
        if (user.getWallet() < target.getPrice()) {
            System.out.println("Insufficient funds – you have $" + user.getWallet());
            return;
        }
        // Deduct money, give the book to the user
        user.debit(target.getPrice());
        user.addBookToShelf(target);
        System.out.println("Purchase successful! \"" + target.getTitle() + "\" added to your library.");
    }

    /** Share a finished book – simple simulation. */
    public void share(User user, int bookId) {
        Book owned = user.findBookOnShelf(bookId);
        if (owned == null) {
            System.out.println("You don't own a book with that ID.");
            return;
        }
        // In a real system we would transfer ownership; here we just note it.
        System.out.println("You have shared \"" + owned.getTitle() + "\" with the community. Thank you!");
    }

    /** Helper – locate a book in the catalogue by its ID. */
    private Book findInCatalogue(int id) {
        return catalogue.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Central manager that holds the catalogue of all books.
            
            Demonstrates:
            - **Composition** (Library "has many" Book objects)
            - **Polymorphism** (works with Borrowable interface)
   -------------------------------------------------------------- */

package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the master list of books and implements purchase/share/borrow logic.
 * 
 * **WEEK 2: Enhanced with borrow/return functionality using Borrowable interface**
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
                "ID  Title                     Author               Genre        Price   Rating  Type       Status");
        System.out.println(
                "----------------------------------------------------------------------------------------------------");
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

    /**
     * **WEEK 2: Polymorphism Demonstration**
     * Borrow a book from the library using the Borrowable interface.
     * Different book types have different borrow periods.
     */
    public void borrowBook(User user, int bookId) {
        Book target = findInCatalogue(bookId);
        if (target == null) {
            System.out.println("Book not found in catalogue.");
            return;
        }
        
        // **POLYMORPHISM**: Using Borrowable interface to work with any book type
        Borrowable borrowable = target;
        
        if (borrowable.isBorrowed()) {
            System.out.println("Sorry, \"" + target.getTitle() + "\" is already borrowed by " 
                             + borrowable.getBorrower());
            return;
        }
        
        if (borrowable.borrow(user.getName())) {
            System.out.println("Success! You borrowed \"" + target.getTitle() + "\"");
            System.out.println("Book type: " + target.getType());
            System.out.println("Borrow period: " + borrowable.getBorrowPeriodDays() + " days");
            System.out.println("(Note: Different book types have different borrow periods!)");
        } else {
            System.out.println("Unable to borrow this book.");
        }
    }
    
    /**
     * **WEEK 2: Polymorphism Demonstration**
     * Return a borrowed book using the Borrowable interface.
     */
    public void returnBook(User user, int bookId) {
        Book target = findInCatalogue(bookId);
        if (target == null) {
            System.out.println("Book not found in catalogue.");
            return;
        }
        
        // **POLYMORPHISM**: Using Borrowable interface
        Borrowable borrowable = target;
        
        if (!borrowable.isBorrowed()) {
            System.out.println("This book is not currently borrowed.");
            return;
        }
        
        if (!borrowable.getBorrower().equals(user.getName())) {
            System.out.println("You cannot return a book you didn't borrow!");
            return;
        }
        
        if (borrowable.returnItem()) {
            System.out.println("Thank you for returning \"" + target.getTitle() + "\"!");
        } else {
            System.out.println("Unable to return this book.");
        }
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

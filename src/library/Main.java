/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Entry point. Shows the week-2 indicator, welcome
            message, and drives the simple console UI.
            
            **WEEK 2: Demonstrates polymorphism with Borrowable interface**
   -------------------------------------------------------------- */

package library;

import java.util.Scanner;

/**
 * Main class – runs the console UI loop.
 *
 * **WEEK 2 Enhancements:**
 *   • Demonstrates interface usage (Borrowable)
 *   • Demonstrates polymorphism (different borrow periods per book type)
 *   • Added borrow/return functionality
 */
public class Main {

    // -----------------------------------------------------------------
    //  Fields
    // -----------------------------------------------------------------
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Library LIBRARY = new Library();
    private static final User CURRENT_USER = new User("Alice", 50.00);

    // -----------------------------------------------------------------
    //  Main entry point
    // -----------------------------------------------------------------
    public static void main(String[] args) {
        // 1️⃣ Informative indicator (week, title, name)
        MessageBox.printHeader();

        // 2️⃣ Seed demo data (realistic objects)
        seedDemoData();

        // 3️⃣ UI loop
        boolean running = true;
        while (running) {
            MessageBox.printMenu();
            String input = SCANNER.nextLine().trim().toUpperCase();

            switch (input) {
                case "B":   // Browse catalogue
                    LIBRARY.listCatalog();
                    break;
                case "P":   // Purchase a book
                    handlePurchase();
                    break;
                case "L":   // Loan (borrow) a book - NEW for Week 2
                    handleBorrow();
                    break;
                case "R":   // Return a book - NEW for Week 2
                    handleReturn();
                    break;
                case "S":   // Share a finished book
                    handleShare();
                    break;
                case "M":   // My library (personal shelf)
                    CURRENT_USER.getShelf().listOwnedBooks();
                    break;
                case "Q":   // Quit
                    running = false;
                    break;
                default:
                    System.out.println("Unrecognised command – please try again.");
            }
        }

        // 4️⃣ Exit message
        MessageBox.goodbye();
    }

    // -----------------------------------------------------------------
    //  Helper methods for UI actions
    // -----------------------------------------------------------------
    private static void handlePurchase() {
        System.out.print("Enter the ID of the book you wish to purchase: ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        LIBRARY.purchase(CURRENT_USER, id);
    }

    /**
     * **WEEK 2: Demonstrates Polymorphism**
     * Borrow a book - different book types have different borrow periods.
     */
    private static void handleBorrow() {
        System.out.print("Enter the ID of the book you wish to borrow: ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        LIBRARY.borrowBook(CURRENT_USER, id);
    }

    /**
     * **WEEK 2: Demonstrates Polymorphism**
     * Return a borrowed book using the Borrowable interface.
     */
    private static void handleReturn() {
        System.out.print("Enter the ID of the book you wish to return: ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        LIBRARY.returnBook(CURRENT_USER, id);
    }

    private static void handleShare() {
        System.out.print("Enter the ID of a book you have finished (to share): ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        LIBRARY.share(CURRENT_USER, id);
    }

    /** 
     * Populate the catalogue with a few realistic books.
     * 
     * **WEEK 2: Demonstrates polymorphism**
     * Each book type implements Borrowable interface differently.
     */
    private static void seedDemoData() {
        // Base Book objects (via derived classes)
        // Each has different borrow periods demonstrating polymorphism
        Book b1 = new EBook(
                "The Time Machine", "H. G. Wells", 4.99,
                "Science Fiction", 4.2,
                "EPUB", 2.1);
        Book b2 = new Audiobook(
                "Pride and Prejudice", "Jane Austen", 3.49,
                "Classic", 4.5,
                720, "Emma Thompson");
        Book b3 = new EBook(
                "Dune", "Frank Herbert", 5.99,
                "Science Fiction", 4.7,
                "PDF", 3.4);
        Book b4 = new Audiobook(
                "1984", "George Orwell", 4.49,
                "Dystopian", 4.6,
                600, "Simon Prebble");

        LIBRARY.addBook(b1);
        LIBRARY.addBook(b2);
        LIBRARY.addBook(b3);
        LIBRARY.addBook(b4);
        
        // **POLYMORPHISM DEMONSTRATION**
        System.out.println("\n=== Demonstrating Polymorphism ===");
        System.out.println("Books implement the Borrowable interface with different borrow periods:\n");
        
        // Using Borrowable reference type - demonstrates polymorphism
        Borrowable[] borrowables = {b1, b2, b3, b4};
        for (Borrowable item : borrowables) {
            // Polymorphic call - same method, different behavior based on book type
            Book book = (Book) item;
            System.out.printf("%-30s | Type: %-10s | Borrow Period: %d days\n", 
                             book.getTitle(), 
                             book.getType(),
                             item.getBorrowPeriodDays());
        }
        System.out.println("\nNotice: Different book types return different borrow periods!");
        System.out.println("=================================\n");
    }
}

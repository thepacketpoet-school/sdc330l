/* --------------------------------------------------------------
   Assignment: Project Part 1
   Author: Haley Archer
   Date: 12 Oct 2025
   Purpose: Entry point. Shows the week‑1 indicator, welcome
            message, and drives the simple console UI.
   -------------------------------------------------------------- */

package library;

import java.util.Scanner;

/**
 * Main class – runs the console UI loop.
 *
 * Demonstrates:
 *   • Use of the MessageBox helper (modular UI component)
 *   • Interaction with Library, User, and the inheritance hierarchy
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

    private static void handleShare() {
        System.out.print("Enter the ID of a book you have finished (to share): ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        LIBRARY.share(CURRENT_USER, id);
    }

    /** Populate the catalogue with a few realistic books. */
    private static void seedDemoData() {
        // Base Book objects (via derived classes)
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

        LIBRARY.addBook(b1);
        LIBRARY.addBook(b2);
        LIBRARY.addBook(b3);
    }
}
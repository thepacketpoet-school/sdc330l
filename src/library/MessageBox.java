/* --------------------------------------------------------------
   Assignment: Project Part 2
   Author: Haley Archer
   Date: 19 Oct 2025
   Purpose: Small utility class that centralizes all UI text.
            Keeps the Main class tidy and demonstrates modularization.
   -------------------------------------------------------------- */

package library;

/**
 * Static helper that prints the banner, menu, and exit line.
 */
public class MessageBox {

    /** Prints the week-2 indicator, title, and quote. */
    public static void printHeader() {
        System.out.println("=== Online Book-Sharing Library – Week 2 Project ===");
        System.out.println("Student: Haley Archer");
        System.out.println("-------------------------------------------------");
        System.out.println(
                "\"A good book is a friend you can carry anywhere; a best friend is one who shares good books.\"");
        System.out.println("-------------------------------------------------");
        System.out.println("\nWelcome! This console application lets you:");
        System.out.println(" • Browse the catalogue");
        System.out.println(" • Purchase books");
        System.out.println(" • Borrow books (NEW!)");
        System.out.println(" • Return borrowed books (NEW!)");
        System.out.println(" • Share books you have finished");
        System.out.println(" • View your personal library");
        System.out.println("\n** Week 2 Features: Interface implementation & Polymorphism **");
        System.out.println("   - All books implement the Borrowable interface");
        System.out.println("   - Different book types have different borrow periods!");
        System.out.println("     * Regular books: 14 days");
        System.out.println("     * E-books: 21 days");
        System.out.println("     * Audiobooks: 30 days");
        System.out.println();
    }

    /** Shows the main command menu. */
    public static void printMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("  (B)rowse catalogue");
        System.out.println("  (P)urchase a book");
        System.out.println("  (L)oan a book (borrow)");
        System.out.println("  (R)eturn a book");
        System.out.println("  (S)hare a finished book");
        System.out.println("  (M)y library");
        System.out.println("  (Q)uit");
        System.out.print("Enter command: ");
    }

    /** Friendly goodbye line shown when the user quits. */
    public static void goodbye() {
        System.out.println("\nThank you for using the Book-Sharing Library. Goodbye!");
    }
}

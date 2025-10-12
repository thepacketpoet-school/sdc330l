/* --------------------------------------------------------------
   Assignment: Project Part 1
   Author: Haley Archer
   Date: 12 Oct 2025
   Purpose: Small utility class that centralises all UI text.
            Keeps the Main class tidy and demonstrates modularisation.
   -------------------------------------------------------------- */

package library;

/**
 * Static helper that prints the banner, menu, and exit line.
 */
public class MessageBox {

    /** Prints the week‑1 indicator, title, and quote. */
    public static void printHeader() {
        System.out.println("=== Online Book‑Sharing Library – Week 1 Project ===");
        System.out.println("Student: Haley Archer");
        System.out.println("-------------------------------------------------");
        System.out.println(
                "\"A good book is a friend you can carry anywhere; a best friend is one who shares good books.\"");
        System.out.println("-------------------------------------------------");
        System.out.println("\nWelcome! This console application lets you:");
        System.out.println(" • Browse the catalogue");
        System.out.println(" • Purchase books");
        System.out.println(" • Share books you have finished");
        System.out.println(" • View your personal library");
        System.out.println();
    }

    /** Shows the main command menu. */
    public static void printMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("  (B)rowse catalogue");
        System.out.println("  (P)urchase a book");
        System.out.println("  (S)hare a finished book");
        System.out.println("  (M)y library");
        System.out.println("  (Q)uit");
        System.out.print("Enter command: ");
    }

    /** Friendly goodbye line shown when the user quits. */
    public static void goodbye() {
        System.out.println("\nThank you for using the Book‑Sharing Library. Goodbye!");
    }
}
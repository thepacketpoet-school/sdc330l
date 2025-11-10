/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 02 Nov 2025
   Purpose: UI messaging utility with Week 3 updates.
   -------------------------------------------------------------- */

package library;

/**
 * Static helper for all UI text.
 */
public class MessageBox {

    /** Prints the week-4 indicator and welcome. */
    public static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   Online Book-Sharing Library – Week 4 Project              ║");
        System.out.println("║   Student: Haley Archer                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("\n\"A good book is a friend you can carry anywhere;");
        System.out.println(" a best friend is one who shares good books.\"\n");
        
        System.out.println("Welcome to your community library system!");
        System.out.println("Now with persistent database storage (SQLite).\n");
    }

    /** Shows the main command menu with database operations. */
    public static void printMenu() {
        System.out.println("\n╔═══ MENU (WEEK 4: DATABASE CRUD) ═══╗");
        System.out.println("  (B)rowse catalogue");
        System.out.println("  (A)dd new media [CREATE]");
        System.out.println("  (E)dit media [UPDATE]");
        System.out.println("  (D)elete media [DELETE]");
        System.out.println("  (P)urchase a book");
        System.out.println("  (L)oan a book (borrow)");
        System.out.println("  (R)eturn a book");
        System.out.println("  (H)istory [READ]");
        System.out.println("  (S)tatistics [READ]");
        System.out.println("  (M)y library");
        System.out.println("  (Q)uit");
        System.out.print("\nEnter command: ");
    }

    /** Goodbye message. */
    public static void goodbye() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  Thank you for using our library!     ║");
        System.out.println("║  Come back soon!                      ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}

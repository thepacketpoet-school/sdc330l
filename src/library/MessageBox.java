/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: UI messaging utility with Week 3 updates.
   -------------------------------------------------------------- */

package library;

/**
 * Static helper for all UI text.
 */
public class MessageBox {

    /** Prints the week-3 indicator and welcome. */
    public static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   Online Book-Sharing Library – Week 3 Project              ║");
        System.out.println("║   Student: Haley Archer                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("\n\"A good book is a friend you can carry anywhere;");
        System.out.println(" a best friend is one who shares good books.\"\n");
        
        System.out.println("═══ WEEK 3 FEATURES ═══");
        System.out.println("✓ ABSTRACTION: Abstract Media class with abstract methods");
        System.out.println("✓ CONSTRUCTORS: Multiple constructors with chaining");
        System.out.println("✓ ACCESS SPECIFIERS: Proper public/protected/private usage");
        System.out.println("\nNew in Week 3:");
        System.out.println("  • Abstract Media base class for all media types");
        System.out.println("  • calculateLateFee() - different fees per media type");
        System.out.println("  • Enhanced input validation in constructors");
        System.out.println("  • Improved encapsulation with access control\n");
    }

    /** Shows the main command menu. */
    public static void printMenu() {
        System.out.println("\n╔═══ MENU ═══╗");
        System.out.println("  (B)rowse catalogue");
        System.out.println("  (P)urchase a book");
        System.out.println("  (L)oan a book (borrow)");
        System.out.println("  (R)eturn a book");
        System.out.println("  (F)ee calculation (NEW!)");
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

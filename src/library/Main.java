/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Main entry point demonstrating Week 3 requirements.
            
            **WEEK 3 DEMONSTRATIONS:**
            - Abstract Media class and abstract methods
            - Multiple constructors with chaining
            - Proper access specifiers (public/protected/private)
   -------------------------------------------------------------- */

package library;

import java.util.Scanner;

/**
 * Main class – runs the console UI loop.
 *
 * **WEEK 3**: Demonstrates abstraction, constructors, and access specifiers
 */
public class Main {

    // **WEEK 3: ACCESS SPECIFIERS**
    // Private static fields - only accessible within Main class
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Library library;
    private static User currentUser;

    public static void main(String[] args) {
        // Display Week 3 header
        MessageBox.printHeader();

        // Initialize library and user
        initializeSystem();

        // Demonstrate Week 3 features
        demonstrateWeek3Features();

        // Run UI loop
        runApplicationLoop();

        // Exit
        MessageBox.goodbye();
        SCANNER.close();
    }

    /**
     * **WEEK 3: CONSTRUCTORS DEMONSTRATION**
     * Shows different constructor usage.
     */
    private static void initializeSystem() {
        System.out.println("═══ INITIALIZING SYSTEM ═══\n");
        
        // **CONSTRUCTORS**: Library with custom name
        library = new Library("Community Book-Sharing Library");
        System.out.println("✓ Library created: " + library.getLibraryName());
        
        // **CONSTRUCTORS**: User with starting balance
        currentUser = new User("Alice", 100.00);
        System.out.println("✓ User created: " + currentUser);
        
        // Seed data demonstrating various constructors
        seedDemoData();
        
        System.out.println("\n═══════════════════════════════════\n");
    }

    /**
     * **WEEK 3: ABSTRACTION DEMONSTRATION**
     * Shows abstract Media class and polymorphic method calls.
     */
    private static void demonstrateWeek3Features() {
        System.out.println("═══ WEEK 3 FEATURE DEMONSTRATION ═══\n");
        
        System.out.println("** ABSTRACTION DEMONSTRATION **");
        System.out.println("All media types extend abstract Media class");
        System.out.println("Each implements abstract methods differently:\n");
        
        // Create instances using different constructors
        Book book = new Book("1984", "George Orwell", 12.99, "Dystopian", 4.8, 328);
        EBook ebook = new EBook("Neuromancer", "William Gibson", 9.99, 
                               "Cyberpunk", 4.6, "EPUB", 2.3);
        Audiobook audiobook = new Audiobook("Dune", "Frank Herbert", 14.99,
                                           "Sci-Fi", 4.9, 600, 1260, "Scott Brick");
        
        Media[] mediaItems = {book, ebook, audiobook};
        
        // **ABSTRACTION**: Polymorphic calls to abstract methods
        for (Media media : mediaItems) {
            System.out.printf("Title: %-20s Type: %-10s\n", 
                            media.getTitle(), media.getMediaType());
            System.out.printf("  Info: %s\n", media.getTypeSpecificInfo());
            System.out.printf("  Borrow Period: %d days\n", media.getBorrowPeriodDays());
            // Calling abstract method - each type calculates differently
            System.out.printf("  Late Fee (7 days): $%.2f\n", 
                            media.calculateLateFee(7));
            System.out.println();
        }
        
        System.out.println("Notice: Same method calls, different behavior!");
        System.out.println("This is polymorphism with abstract classes.\n");
        
        System.out.println("** CONSTRUCTORS DEMONSTRATION **");
        System.out.println("Multiple constructors with validation:\n");
        
        // Full constructor
        Book fullBook = new Book("Title", "Author", 9.99, "Fiction", 4.5, 300);
        System.out.println("✓ Full constructor: " + fullBook.getTitle());
        
        // Partial constructor (constructor chaining)
        Book partialBook = new Book("Short Title", "Author", 5.99, "Fiction", 4.0);
        System.out.println("✓ Partial constructor (chains to full): " + partialBook.getTitle());
        
        // Minimal constructor
        Book minimalBook = new Book("Minimal", "Author");
        System.out.println("✓ Minimal constructor (chains with defaults): " + minimalBook.getTitle());
        
        System.out.println("\n** ACCESS SPECIFIERS DEMONSTRATION **");
        System.out.println("Proper encapsulation implemented:");
        System.out.println("  • Private fields (wallet, borrower, etc.)");
        System.out.println("  • Protected methods (for inheritance)");
        System.out.println("  • Public methods (for external access)");
        System.out.println("  • Package-private (for related classes only)\n");
        
        System.out.println("═══════════════════════════════════\n");
    }

    /**
     * Seed the catalogue with demo data.
     * **WEEK 3**: Uses different constructors.
     */
    private static void seedDemoData() {
        // Using various constructors to demonstrate flexibility
        
        // Full constructor
        library.addMedia(new EBook(
                "The Time Machine", "H. G. Wells", 4.99,
                "Science Fiction", 4.2, 118,
                "EPUB", 2.1));
        
        // Partial constructor
        library.addMedia(new Audiobook(
                "Pride and Prejudice", "Jane Austen", 3.49,
                "Classic", 4.5,
                720, "Rosamund Pike"));
        
        library.addMedia(new EBook(
                "Dune", "Frank Herbert", 5.99,
                "Science Fiction", 4.7, 412,
                "PDF", 3.4));
        
        library.addMedia(new Audiobook(
                "1984", "George Orwell", 4.49,
                "Dystopian", 4.6,
                600, "Simon Prebble"));
        
        System.out.println("✓ Catalogue populated with 4 books");
    }

    /**
     * Main application loop.
     */
    private static void runApplicationLoop() {
        boolean running = true;
        while (running) {
            MessageBox.printMenu();
            String input = SCANNER.nextLine().trim().toUpperCase();

            switch (input) {
                case "B":
                    library.listCatalog();
                    break;
                case "P":
                    handlePurchase();
                    break;
                case "L":
                    handleBorrow();
                    break;
                case "R":
                    handleReturn();
                    break;
                case "F":  // NEW for Week 3
                    handleLateFee();
                    break;
                case "M":
                    currentUser.getShelf().listOwnedBooks();
                    break;
                case "Q":
                    running = false;
                    break;
                default:
                    System.out.println("Unrecognized command – please try again.");
            }
        }
    }

    // **WEEK 3: ACCESS SPECIFIERS**
    // Private helper methods - only used within Main class
    
    private static void handlePurchase() {
        System.out.print("Enter the ID of the book you wish to purchase: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            library.purchase(currentUser, id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    private static void handleBorrow() {
        System.out.print("Enter the ID of the book you wish to borrow: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            library.borrowMedia(currentUser, id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    private static void handleReturn() {
        System.out.print("Enter the ID of the book you wish to return: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            library.returnMedia(currentUser, id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    /**
     * **WEEK 3: NEW FEATURE**
     * Calculate late fees - demonstrates abstract method usage.
     */
    private static void handleLateFee() {
        System.out.print("Enter the ID of the media item: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            System.out.print("Enter number of days late: ");
            int days = Integer.parseInt(SCANNER.nextLine().trim());
            
            // **ABSTRACTION**: calculateLateFee is an abstract method
            // Each media type implements it differently
            library.calculateLateFeeForItem(id, days);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers only.");
        }
    }
}

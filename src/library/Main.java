/* --------------------------------------------------------------
   Assignment: Project Part 4
   Author: Haley Archer
   Date: 02 Nov 2025
   Purpose: Main entry point with database integration.
   -------------------------------------------------------------- */

package library;

import java.util.Scanner;
import java.util.List;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static DatabaseManager dbManager;
    private static Library library;
    private static User currentUser;

    public static void main(String[] args) {
        MessageBox.printHeader();
        initializeSystem();
        runApplicationLoop();
        dbManager.close();
        MessageBox.goodbye();
        SCANNER.close();
    }

    private static void initializeSystem() {
        System.out.println("═══ INITIALIZING SYSTEM (WEEK 4: DATABASE) ═══\n");
        
        dbManager = new DatabaseManager();

        List<Media> existingMedia = dbManager.getAllMedia();
        if (existingMedia.isEmpty()) {
            System.out.println("Database is empty. Seeding with initial data...");
            seedDatabase();
            // DON'T call getAllMedia again - we'll load into library directly
        }
        
        // Load whatever is in database (fresh load)
        Media.setNextId(1);  // Reset counter
        existingMedia = dbManager.getAllMedia();  // Load with database constructors

        library = new Library("Community Book-Sharing Library");
        for (Media media : existingMedia) {
            library.addMedia(media);
        }
        
        currentUser = dbManager.getUserByName("Alice");
        if (currentUser == null) {
            dbManager.addUser("Alice", 100.00);
            currentUser = new User("Alice", 100.00);
            System.out.println("✓ Created new user: Alice");
        } else {
            System.out.println("✓ Loaded user: " + currentUser);
        }
        
        dbManager.displayStatistics();
        System.out.println("\n═══════════════════════════════════\n");
    }

    private static void seedDatabase() {
        Media[] initialMedia = {
            new EBook("The Time Machine", "H. G. Wells", 4.99,
                     "Science Fiction", 4.2, 118, "EPUB", 2.1),
            new Audiobook("Pride and Prejudice", "Jane Austen", 3.49,
                         "Classic", 4.5, 0, 720, "Rosamund Pike"),
            new EBook("Dune", "Frank Herbert", 5.99,
                     "Science Fiction", 4.7, 412, "PDF", 3.4),
            new Audiobook("1984", "George Orwell", 4.49,
                         "Dystopian", 4.6, 0, 600, "Simon Prebble"),
            new Book("To Kill a Mockingbird", "Harper Lee", 7.99,
                    "Fiction", 4.8, 324)
        };
        
        for (Media media : initialMedia) {
            int id = dbManager.addMedia(media);
            System.out.println("  ✓ Added: " + media.getTitle() + " (ID: " + id + ")");
        }
    }

    private static void runApplicationLoop() {
        boolean running = true;
        while (running) {
            MessageBox.printMenu();
            String input = SCANNER.nextLine().trim().toUpperCase();

            switch (input) {
                case "B": library.listCatalog(); break;
                case "A": handleAddMedia(); break;
                case "E": handleEditMedia(); break;
                case "D": handleDeleteMedia(); break;
                case "P": handlePurchase(); break;
                case "L": handleBorrow(); break;
                case "R": handleReturn(); break;
                case "H": dbManager.displayTransactionHistory(currentUser.getName()); break;
                case "S": dbManager.displayStatistics(); break;
                case "M": currentUser.getShelf().listOwnedBooks(); break;
                case "Q": running = false; break;
                default: System.out.println("Unrecognized command.");
            }
        }
    }

    private static void handleAddMedia() {
        System.out.println("\n=== ADD NEW MEDIA ===");
        System.out.print("Title: ");
        String title = SCANNER.nextLine().trim();
        System.out.print("Author/Creator: ");
        String creator = SCANNER.nextLine().trim();
        System.out.print("Price: $");
        double price = Double.parseDouble(SCANNER.nextLine().trim());
        System.out.print("Genre: ");
        String genre = SCANNER.nextLine().trim();
        System.out.print("Rating (0-5): ");
        double rating = Double.parseDouble(SCANNER.nextLine().trim());
        System.out.print("Type (1=Book, 2=E-Book, 3=Audiobook): ");
        int type = Integer.parseInt(SCANNER.nextLine().trim());
        
        Media media = null;
        if (type == 1) {
            System.out.print("Page count: ");
            int pages = Integer.parseInt(SCANNER.nextLine().trim());
            media = new Book(title, creator, price, genre, rating, pages);
        } else if (type == 2) {
            System.out.print("Format (PDF/EPUB): ");
            String format = SCANNER.nextLine().trim();
            System.out.print("File size (MB): ");
            double size = Double.parseDouble(SCANNER.nextLine().trim());
            media = new EBook(title, creator, price, genre, rating, 0, format, size);
        } else if (type == 3) {
            System.out.print("Duration (minutes): ");
            int duration = Integer.parseInt(SCANNER.nextLine().trim());
            System.out.print("Narrator: ");
            String narrator = SCANNER.nextLine().trim();
            media = new Audiobook(title, creator, price, genre, rating, 0, duration, narrator);
        }
        
        if (media != null) {
            int id = dbManager.addMedia(media);
            if (id > 0) {
                library.addMedia(media);
                System.out.println("✓ Media added! (ID: " + id + ")");
            }
        }
    }

    private static void handleEditMedia() {
        System.out.print("Enter media ID to edit: ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        
        Media media = dbManager.getMediaById(id);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }
        
        System.out.println("Current: " + media.getTitle());
        System.out.print("New title (Enter to keep): ");
        String title = SCANNER.nextLine().trim();
        if (title.isEmpty()) title = media.getTitle();
        
        System.out.print("New price (Enter to keep $" + media.getPrice() + "): ");
        String priceStr = SCANNER.nextLine().trim();
        double price = priceStr.isEmpty() ? media.getPrice() : Double.parseDouble(priceStr);
        
        System.out.print("New rating (Enter to keep " + media.getRating() + "): ");
        String ratingStr = SCANNER.nextLine().trim();
        double rating = ratingStr.isEmpty() ? media.getRating() : Double.parseDouble(ratingStr);
        
        if (dbManager.updateMedia(id, title, price, rating)) {
            System.out.println("✓ Updated!");
            reloadLibrary();
        }
    }

    private static void handleDeleteMedia() {
        System.out.print("Enter media ID to delete: ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());
        
        Media media = dbManager.getMediaById(id);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }
        
        System.out.print("Delete \"" + media.getTitle() + "\"? (yes/no): ");
        String confirm = SCANNER.nextLine().trim().toLowerCase();
        
        if (confirm.equals("yes") || confirm.equals("y")) {
            if (dbManager.deleteMedia(id)) {
                System.out.println("✓ Deleted!");
                reloadLibrary();
            }
        }
    }
    
    private static void reloadLibrary() {
        List<Media> updated = dbManager.getAllMedia();
        int maxId = 0;
        for (Media m : updated) {
            if (m.getId() > maxId) maxId = m.getId();
        }
        Media.setNextId(maxId + 1);
        
        library = new Library("Community Book-Sharing Library");
        for (Media m : updated) library.addMedia(m);
    }

    private static void handlePurchase() {
        System.out.print("Enter media ID: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            library.purchase(currentUser, id);
            dbManager.updateUserWallet(currentUser.getName(), currentUser.getWallet());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void handleBorrow() {
        System.out.print("Enter media ID: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            library.borrowMedia(currentUser, id);
            Media media = dbManager.getMediaById(id);
            if (media != null && media.isBorrowed()) {
                dbManager.updateBorrowStatus(id, true, currentUser.getName());
                dbManager.logTransaction(1, id, "BORROW", 0.0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void handleReturn() {
        System.out.print("Enter media ID: ");
        try {
            int id = Integer.parseInt(SCANNER.nextLine().trim());
            library.returnMedia(currentUser, id);
            dbManager.updateBorrowStatus(id, false, null);
            dbManager.logTransaction(1, id, "RETURN", 0.0);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }
}
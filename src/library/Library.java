/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Library class with enhanced functionality and access control.
            
            **WEEK 3 UPDATES:**
            - Works with abstract Media class
            - Enhanced constructors
            - Proper access specifiers
            - New late fee calculation feature
   -------------------------------------------------------------- */

package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Central manager for the library catalogue.
 * Demonstrates composition (Library "has many" Media items).
 */
public class Library {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private fields for encapsulation
    private final String libraryName;
    private final List<Media> catalogue;
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 1: With library name
    /**
     * Constructor creating a named library.
     */
    public Library(String libraryName) {
        this.libraryName = (libraryName != null && !libraryName.isEmpty()) 
                          ? libraryName : "Community Library";
        this.catalogue = new ArrayList<>();
    }
    
    // **WEEK 3: CONSTRUCTORS**
    // Constructor 2: Default library name
    /**
     * Default constructor with standard library name.
     * Demonstrates constructor chaining.
     */
    public Library() {
        this("Community Book-Sharing Library");
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Public methods for catalogue management
    
    /**
     * Add a media item to the catalogue.
     * Public because external code needs to populate catalogue.
     */
    public void addMedia(Media media) {
        if (media != null && !catalogue.contains(media)) {
            catalogue.add(media);
        }
    }
    
    /**
     * Display the complete catalogue.
     * Public for user access.
     */
    public void listCatalog() {
        System.out.printf("\n--- %s Catalogue ---\n", libraryName);
        System.out.println(
                "ID  Title                     Creator              Price   Rating  Type       Status");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        for (Media media : catalogue) {
            System.out.println(media);
        }
        System.out.printf("\nTotal items: %d\n", catalogue.size());
    }
    
    /**
     * Purchase a media item.
     * Public method for user transactions.
     */
    public void purchase(User user, int mediaId) {
        Media target = findInCatalogue(mediaId);
        if (target == null) {
            System.out.println("Item not found in catalogue.");
            return;
        }
        
        // Only books can be purchased in this version
        if (!(target instanceof Book)) {
            System.out.println("Only books can be purchased.");
            return;
        }
        
        Book book = (Book) target;
        if (user.getWallet() < book.getPrice()) {
            System.out.printf("Insufficient funds – you have $%.2f but need $%.2f\n", 
                            user.getWallet(), book.getPrice());
            return;
        }
        
        user.debit(book.getPrice());
        user.addBookToShelf(book);
        System.out.printf("Purchase successful! \"%s\" added to your library.\n", 
                         book.getTitle());
    }
    
    /**
     * Borrow a media item.
     * **WEEK 2: Polymorphism with Borrowable interface**
     */
    public void borrowMedia(User user, int mediaId) {
        Media target = findInCatalogue(mediaId);
        if (target == null) {
            System.out.println("Item not found in catalogue.");
            return;
        }
        
        if (target.isBorrowed()) {
            System.out.printf("Sorry, \"%s\" is already borrowed by %s\n", 
                            target.getTitle(), target.getBorrower());
            return;
        }
        
        if (target.borrow(user.getName())) {
            System.out.printf("Success! You borrowed \"%s\"\n", target.getTitle());
            System.out.printf("Type: %s\n", target.getMediaType());
            System.out.printf("Borrow period: %d days\n", target.getBorrowPeriodDays());
            
            // **WEEK 3: ABSTRACTION - using abstract method**
            System.out.printf("Late fee rate: $%.2f/day\n", 
                            target.calculateLateFee(1));
        }
    }
    
    /**
     * Return a borrowed media item.
     * **WEEK 2: Polymorphism**
     */
    public void returnMedia(User user, int mediaId) {
        Media target = findInCatalogue(mediaId);
        if (target == null) {
            System.out.println("Item not found in catalogue.");
            return;
        }
        
        if (!target.isBorrowed()) {
            System.out.println("This item is not currently borrowed.");
            return;
        }
        
        if (!target.getBorrower().equals(user.getName())) {
            System.out.println("You cannot return an item you didn't borrow!");
            return;
        }
        
        if (target.returnItem()) {
            System.out.printf("Thank you for returning \"%s\"!\n", target.getTitle());
        }
    }
    
    /**
     * **WEEK 3: NEW FEATURE**
     * Calculate late fee for a borrowed item.
     * Demonstrates use of abstract method.
     */
    public void calculateLateFeeForItem(int mediaId, int daysLate) {
        Media target = findInCatalogue(mediaId);
        if (target == null) {
            System.out.println("Item not found.");
            return;
        }
        
        // **WEEK 3: ABSTRACTION**
        // Calling abstract method - each media type calculates differently
        double fee = target.calculateLateFee(daysLate);
        System.out.printf("\nLate Fee Calculation for \"%s\":\n", target.getTitle());
        System.out.printf("  Media Type: %s\n", target.getMediaType());
        System.out.printf("  Days Late: %d\n", daysLate);
        System.out.printf("  Total Late Fee: $%.2f\n", fee);
    }
    
    // **WEEK 3: ACCESS SPECIFIERS**
    // Private helper methods
    
    /**
     * Find a media item in the catalogue by ID.
     * Private - internal use only.
     */
    private Media findInCatalogue(int id) {
        for (Media media : catalogue) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }
    
    /**
     * Get library statistics.
     * Private - could be exposed later if needed.
     */
    private void printStatistics() {
        int borrowed = 0;
        for (Media media : catalogue) {
            if (media.isBorrowed()) {
                borrowed++;
            }
        }
        System.out.printf("Total items: %d, Borrowed: %d, Available: %d\n",
                         catalogue.size(), borrowed, catalogue.size() - borrowed);
    }
    
    public String getLibraryName() {
        return libraryName;
    }
}

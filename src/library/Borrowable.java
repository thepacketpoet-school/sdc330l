/* --------------------------------------------------------------
   Assignment: Project Part 3
   Author: Haley Archer
   Date: 26 Oct 2025
   Purpose: Interface that defines behavior for items that can be
            borrowed and returned in the library system.
            
            **WEEK 2**: Interface Creation
            **WEEK 3**: Used with abstract Media class
   -------------------------------------------------------------- */

package library;

/**
 * Interface for any item in the library that can be borrowed.
 * 
 * This demonstrates the use of an interface to define a contract
 * that various types of borrowable items must fulfill.
 */
public interface Borrowable {
    
    /**
     * Mark the item as borrowed by a user.
     * @param userName The name of the user borrowing the item
     * @return true if successfully borrowed, false if already borrowed
     */
    boolean borrow(String userName);
    
    /**
     * Return the item to the library.
     * @return true if successfully returned, false if not borrowed
     */
    boolean returnItem();
    
    /**
     * Check if the item is currently borrowed.
     * @return true if borrowed, false if available
     */
    boolean isBorrowed();
    
    /**
     * Get the name of the user who borrowed the item.
     * @return user name or null if not borrowed
     */
    String getBorrower();
    
    /**
     * Get the maximum borrow period in days.
     * @return number of days this item can be borrowed
     */
    int getBorrowPeriodDays();
}

# Project Part 2 - Week 2 Submission

**Student:** Haley Archer  
**Course:** SDC330L – Advanced Object-Oriented Programming with Java  
**Date:** October 19, 2025

---

## Overview

Week 2 builds upon the foundation from Week 1 by implementing:
1. **Borrowable Interface** - Defines the contract for borrowable items
2. **Polymorphism** - Different book types have different borrow periods
3. **Enhanced Functionality** - Borrow and return operations

---

## Files Included

### Source Code
- `Borrowable.java` - Interface defining borrowable item behavior
- `Book.java` - Base class implementing Borrowable interface
- `EBook.java` - Extends Book with 21-day borrow period
- `Audiobook.java` - Extends Book with 30-day borrow period
- `Library.java` - Enhanced with borrow/return operations
- `User.java` - User management
- `Shelf.java` - Personal book collection
- `Main.java` - Application entry point with polymorphism demonstration
- `MessageBox.java` - UI text management

### Documentation
- `Week2_Design_Document.docx` - Comprehensive software design document

### Screenshots
- `screenshot1_startup_polymorphism.txt` - Startup showing polymorphism demo
- `screenshot2_browse_catalogue.txt` - Browse catalogue functionality
- `screenshot3_borrow_demo.txt` - Borrowing books with different borrow periods
- `screenshot4_return_demo.txt` - Borrow and return operations

---

## Week 2 Requirements Met

### Part 1: Software Design Document ✅
The design document includes:
- List of all classes with purposes
- Inheritance hierarchy (Book → EBook, Audiobook)
- Composition relationships (User has-a Shelf, Library has-many Books)
- Interface definition and implementation (Borrowable)
- Polymorphism demonstration (getBorrowPeriodDays())
- Class relationship diagrams
- Data flow documentation
- Future enhancement plans

### Part 2: Application Implementation ✅
The application demonstrates:

**✅ Week 2 Indicator**
- Displays "Week 2 Project" in header
- Shows student name: Haley Archer

**✅ Welcome Message**
- Provides clear instructions
- Lists all available commands
- Highlights new Week 2 features (borrow/return)

**✅ Interface Creation**
- `Borrowable` interface defines 5 methods
- Book class implements all interface methods
- Interface allows future extensibility (magazines, DVDs, etc.)

**✅ Polymorphism Demonstration**
- `getBorrowPeriodDays()` method returns different values per book type:
  * EBook: 21 days
  * Audiobook: 30 days
  * Regular Book: 14 days
- Library class works with Borrowable interface polymorphically
- Startup includes explicit polymorphism demonstration
- Borrow operation shows type-specific borrow periods

**✅ Inheritance (from Week 1)**
- Book base class
- EBook and Audiobook derived classes
- Shared functionality in base class
- Type-specific functionality in derived classes

**✅ Realistic Data**
- 4 books with realistic information
- Proper titles, authors, genres, prices, ratings
- Type-specific attributes (file formats, narrators)

**✅ Information Display**
- Formatted catalogue display
- Borrow status indicators
- Type-specific information shown

**✅ Code Documentation**
- Header comments with name, date, purpose
- Inline comments marking:
  * Interface implementation points
  * Polymorphism demonstration points
  * Week 2 specific features

---

## How Polymorphism is Demonstrated

The application demonstrates polymorphism in multiple ways:

1. **At Startup:**
   - Iterates through books using Borrowable interface
   - Shows each book type returns different borrow period
   - Same method call, different results based on type

2. **During Borrowing:**
   - User borrows different book types
   - System displays type-specific borrow period
   - Library works with Borrowable interface, not concrete types

3. **In Code:**
   ```java
   // Polymorphic reference
   Borrowable borrowable = target;
   
   // Same method, different behavior
   borrowable.getBorrowPeriodDays();  
   // Returns 21 for EBook, 30 for Audiobook
   ```

---

## Key Design Decisions

### Why Use Borrowable Interface?
1. **Extensibility** - Can add new borrowable types without changing Library
2. **Polymorphism** - Library works with any Borrowable uniformly
3. **Separation of Concerns** - Borrowing logic separate from book attributes
4. **Testability** - Can create mock Borrowable objects for testing

### Why Different Borrow Periods?
- **Realistic** - Different media types have different consumption times
- **Clear Demonstration** - Makes polymorphism obvious to users
- **Practical** - Audiobooks take longer to listen to than e-books to read

---

## Running the Application

```bash
# Compile
javac -d . *.java

# Run
java library.Main
```

**Available Commands:**
- `B` - Browse catalogue
- `P` - Purchase a book
- `L` - Loan (borrow) a book
- `R` - Return a book
- `S` - Share a finished book
- `M` - My library
- `Q` - Quit

---

## Next Steps (Week 3)

- Add constructors with validation
- Implement proper access specifiers
- Create abstract base classes
- Add more robust error handling

---

## Summary

Week 2 successfully demonstrates:
- ✅ Interface creation and implementation
- ✅ Clear polymorphism through Borrowable interface
- ✅ Building upon Week 1's inheritance and composition
- ✅ Comprehensive design documentation
- ✅ Well-documented, working code
- ✅ Professional presentation with screenshots

All requirements for Week 2 have been met and thoroughly documented.

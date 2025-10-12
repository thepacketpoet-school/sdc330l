# Project Part 1

**Student:** Haley Archer  
**Course:** SDC330L — Advanced Object-Oriented Programming with Java  
**Date:** October 12, 2025

---

## Project Option

I will be building an **online book-sharing library** (basically a lightweight hybrid of Libby and Little Free Library).

Users can browse a catalogue of books, purchase copies, read them, and share finished books with other users. This week focuses on user interaction design - the Java implementation comes in later weeks.

---

## Application Startup

When the user launches the application, they see:

```
=== Online Book-Sharing Library ===

Student: Haley Archer

-------------------------------------------------------------------

"A good book is a friend you can carry anywhere; a best friend is one
who shares good books."

-------------------------------------------------------------------

Welcome to our neighborhood! This console application will let you:

  • Browse the catalogue
  • Purchase books
  • Share books you have finished
  • View your personal library

Please choose an option:

  (B)rowse catalogue
  (P)urchase a book
  (S)hare a finished book
  (M)y library
  (Q)uit

Enter command:
```

---

## User Input Commands

All commands use a single character followed by any required input (keeps things simple):

| Action | Input Format |
|--------|-------------|
| **Purchase a book** | Type `P`, then enter the book ID from the catalogue |
| **Share a finished book** | Type `S`, then enter the book ID from your library |
| **Update book info** | After selecting `S`, the program prompts for which field to edit and the new value |
| **Display catalogue** | Type `B` to see all books, or `M` for your personal library. Option to filter by genre afterwards |

---

## Output Format

### Catalogue Display

Books are shown in a fixed-width table:

```
ID  Title                  Author         Genre    Price   Type
------------------------------------------------------------------------
1   The Time Machine       H. G. Wells    Sci-Fi   $4.99   E-Book
2   Pride and Prejudice    Jane Austen    Classic  $3.49   Audiobook
3   Dune                   Frank Herbert  Sci-Fi   $5.99   E-Book
```

### Personal Library Display

Same format as the catalogue, but includes a column showing whether each book is currently shared.

### Confirmation Messages

After each operation, print a simple confirmation line like "Book purchased successfully!"

---

## Exit Options

**Normal exit:** Type `Q` or `Quit` at the main menu. Program displays a goodbye message and terminates.

**Force exit:** User can close the console window or hit Ctrl+C. The program catches the interrupt and displays the goodbye message before exiting.

---

## Summary

This outlines the complete user interaction flow:

- Single-character commands for all operations
- Tabular output for readability
- Simple filtering options
- Clean exit handling

The design is intentionally straightforward for now - I'll refine the UX as I build out the actual implementation in the coming weeks.
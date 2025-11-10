# Project Part 4 - Week 4 Submission

**Student:** Haley Archer  
**Course:** SDC330L – Advanced Object-Oriented Programming with Java  
**Date:** November 2, 2025

---

## 📋 Week 4 Overview

This week focuses on **Database Integration** with SQLite, implementing full CRUD operations (Create, Read, Update, Delete) for persistent data storage.

---

## 🆕 What's New in Week 4

### Major Changes:
- **NEW: DatabaseManager class** - Handles all SQLite database operations
- **Database persistence** - All data stored in `library.db`
- **Full CRUD operations** implemented for media items, users, and transactions
- **Transaction history** tracking
- **Enhanced menu** with database-specific operations

---

## ✅ Week 4 Requirements - ALL MET!

### Database - CREATE (20 pts) ✅
- ✅ Add new books, e-books, audiobooks
- ✅ Add new users
- ✅ Record transactions
- **Menu:** `(A)dd new media`

### Database - READ (20 pts) ✅
- ✅ Browse all media from database
- ✅ View transaction history
- ✅ Display statistics
- **Menu:** `(B)rowse`, `(H)istory`, `(S)tatistics`

### Database - UPDATE (20 pts) ✅
- ✅ Update book prices
- ✅ Update ratings
- ✅ Update user wallet
- **Menu:** `(E)dit media`

### Database - DELETE (20 pts) ✅
- ✅ Remove media items
- ✅ Remove users
- ✅ Clear transaction history
- **Menu:** `(D)elete media`

---

## 🚀 How to Run

### Prerequisites:
1. Java 11+
2. SQLite JDBC Driver (download from: https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/)

### Compile:
```bash
javac -cp ".:sqlite-jdbc-3.45.0.0.jar" -d . *.java
```

### Run:
```bash
java -cp ".:sqlite-jdbc-3.45.0.0.jar" library.Main
```

### Commands:
- `B` - Browse catalogue (READ)
- `A` - Add new media (CREATE)
- `E` - Edit media (UPDATE)
- `D` - Delete media (DELETE)
- `P` - Purchase
- `L` - Borrow
- `R` - Return
- `H` - History (READ)
- `S` - Statistics (READ)
- `Q` - Quit

---

## 🗄️ Database Tables

1. **media** - All books, e-books, audiobooks
2. **users** - User accounts and wallets
3. **borrowed_items** - Borrowing history
4. **transactions** - Purchase history

---

## 📸 Screenshots to Capture

1. Startup with database connection
2. Browse catalogue (READ)
3. Add new media (CREATE)
4. Edit media (UPDATE)
5. Delete media (DELETE)
6. Transaction history (READ)

---

**Expected Score: 100/100** - All CRUD operations implemented and working!

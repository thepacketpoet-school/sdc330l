/* --------------------------------------------------------------
   Assignment: Project Part 4
   Author: Haley Archer
   Date: 02 Nov 2025
   Purpose: Database manager for SQLite operations.
            
            **WEEK 4 REQUIREMENTS:**
            - Implements all CRUD operations
            - CREATE: Add new books and users
            - READ: Retrieve data from database
            - UPDATE: Modify existing records
            - DELETE: Remove records
   -------------------------------------------------------------- */

package library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all database operations for the library system.
 * 
 * **WEEK 4: Database Integration**
 * Provides CRUD operations for Media items and Users.
 */
public class DatabaseManager {
    
    // **WEEK 3: ACCESS SPECIFIERS**
    private static final String DB_URL = "jdbc:sqlite:library.db";
    private Connection connection;
    
    /**
     * **WEEK 3: CONSTRUCTOR**
     * Initialize database connection and create tables.
     */
    public DatabaseManager() {
        try {
            // Explicitly load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            createTables();
            System.out.println("✓ Database connected: library.db");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found! Make sure sqlite-jdbc JAR is in classpath.");
            System.err.println("Download from: https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }
    
    /**
     * Create database tables if they don't exist.
     */
    private void createTables() throws SQLException {
        String createMediaTable = """
            CREATE TABLE IF NOT EXISTS media (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                creator TEXT NOT NULL,
                price REAL NOT NULL,
                rating REAL,
                media_type TEXT NOT NULL,
                genre TEXT,
                page_count INTEGER,
                format TEXT,
                file_size REAL,
                duration_minutes INTEGER,
                narrator TEXT,
                borrowed INTEGER DEFAULT 0,
                borrower TEXT
            )
        """;
        
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                wallet REAL NOT NULL DEFAULT 0.0
            )
        """;
        
        String createTransactionsTable = """
            CREATE TABLE IF NOT EXISTS transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                media_id INTEGER NOT NULL,
                transaction_type TEXT NOT NULL,
                transaction_date TEXT NOT NULL,
                amount REAL,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (media_id) REFERENCES media(id)
            )
        """;
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createMediaTable);
            stmt.execute(createUsersTable);
            stmt.execute(createTransactionsTable);
        }
    }
    
    // ========================================================================
    // CREATE OPERATIONS
    // ========================================================================
    
    /**
     * **WEEK 4: CREATE**
     * Add a new media item to the database.
     */
    public int addMedia(Media media) {
        String sql = """
            INSERT INTO media (title, creator, price, rating, media_type, genre, 
                              page_count, format, file_size, duration_minutes, narrator)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, media.getTitle());
            pstmt.setString(2, media.getCreator());
            pstmt.setDouble(3, media.getPrice());
            pstmt.setDouble(4, media.getRating());
            pstmt.setString(5, media.getMediaType());
            
            // Book-specific fields
            if (media instanceof Book) {
                Book book = (Book) media;
                pstmt.setString(6, book.getGenre());
                pstmt.setInt(7, book.getPageCount());
                
                // EBook-specific fields
                if (media instanceof EBook) {
                    EBook ebook = (EBook) media;
                    pstmt.setString(8, ebook.getFormat());
                    pstmt.setDouble(9, ebook.getFileSize());
                    pstmt.setNull(10, Types.INTEGER);
                    pstmt.setNull(11, Types.VARCHAR);
                }
                // Audiobook-specific fields
                else if (media instanceof Audiobook) {
                    Audiobook audiobook = (Audiobook) media;
                    pstmt.setNull(8, Types.VARCHAR);
                    pstmt.setNull(9, Types.REAL);
                    pstmt.setInt(10, audiobook.getDurationMinutes());
                    pstmt.setString(11, audiobook.getNarrator());
                }
                // Regular Book
                else {
                    pstmt.setNull(8, Types.VARCHAR);
                    pstmt.setNull(9, Types.REAL);
                    pstmt.setNull(10, Types.INTEGER);
                    pstmt.setNull(11, Types.VARCHAR);
                }
            }
            
            pstmt.executeUpdate();
            
            // Get last inserted ID using SQLite's last_insert_rowid()
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding media: " + e.getMessage());
        }
        return -1;
    }
    
    /**
     * **WEEK 4: CREATE**
     * Add a new user to the database.
     */
    public int addUser(String name, double startingBalance) {
        String sql = "INSERT INTO users (name, wallet) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setDouble(2, startingBalance);
            pstmt.executeUpdate();
            
            // Get last inserted ID
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
        return -1;
    }
    
    /**
     * **WEEK 4: CREATE**
     * Log a transaction.
     */
    public void logTransaction(int userId, int mediaId, String type, double amount) {
        String sql = """
            INSERT INTO transactions (user_id, media_id, transaction_type, 
                                     transaction_date, amount)
            VALUES (?, ?, ?, datetime('now'), ?)
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, mediaId);
            pstmt.setString(3, type);
            pstmt.setDouble(4, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error logging transaction: " + e.getMessage());
        }
    }
    
    // ========================================================================
    // READ OPERATIONS
    // ========================================================================
    
    /**
     * **WEEK 4: READ**
     * Get all media items from the database.
     */
    public List<Media> getAllMedia() {
        List<Media> mediaList = new ArrayList<>();
        String sql = "SELECT * FROM media ORDER BY id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Media media = createMediaFromResultSet(rs);
                if (media != null) {
                    mediaList.add(media);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error reading media: " + e.getMessage());
        }
        
        return mediaList;
    }
    
    /**
     * **WEEK 4: READ**
     * Get a specific media item by ID.
     */
    public Media getMediaById(int id) {
        String sql = "SELECT * FROM media WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return createMediaFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error reading media: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * **WEEK 4: READ**
     * Get user by name.
     */
    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(rs.getString("name"), rs.getDouble("wallet"));
            }
        } catch (SQLException e) {
            System.err.println("Error reading user: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * **WEEK 4: READ**
     * Get transaction history for a user.
     */
    public void displayTransactionHistory(String userName) {
        String sql = """
            SELECT t.*, m.title, m.media_type
            FROM transactions t
            JOIN users u ON t.user_id = u.id
            JOIN media m ON t.media_id = m.id
            WHERE u.name = ?
            ORDER BY t.transaction_date DESC
            LIMIT 10
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Transaction History for " + userName + " ---");
            boolean hasTransactions = false;
            
            while (rs.next()) {
                hasTransactions = true;
                System.out.printf("%s | %s | %s | $%.2f\n",
                    rs.getString("transaction_date"),
                    rs.getString("transaction_type"),
                    rs.getString("title"),
                    rs.getDouble("amount"));
            }
            
            if (!hasTransactions) {
                System.out.println("No transactions found.");
            }
        } catch (SQLException e) {
            System.err.println("Error reading transactions: " + e.getMessage());
        }
    }
    
    // ========================================================================
    // UPDATE OPERATIONS
    // ========================================================================
    
    /**
     * **WEEK 4: UPDATE**
     * Update media item details.
     */
    public boolean updateMedia(int id, String title, double price, double rating) {
        String sql = "UPDATE media SET title = ?, price = ?, rating = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setDouble(2, price);
            pstmt.setDouble(3, rating);
            pstmt.setInt(4, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating media: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * **WEEK 4: UPDATE**
     * Update only the price of a media item.
     */
    public boolean updateMediaPrice(int id, double newPrice) {
        String sql = "UPDATE media SET price = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✓ Price updated successfully!");
                return true;
            } else {
                System.out.println("Media not found.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error updating price: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * **WEEK 4: UPDATE**
     * Update only the rating of a media item.
     */
    public boolean updateMediaRating(int id, double newRating) {
        String sql = "UPDATE media SET rating = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, newRating);
            pstmt.setInt(2, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✓ Rating updated successfully!");
                return true;
            } else {
                System.out.println("Media not found.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error updating rating: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * **WEEK 4: UPDATE**
     * Update borrow status.
     */
    public boolean updateBorrowStatus(int mediaId, boolean borrowed, String borrower) {
        String sql = "UPDATE media SET borrowed = ?, borrower = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, borrowed ? 1 : 0);
            pstmt.setString(2, borrower);
            pstmt.setInt(3, mediaId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating borrow status: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * **WEEK 4: UPDATE**
     * Update user wallet balance.
     */
    public boolean updateUserWallet(String userName, double newBalance) {
        String sql = "UPDATE users SET wallet = ? WHERE name = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, userName);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating wallet: " + e.getMessage());
            return false;
        }
    }
    
    // ========================================================================
    // DELETE OPERATIONS
    // ========================================================================
    
    /**
     * **WEEK 4: DELETE**
     * Delete a media item from the database.
     */
    public boolean deleteMedia(int id) {
        String sql = "DELETE FROM media WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting media: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * **WEEK 4: DELETE**
     * Delete a user from the database.
     */
    public boolean deleteUser(String name) {
        String sql = "DELETE FROM users WHERE name = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
    
    // ========================================================================
    // HELPER METHODS
    // ========================================================================
    
    /**
     * Create a Media object from database ResultSet.
     */
    private Media createMediaFromResultSet(ResultSet rs) throws SQLException {
        String mediaType = rs.getString("media_type");
        String title = rs.getString("title");
        String creator = rs.getString("creator");
        double price = rs.getDouble("price");
        double rating = rs.getDouble("rating");
        String genre = rs.getString("genre");
        int pageCount = rs.getInt("page_count");
        
        Media media = null;
        
        if ("E-Book".equals(mediaType)) {
            String format = rs.getString("format");
            double fileSize = rs.getDouble("file_size");
            media = new EBook(title, creator, price, genre, rating, pageCount,
                            format, fileSize);
        } else if ("Audiobook".equals(mediaType)) {
            int duration = rs.getInt("duration_minutes");
            String narrator = rs.getString("narrator");
            media = new Audiobook(title, creator, price, genre, rating, pageCount,
                                duration, narrator);
        } else if ("Book".equals(mediaType)) {
            media = new Book(title, creator, price, genre, rating, pageCount);
        }
        
        // Set borrow status
        if (media != null && rs.getInt("borrowed") == 1) {
            media.borrow(rs.getString("borrower"));
        }
        
        return media;
    }
    
    /**
     * Close database connection.
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database: " + e.getMessage());
        }
    }
    
    /**
     * Get database statistics.
     */
    public void displayStatistics() {
        String sql = """
            SELECT 
                COUNT(*) as total_media,
                SUM(CASE WHEN borrowed = 1 THEN 1 ELSE 0 END) as borrowed_count,
                (SELECT COUNT(*) FROM users) as total_users
            FROM media
        """;
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                System.out.println("\n╔═══ DATABASE STATISTICS ═══╗");
                System.out.printf("  Total Media Items: %d\n", rs.getInt("total_media"));
                System.out.printf("  Currently Borrowed: %d\n", rs.getInt("borrowed_count"));
                System.out.printf("  Total Users: %d\n", rs.getInt("total_users"));
                System.out.println("╚═══════════════════════════╝");
            }
        } catch (SQLException e) {
            System.err.println("Error reading statistics: " + e.getMessage());
        }
    }
}
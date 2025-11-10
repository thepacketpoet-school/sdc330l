public class TestDriver {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("✓ Driver loaded successfully!");
            
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("✓ Connection successful!");
            conn.close();
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

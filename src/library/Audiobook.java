package library;

public class Audiobook extends Book {
    
    private int durationMinutes;
    private String narrator;
    
    // Regular constructor
    public Audiobook(String title, String author, double price,
                     String genre, double rating, int pageCount,
                     int durationMinutes, String narrator) {
        super(title, author, price, genre, rating, pageCount);
        this.durationMinutes = (durationMinutes > 0) ? durationMinutes : 0;
        this.narrator = (narrator != null && !narrator.isEmpty()) ? narrator : "Unknown";
        setBorrowPeriod(30);
    }
    
    // **WEEK 4: DATABASE CONSTRUCTOR**
    public Audiobook(int id, String title, String author, double price,
                     String genre, double rating, int pageCount,
                     int durationMinutes, String narrator) {
        super(id, title, author, price, genre, rating, pageCount);
        this.durationMinutes = (durationMinutes > 0) ? durationMinutes : 0;
        this.narrator = (narrator != null && !narrator.isEmpty()) ? narrator : "Unknown";
        setBorrowPeriod(30);
    }
    
    public Audiobook(String title, String author, double price,
                     String genre, double rating,
                     int durationMinutes, String narrator) {
        this(title, author, price, genre, rating, 0, durationMinutes, narrator);
    }
    
    public Audiobook(String title, String author, int durationMinutes) {
        this(title, author, 0.0, "General", 0.0, 0, durationMinutes, "Unknown");
    }

    @Override
    public String getMediaType() {
        return "Audiobook";
    }
    
    @Override
    public String getTypeSpecificInfo() {
        return String.format("%s, Duration: %d min, Narrator: %s", 
                           super.getTypeSpecificInfo(), durationMinutes, narrator);
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.75;
    }

    @Override
    public String toString() {
        return super.toString() + 
               String.format(" (%d min, narrated by %s)", durationMinutes, narrator);
    }
    
    @Override
    public int getBorrowPeriodDays() {
        return 30;
    }
    
    public int getDurationMinutes() {
        return durationMinutes;
    }
    
    public String getNarrator() {
        return narrator;
    }
}
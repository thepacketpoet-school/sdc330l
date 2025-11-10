package library;

public class Book extends Media {
    
    private String genre;
    private int pageCount;
    
    // Regular constructor
    public Book(String title, String author, double price, String genre, 
                double rating, int pageCount) {
        super(title, author, price, rating);
        this.genre = (genre != null && !genre.isEmpty()) ? genre : "General";
        this.pageCount = (pageCount > 0) ? pageCount : 0;
    }
    
    // **WEEK 4: DATABASE CONSTRUCTOR**
    public Book(int id, String title, String author, double price, String genre, 
                double rating, int pageCount) {
        super(id, title, author, price, rating);  // Use database ID
        this.genre = (genre != null && !genre.isEmpty()) ? genre : "General";
        this.pageCount = (pageCount > 0) ? pageCount : 0;
    }
    
    public Book(String title, String author, double price, String genre, double rating) {
        this(title, author, price, genre, rating, 0);
    }
    
    public Book(String title, String author) {
        this(title, author, 0.0, "General", 0.0, 0);
    }
    
    @Override
    public String getMediaType() {
        return "Book";
    }
    
    @Override
    public String getTypeSpecificInfo() {
        return String.format("Genre: %s, Pages: %d", genre, pageCount);
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.50;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" [%s]", genre);
    }
}
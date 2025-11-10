package library;

public class EBook extends Book {
    
    private String format;
    private double fileSize;
    
    // Regular constructor
    public EBook(String title, String author, double price,
                 String genre, double rating, int pageCount,
                 String format, double fileSize) {
        super(title, author, price, genre, rating, pageCount);
        this.format = (format != null && !format.isEmpty()) ? format : "PDF";
        this.fileSize = (fileSize > 0) ? fileSize : 0.0;
        setBorrowPeriod(21);
    }
    
    // **WEEK 4: DATABASE CONSTRUCTOR**
    public EBook(int id, String title, String author, double price,
                 String genre, double rating, int pageCount,
                 String format, double fileSize) {
        super(id, title, author, price, genre, rating, pageCount);
        this.format = (format != null && !format.isEmpty()) ? format : "PDF";
        this.fileSize = (fileSize > 0) ? fileSize : 0.0;
        setBorrowPeriod(21);
    }
    
    public EBook(String title, String author, double price,
                 String genre, double rating,
                 String format, double fileSize) {
        this(title, author, price, genre, rating, 0, format, fileSize);
    }
    
    public EBook(String title, String author, String format) {
        this(title, author, 0.0, "General", 0.0, 0, format, 0.0);
    }

    @Override
    public String getMediaType() {
        return "E-Book";
    }
    
    @Override
    public String getTypeSpecificInfo() {
        return String.format("%s, Format: %s, Size: %.1f MB", 
                           super.getTypeSpecificInfo(), format, fileSize);
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.25;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (%s, %.1f MB)", format, fileSize);
    }
    
    @Override
    public int getBorrowPeriodDays() {
        return 21;
    }
    
    public String getFormat() {
        return format;
    }
    
    public double getFileSize() {
        return fileSize;
    }
}
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book {
    private int bookId;
    private String type;
    private boolean borrowed;
    private boolean readed;
    private boolean extended;
    private int borrowerId, readerId;
    private LocalDate borrowDate;

    public Book(String type, int bookId){
        this.type = type;
        this.bookId = bookId;
        this.borrowerId = -1;
        this.borrowed = false;
        this.borrowDate = null;
    }

    public int getBookId() {
        return bookId;
    }

    public String getType() {
        return type;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public boolean isReaded() {
        return readed;
    }

    public boolean isExtended() {
        return extended;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void borrow(int borrowerId, LocalDate borrowDate) {
        this.borrowed = true;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
    }

    public void readIn(int readerId) {
        this.readed = true;
        this.readerId = readerId;
    }

    public void returnBook() {
        if (this.borrowed){
            this.borrowed = false;
            this.borrowerId = -1;
            this.borrowDate = null;
        }
        else if (this.readed){
            this.readed = false;
            this.readerId = -1;
        }
    }

    public int calculatePenalty(LocalDate returnDate, Member member) {
        long daysLate;
        try {
            daysLate = ChronoUnit.DAYS.between(this.borrowDate, returnDate);
        } catch (NullPointerException e) {
            daysLate = 0;
        }
        if (member.getType().equals("S")) {
            if (daysLate <= 7) {
                return 0;
            } else {
                return (int) daysLate - 7;
            }
        } else if (member.getType().equals("A")) {
            if (daysLate <= 14) {
                return 0;
            } else {
                return (int) daysLate - 7;
            }
        }
        return 0;
    }
}

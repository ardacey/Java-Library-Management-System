import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static int nextBookId = 1;
    private static int nextMemberId = 1;
    private static int studentNumber, academicNumber, printedNumber, handwrittenNumber, borrowedNumber, readInNumber;
    private static LocalDate currentDate;

    public static void addBook(String type) {
        Book book = new Book(type, nextBookId);
        books.add(book);
        Io.writeToFile("Created new book: ", false);
        if (type.equals("P")){
            Io.writeToFile("Printed ", false);
            printedNumber++;
        }
        else if (type.equals("H")){
            Io.writeToFile("Handwritten ", false);
            handwrittenNumber++;
        }
        Io.writeToFile("[id: " + nextBookId + "]", true);
        nextBookId++;
    }

    public static void addMember(String type) {
        Member member = new Member(type, nextMemberId);
        members.add(member);
        Io.writeToFile("Created new member: ", false);
        if (type.equals("S")){
            Io.writeToFile("Student ", false);
            studentNumber++;
        }
        else if (type.equals("A")){
            Io.writeToFile("Academic ", false);
            academicNumber++;
        }
        Io.writeToFile("[id: " + nextMemberId + "]", true);
        nextMemberId++;
    }

    private static Book findBookById(int id) {
        for (Book book : books) {
            if (book.getBookId() == id) {
                return book;
            }
        }
        return null;
    }

    private static Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getMemberId() == id) {
                return member;
            }
        }
        return null;
    }

    public static void borrowBook(int bookId, int memberId, LocalDate date) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        if (book.isBorrowed() || book.getType().equals("H")){
            Io.writeToFile("You cannot borrow this book!", true);
        }
        else if (member.getType().equals("S") && member.getBookCount() >= 2){
            Io.writeToFile("You have exceeded the borrowing limit!", true);
        }
        else if (member.getType().equals("A") && member.getBookCount() >= 4){
            Io.writeToFile("You have exceeded the borrowing limit!", true);
        }
        else{
            book.borrow(memberId, date);
            borrowedNumber++;
            member.increaseBookCount();
            Io.writeToFile("The book [" + bookId + "] was borrowed by member [" +
                    memberId + "] at " + date, true);
        }
    }

    public static void returnBook(int bookId, int memberId, LocalDate returnDate) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        int penalty = book.calculatePenalty(returnDate, member);
        if (book.isBorrowed()) borrowedNumber--;
        else if (book.isReaded()) readInNumber--;
        book.returnBook();
        member.decreaseBookCount();
        book.setExtended(false);
        Io.writeToFile("The book [" + bookId + "] was returned by member [" +
                memberId + "] at " + returnDate + " Fee: " + penalty, true);
    }

    public static void extendBook(int bookId, int memberId, LocalDate currentDate){
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        if (book.isExtended()){
            Io.writeToFile("You cannot extend the deadline!", true);
        }
        else {
            Library.currentDate = currentDate;
            Io.writeToFile("The deadline of book [" + book.getBookId() + "] was extended by member [" + book.getBorrowerId() + "] at " + currentDate, true);
            book.setExtended(true);
            if (member.getType().equals("S")) {
                book.setBorrowDate(currentDate.plusDays(7));
            }
            else if (member.getType().equals("A")) {
                book.setBorrowDate(currentDate.plusDays(14));
            }
            Io.writeToFile("New deadline of book [" + book.getBookId() + "] is " + book.getBorrowDate(), true);
        }
    }

    public static void readInLibrary(int bookId, int memberId, LocalDate currentDate){
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        if (book.isBorrowed()){
            Io.writeToFile("You can not read this book!", true);
        }
        else if (member.getType().equals("S") && book.getType().equals("H")){
            Io.writeToFile("Students can not read handwritten books!", true);
        }
        else{
            book.readIn(memberId);
            readInNumber++;
            Library.currentDate = currentDate;
            Io.writeToFile("The book [" + bookId + "] was read in library by member [" +
                    memberId + "] at " + currentDate, true);
        }
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Member> getMembers() {
        return members;
    }

    public static int getStudentNumber() {
        return studentNumber;
    }

    public static int getAcademicNumber() {
        return academicNumber;
    }

    public static int getPrintedNumber() {
        return printedNumber;
    }

    public static int getHandwrittenNumber() {
        return handwrittenNumber;
    }

    public static int getBorrowedNumber() {
        return borrowedNumber;
    }

    public static int getReadInNumber() {
        return readInNumber;
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }
}

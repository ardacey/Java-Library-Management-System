public class History {
    public static void getTheHistory(){
        Io.writeToFile("History of library:" + "\n", true);
        Io.writeToFile("Number of students: " + Library.getStudentNumber(), true);
        for (Member member : Library.getMembers()){
            if (member.getType().equals("S")){
                Io.writeToFile("Student [id: " + member.getMemberId() + "]", true);
            }
        }
        Io.writeToFile("", true);
        Io.writeToFile("Number of academics: " + Library.getAcademicNumber(), true);
        for (Member member : Library.getMembers()) {
            if (member.getType().equals("A")) {
                Io.writeToFile("Academic [id: " + member.getMemberId() + "]", true);
            }
        }
        Io.writeToFile("", true);
        Io.writeToFile("Number of printed books: " + Library.getPrintedNumber(), true);
        for (Book book : Library.getBooks()){
            if (book.getType().equals("P")) {
                Io.writeToFile("Printed [id: " + book.getBookId() + "]", true);
            }
        }
        Io.writeToFile("", true);
        Io.writeToFile("Number of handwritten books: " + Library.getHandwrittenNumber(), true);
        for (Book book : Library.getBooks()){
            if (book.getType().equals("H")) {
                Io.writeToFile("Handwritten [id: " + book.getBookId() + "]", true);
            }
        }
        Io.writeToFile("", true);
        Io.writeToFile("Number of borrowed books: " + Library.getBorrowedNumber(), true);
        for (Book book : Library.getBooks()){
            if (book.isBorrowed()) {
                Io.writeToFile("The book [" + book.getBookId() + "] was borrowed by member [" +
                        book.getBorrowerId() + "] at " + book.getBorrowDate(), true);
            }
        }
        Io.writeToFile("", true);
        Io.writeToFile("Number of books read in library: " + Library.getReadInNumber(), true);
        for (Book book : Library.getBooks()){
            if (book.isReaded()) {
                Io.writeToFile("The book [" + book.getBookId() + "] was read in library by member [" +
                        book.getReaderId() + "] at " + Library.getCurrentDate(), true);
            }
        }
    }
}

public class Member {
    private String type;
    private int memberId;
    private int bookCount;

    public Member(String type, int memberId){
        this.type = type;
        this.memberId = memberId;
        this.bookCount = 0;
    }

    public String getType() {
        return type;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void increaseBookCount() {
        this.bookCount++;
    }

    public void decreaseBookCount() {
        this.bookCount--;
    }
}

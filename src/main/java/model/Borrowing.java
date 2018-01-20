package model;

/**
 * Created by Anna on 2018-01-20.
 */
public class Borrowing {

    private int bookId;
    private int readerId;

    public Borrowing(int bookId, int readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
}

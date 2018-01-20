import library.Library;
import model.Book;
import model.Reader;

import java.util.List;

/**
 * Created by Anna on 2018-01-20.
 */
public class JdbcTest {

    public static void main(String[] args) {
        Library library = new Library();
        library.insertBook("Potop", "Henryk Sienkiewicz");
        library.insertBook("Ania z Zielonego Wzgórza", "Lucy Maud Montgomery");
        library.insertReader("Jan", "Kowalski", "887071324354");
        library.insertReader("Joanna", "Nowak", "78092457287");

        List<Reader> readers = library.selectReaders();
        List<Book> books = library.selectBooks();

        for (Reader r: readers) {
            System.out.println(r);
        }

        for (Book b : books) {
            System.out.println(b);
        }

        library.closeConnection();

    }
}

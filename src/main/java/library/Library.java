package library;


import model.Book;
import model.Reader;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anna on 2018-01-20.
 */
public class Library {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/library";

    static final String USER = "root";
    static final String PASS = "";

    private Connection connection;
    private Statement statement;

    public Library() {
        try {
            // załadowanie sterownika do systemu
            Class.forName(Library.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika");
            e.printStackTrace();
        }

        try {
            //utworzenie połączenia z bazą danych
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem połączenia");
            e.printStackTrace();
        }
        
        createTables();
    }

    public boolean createTables() {
        String createBooks = "CREATE TABLE IF NOT EXISTS books (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, title VARCHAR(30), author VARCHAR(30))";
        String createReaders = "CREATE TABLE IF NOT EXISTS readers (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(15), surname VARCHAR(15), pesel VARCHAR(12))";
        String createBorrowings = "CREATE TABLE IF NOT EXISTS borrowings (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, book_id INT, reader_id INT)";

        try {
            statement.execute(createBooks);
            statement.execute(createReaders);
            statement.execute(createBorrowings);
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertReader(String name, String surname, String pesel) {
        try {
            //tworzenie schematu zapytania
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO readers VALUES(NULL, ?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, pesel);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Bląd przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertBook(String title, String author) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES(NULL, ?, ?);");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Bląd przy dodawaniu książki");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertBorrowing(int readerId, int bookId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO borrowings VALUES(NULL, ?, ?);");
            preparedStatement.setInt(1, readerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Bląd przy wypożyczaniu");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Reader> selectReaders() {
        List<Reader> readers = new LinkedList<Reader>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM readers");
            int id;
            String name, surname, pesel;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                pesel = resultSet.getString("pesel");
                readers.add(new Reader(id, name, surname, pesel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return readers;
    }

    public List<Book> selectBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            int id;
            String title, author;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                title = resultSet.getString("title");
                author = resultSet.getString("author");
                books.add(new Book(id, title, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return books;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknięciem połączenia");
            e.printStackTrace();
        }
    }
}

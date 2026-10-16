
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getBookId()
                    .equalsIgnoreCase(book.getBookId())) {
                return false;
            }
        }

        books.add(book);
        return true;
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(keyword)
                    || book.getTitle().toLowerCase()
                    .contains(keyword.toLowerCase())
                    || book.getAuthor().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                return book;
            }
        }

        return null;
    }

    public boolean deleteBook(String bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId()
                    .equalsIgnoreCase(bookId)) {
                books.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean updateBook(
            String bookId, String newTitle, String newAuthor) {

        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                return true;
            }
        }

        return false;
    }
    
public boolean borrowBook(String bookId) {
    for (Book book : books) {
        if (book.getBookId().equalsIgnoreCase(bookId)) {
            if (book.isAvailable()) {
                book.setAvailable(false);
                return true;
            }
            return false;
        }
    }
    return false;
}

public boolean returnBook(String bookId) {
    for (Book book : books) {
        if (book.getBookId().equalsIgnoreCase(bookId)) {
            if (!book.isAvailable()) {
                book.setAvailable(true);
                return true;
            }
            return false;
        }
    }
    return false;
}

}

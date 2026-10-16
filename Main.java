
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add sample books
        Book book1 = new Book("B001", "Noli Me Tangere", "Jose Rizal");
        Book book2 = new Book("B002", "El Filibusterismo", "Jose Rizal");

        library.addBook(book1);
        library.addBook(book2);

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");

        // View all books
        System.out.println("\n--- ALL BOOKS ---");
        for (Book book : library.getBooks()) {
            book.displayInfo();
            System.out.println();
        }

        // Search for a book
        System.out.println("--- SEARCH BOOK ---");
        Book result = library.searchBook("Noli Me Tangere");

        if (result != null) {
            result.displayInfo();
        } else {
            System.out.println("Book not found.");
        }

        // Update a book
        System.out.println("\n--- UPDATE BOOK ---");
        boolean updated = library.updateBook(
                "B001", "Noli Me Tangere (Updated)", "Jose Rizal");

        System.out.println(updated
                ? "Book updated successfully."
                : "Book not found.");

        // Delete a book
        System.out.println("\n--- DELETE BOOK ---");
        boolean deleted = library.deleteBook("B002");

        System.out.println(deleted
                ? "Book deleted successfully."
                : "Book not found.");

        // Display remaining books
        System.out.println("\n--- REMAINING BOOKS ---");
        for (Book book : library.getBooks()) {
            book.displayInfo();
            System.out.println();
        }
    }
}

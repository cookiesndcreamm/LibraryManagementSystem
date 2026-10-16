
import javax.swing.*;
import java.awt.*;

public class LibraryGUI extends JFrame {
        // Pink and Purple Theme
    private final Color PURPLE = new Color(109, 58, 199);
    private final Color PINK = new Color(244, 184, 208);
    private final Color LAVENDER = new Color(248, 245, 255);
    private final Color WHITE = Color.WHITE;

    private JTextField idField;
    private JTextField titleField;
    private JTextField authorField;
    private Library library = new Library();

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(LAVENDER);

        JLabel heading = new JLabel(
                "LIBRARY MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(PURPLE);
heading.setBorder(
        BorderFactory.createEmptyBorder(20, 10, 20, 10)
);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
inputPanel.setBackground(WHITE);
inputPanel.setBorder(
        BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PINK, 2),
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        )
);

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();

        JLabel idLabel = new JLabel("Book ID:");
JLabel titleLabel = new JLabel("Book Title:");
JLabel authorLabel = new JLabel("Author:");

idLabel.setForeground(PURPLE);
titleLabel.setForeground(PURPLE);
authorLabel.setForeground(PURPLE);

inputPanel.add(idLabel);
inputPanel.add(idField);

inputPanel.add(titleLabel);
inputPanel.add(titleField);

inputPanel.add(authorLabel);
inputPanel.add(authorField);
        JLabel message = new JLabel(
                "Enter the book information below.",
                SwingConstants.CENTER
        );

        
JPanel buttonPanel = new JPanel(new FlowLayout());

JButton addButton = new JButton("Add Book");
JButton viewButton = new JButton("View Books");
JButton searchButton = new JButton("Search Book");
JButton updateButton = new JButton("Update Book");
JButton deleteButton = new JButton("Delete Book");
JButton borrowButton = new JButton("Borrow Book");
JButton returnButton = new JButton("Return Book");

// Button styling
JButton[] buttons = {
    addButton, viewButton, searchButton, updateButton,
    deleteButton, borrowButton, returnButton
};

for (JButton button : buttons) {
    button.setFocusPainted(false);
    button.setBackground(PURPLE);
    button.setForeground(WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 12));
    button.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PINK, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        )
    );
}

buttonPanel.add(addButton);
buttonPanel.add(viewButton);
buttonPanel.add(searchButton);
buttonPanel.add(updateButton);
buttonPanel.add(deleteButton);
buttonPanel.add(borrowButton);
buttonPanel.add(returnButton);

viewButton.addActionListener(e -> {
    java.util.ArrayList<Book> books = library.getBooks();

    if (books.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "No books available in the library.",
                "View Books",
                JOptionPane.INFORMATION_MESSAGE
        );
        return;
    }

    StringBuilder bookList = new StringBuilder();

    for (Book book : books) {
        bookList.append("Book ID: ")
                .append(book.getBookId())
                .append("\nTitle: ")
                .append(book.getTitle())
                .append("\nAuthor: ")
                .append(book.getAuthor())
                .append("\nStatus: ")
                .append(book.isAvailable() ? "Available" : "Borrowed")
                .append("\n-------------------------\n");
    }

    JTextArea textArea = new JTextArea(bookList.toString());
    textArea.setEditable(false);
    textArea.setCaretPosition(0);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(400, 300));

    JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "All Library Books",
            JOptionPane.INFORMATION_MESSAGE
    );
});

searchButton.addActionListener(e -> {
    String keyword = JOptionPane.showInputDialog(
            this,
            "Enter Book ID, title, or author:",
            "Search Book",
            JOptionPane.QUESTION_MESSAGE
    );

    if (keyword == null) {
        return;
    }

    keyword = keyword.trim();

    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a search keyword.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    Book book = library.searchBook(keyword);

    if (book != null) {
        String status = book.isAvailable()
                ? "Available"
                : "Borrowed";

        JOptionPane.showMessageDialog(
                this,
                "Book ID: " + book.getBookId()
                        + "\nTitle: " + book.getTitle()
                        + "\nAuthor: " + book.getAuthor()
                        + "\nStatus: " + status,
                "Book Found",
                JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JOptionPane.showMessageDialog(
                this,
                "Book not found.",
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
});

updateButton.addActionListener(e -> {
    String id = JOptionPane.showInputDialog(
            this,
            "Enter the Book ID to update:"
    );

    if (id == null) {
        return;
    }

    id = id.trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Book ID cannot be empty.");
        return;
    }

    Book book = library.searchBook(id);

    if (book == null
            || !book.getBookId().equalsIgnoreCase(id)) {
        JOptionPane.showMessageDialog(this, "Book ID not found.");
        return;
    }

    String newTitle = JOptionPane.showInputDialog(
            this, "Enter the new title:", book.getTitle()
    );

    if (newTitle == null) {
        return;
    }

    String newAuthor = JOptionPane.showInputDialog(
            this, "Enter the new author:", book.getAuthor()
    );

    if (newAuthor == null) {
        return;
    }

    newTitle = newTitle.trim();
    newAuthor = newAuthor.trim();

    if (newTitle.isEmpty() || newAuthor.isEmpty()) {
        JOptionPane.showMessageDialog(
                this, "Title and author cannot be empty."
        );
        return;
    }

    if (library.updateBook(id, newTitle, newAuthor)) {
        JOptionPane.showMessageDialog(
                this, "Book updated successfully!"
        );
    } else {
        JOptionPane.showMessageDialog(
                this, "Unable to update book."
        );
    }
});

// ADD THE ADD BOOK ACTION HERE
addButton.addActionListener(e -> {
    String id = idField.getText().trim();
    String title = titleField.getText().trim();
    String author = authorField.getText().trim();

    if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Please fill in all book information.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    Book book = new Book(id, title, author);

    if (library.addBook(book)) {
        JOptionPane.showMessageDialog(this, "Book added successfully!");
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
    } else {
        JOptionPane.showMessageDialog(
                this,
                "This Book ID already exists.",
                "Duplicate Book ID",
                JOptionPane.WARNING_MESSAGE
        );
    }
});

deleteButton.addActionListener(e -> {
    String id = JOptionPane.showInputDialog(
            this,
            "Enter the Book ID to delete:",
            "Delete Book",
            JOptionPane.QUESTION_MESSAGE
    );

    if (id == null) {
        return;
    }

    id = id.trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Book ID cannot be empty.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Book ID: " + id + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    if (library.deleteBook(id)) {
        JOptionPane.showMessageDialog(
                this,
                "Book deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JOptionPane.showMessageDialog(
                this,
                "Book ID not found.",
                "Delete Result",
                JOptionPane.WARNING_MESSAGE
        );
    }
});

borrowButton.addActionListener(e -> {
    String id = JOptionPane.showInputDialog(
            this,
            "Enter the Book ID to borrow:",
            "Borrow Book",
            JOptionPane.QUESTION_MESSAGE
    );

    if (id == null) {
        return;
    }

    id = id.trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Book ID cannot be empty.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    if (library.borrowBook(id)) {
        JOptionPane.showMessageDialog(
                this,
                "Book borrowed successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JOptionPane.showMessageDialog(
                this,
                "Book not found or already borrowed.",
                "Borrow Book",
                JOptionPane.WARNING_MESSAGE
        );
    }
});

returnButton.addActionListener(e -> {
    String id = JOptionPane.showInputDialog(
            this,
            "Enter the Book ID to return:",
            "Return Book",
            JOptionPane.QUESTION_MESSAGE
    );

    if (id == null) {
        return;
    }

    id = id.trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Book ID cannot be empty.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    if (library.returnBook(id)) {
        JOptionPane.showMessageDialog(
                this,
                "Book returned successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JOptionPane.showMessageDialog(
                this,
                "Book not found or it has not been borrowed.",
                "Return Book",
                JOptionPane.WARNING_MESSAGE
        );
    }
});


JPanel centerPanel = new JPanel(new BorderLayout());
centerPanel.add(inputPanel, BorderLayout.NORTH);
centerPanel.add(buttonPanel, BorderLayout.CENTER);

setLayout(new BorderLayout(10, 20));
add(heading, BorderLayout.NORTH);
add(centerPanel, BorderLayout.CENTER);
add(message, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI window = new LibraryGUI();
            window.setVisible(true);
        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.JTableHeader;



// Book Class
class Book {
    
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }
    
    

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }
}

// Library Class
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public boolean borrowBook(int index) {
        if (index < 0 || index >= books.size()) return false;

        Book book = books.get(index);
        if (book.isBorrowed()) return false;
        book.borrowBook();
        return true;
    }

    public boolean returnBook(int index) {
        if (index < 0 || index >= books.size()) return false;

        Book book = books.get(index);
        if (!book.isBorrowed()) return false;
        book.returnBook();
        return true;
    }
}
public class ModernLibraryGUI {
    private JFrame frame;
    private Library library = new Library();
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public ModernLibraryGUI() {
        showLoginDialog();
    }

    private void showLoginDialog() {
        // Login Panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(92, 107, 156), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        usernameLabel.setForeground(new Color(41, 50, 65));
        JTextField usernameField = new JTextField(24);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 24));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        loginPanel.add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        passwordLabel.setForeground(new Color(41, 50, 65));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        loginPanel.add(passwordField, gbc);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(Color.WHITE);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(152, 177, 200));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        loginButton.setFocusPainted(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(0, 0, 0));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        exitButton.setFocusPainted(false);

        buttonsPanel.add(exitButton);
        buttonsPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(buttonsPanel, gbc);

        // Login Dialog
        JDialog loginDialog = new JDialog((Frame) null, "Login", true);
        loginDialog.setSize(500, 300);
        loginDialog.setResizable(false);
        loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loginDialog.setLocationRelativeTo(null);
        loginDialog.setContentPane(loginPanel);

        // Button Listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Hardcoded credentials for simplicity
            if (username.equals("admin") && password.equals("password")) {
                loginDialog.dispose();
                initializeUI();
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        loginDialog.setVisible(true);
    }
    
   private JTextField searchField;
private JButton searchButton;

private void initializeUI() {
    frame = new JFrame("Modern Library Management System");
    frame.setSize(1550, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    // Header Panel
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    headerPanel.setBackground(new Color(152, 177, 200));
    JLabel headerLabel = new JLabel("Library Management System");
    headerLabel.setForeground(Color.WHITE);
    headerLabel.setFont(new Font("Arial", Font.BOLD, 40));
    headerPanel.add(headerLabel);

    // Search Bar
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    searchField = new JTextField(30);
    searchField.setFont(new Font("Arial", Font.PLAIN, 20));
    searchButton = new JButton("Search");
    searchButton.setFont(new Font("Arial", Font.BOLD, 20));
    searchButton.setForeground(Color.WHITE);
    searchButton.setBackground(new Color(152, 177, 200));

    searchPanel.add(new JLabel("Search:"));
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Main Content Panel
    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    contentPanel.setBackground(Color.white);

    // Book Table
    String[] columnNames = {"Book Title", "Author", "Status"};
    tableModel = new DefaultTableModel(columnNames, 0);
    bookTable = new JTable(tableModel);
    JTableHeader header = bookTable.getTableHeader();
    header.setFont(new Font("Arial", Font.BOLD, 24));
    bookTable.setFont(new Font("Arial", Font.PLAIN, 24));
    bookTable.setRowHeight(38);
    bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(bookTable);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Available Books"));
    scrollPane.setBackground(Color.white);

    contentPanel.add(searchPanel, BorderLayout.NORTH);
    contentPanel.add(scrollPane, BorderLayout.CENTER);

    // Buttons Panel
    JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
    buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

    JButton addBookButton = createButton("Add Book", new Color(152, 177, 200));
    JButton borrowBookButton = createButton("Borrow Book", new Color(152, 177, 200));
    JButton returnBookButton = createButton("Return Book", new Color(152, 177, 200));
    JButton receiptButton = createButton("Receipt", new Color(152, 177, 200));
    JButton logoutButton = createButton("Logout", new Color(152, 177, 200));

    addBookButton.addActionListener(e -> showAddBookDialog());
    borrowBookButton.addActionListener(e -> borrowSelectedBook());
    returnBookButton.addActionListener(e -> returnSelectedBook());
    receiptButton.addActionListener(e -> showFeeSystem());
    logoutButton.addActionListener(e -> {
        frame.dispose();
        showLoginDialog();
    });

    buttonPanel.add(addBookButton);
    buttonPanel.add(borrowBookButton);
    buttonPanel.add(returnBookButton);
    buttonPanel.add(receiptButton);
    buttonPanel.add(logoutButton);

    contentPanel.add(buttonPanel, BorderLayout.SOUTH);

    // Footer Panel
    JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    footerPanel.setBackground(new Color(0, 0, 0));
    JLabel footerLabel = new JLabel("Modern Library System © 2025");
    footerLabel.setForeground(Color.WHITE);
    footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    footerPanel.add(footerLabel);

    // Add Panels to Frame
    frame.add(headerPanel, BorderLayout.NORTH);
    frame.add(contentPanel, BorderLayout.CENTER);
    frame.add(footerPanel, BorderLayout.SOUTH);

    frame.setVisible(true);

    // Add search functionality
    searchButton.addActionListener(e -> searchBooks(searchField.getText()));
}

// Method to search for books
private void searchBooks(String query) {
    tableModel.setRowCount(0); // Clear the table
    for (Book book : library.getBooks()) {
        if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
            book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
            String status = book.isBorrowed() ? "Borrowed" : "Available";
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), status});
        }
    }
}

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void showAddBookDialog() {
        // Custom Dialog Panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(92, 107, 156), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label and TextField
        JLabel titleLabel = new JLabel("Book Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 50, 65));

        JTextField titleField = new JTextField(30); // Increased size of text field
        titleField.setFont(new Font("Arial", Font.PLAIN, 24));
        titleField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1; // Allow the text field to take more space
        panel.add(titleField, gbc);

        // Author Label and TextField
        JLabel authorLabel = new JLabel("Book Author:");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        authorLabel.setForeground(new Color(41, 50, 65));

        JTextField authorField = new JTextField(30); // Increased size of text field
        authorField.setFont(new Font("Arial", Font.PLAIN, 24));
        authorField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Reset weight for label
        panel.add(authorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1; // Allow the text field to take more space
        panel.add(authorField, gbc);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(new Color(250, 250, 250));

        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(152, 177, 200));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        addButton.setFocusPainted(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(0, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        cancelButton.setFocusPainted(false);

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(addButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonsPanel, gbc);

        // Custom Dialog
        JDialog dialog = new JDialog(frame, "Add New Book", true);
        dialog.setSize(500, 300); // Adjusted dialog size to fit wider fields
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(frame);
        dialog.setContentPane(panel);

        // Button Listeners
        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            if (!title.isEmpty() && !author.isEmpty()) {
                library.addBook(title, author);
                updateBookTable();
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

     private void showFeeSystem() {
        FeeSystem feeSystem = new FeeSystem();
        feeSystem.setVisible(true);
    }
    
    
     
    private void borrowSelectedBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0 && library.borrowBook(selectedRow)) {
            updateBookTable();
        } else {
            JOptionPane.showMessageDialog(frame, "This book is already borrowed or not available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnSelectedBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0 && library.returnBook(selectedRow)) {
            updateBookTable();
        } else {
            JOptionPane.showMessageDialog(frame, "This book is not borrowed or not available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBookTable() {
        tableModel.setRowCount(0);  // Clear existing rows
        for (Book book : library.getBooks()) {
            String status = book.isBorrowed() ? "Borrowed" : "Available";
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), status});
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModernLibraryGUI libraryGUI = new ModernLibraryGUI();
            libraryGUI.library.addBook("The Great Gatsby", "F. Scott Fitzgerald");
            libraryGUI.library.addBook("Moby Dick", "Herman Melville");
            libraryGUI.library.addBook("Harry Potter", "J.K Rowlings");
            libraryGUI.library.addBook("The Lord Of The Rings", "J.R.R Tolkien");
            libraryGUI.library.addBook("The Hobbit", "J.R.R Tolkien");
            libraryGUI.library.addBook("War and Peace", "Leo Tolstoy");
            libraryGUI.library.addBook("The Catcher In The Eye", "J.D Salinger");
            libraryGUI.library.addBook("To Kill A Mockingbird", "Harper Lee");
            libraryGUI.library.addBook("Crime And Punishment", "Fyodor Dostoesky");
            libraryGUI.library.addBook("The Women", "Kristin Hannah");
            libraryGUI.library.addBook("Iron Flame", "Rebecca Yarros");
            libraryGUI.library.addBook("Quicksilver", "Callie Hart");
            libraryGUI.library.addBook("Haunting Adeline", "H. D. Carlton");
            libraryGUI.library.addBook("The Frozen River", "Ariel Lawhon");
            libraryGUI.library.addBook("Lights Out", "Navessa Allen");
            libraryGUI.library.addBook("The Little Prince", "Antoine de Saint-Exupery");
            libraryGUI.library.addBook("One of Us is Lying", "Karen M. McManus");
            libraryGUI.updateBookTable();
        });
    }

    private void showReceiptButton() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private JButton createbutton(String logout) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
} 
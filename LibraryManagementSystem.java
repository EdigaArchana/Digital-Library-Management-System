import java.util.*;

class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availability;

    // Constructor to initialize book details
    public Book(String id, String title, String author, String genre, String availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Getters to retrieve book details
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getAvailability() { return availability; }

    // Setters to update book details
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailability(String availability) { this.availability = availability; }

    // String representation of a book
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + availability;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    // Adds a book to the library collection
    public void addBook(Book book) {
        if (isDuplicateId(book.getId())) {
            System.out.println("Error: Book ID must be unique.");
            return;
        }
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            System.out.println("Error: Title and Author cannot be empty.");
            return;
        }
        if (!book.getAvailability().equalsIgnoreCase("Available") && !book.getAvailability().equalsIgnoreCase("Checked Out")) {
            System.out.println("Error: Availability must be either 'Available' or 'Checked Out'.");
            return;
        }
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Checks if a book ID already exists in the collection
    public boolean isDuplicateId(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    // Displays all books in the library
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Searches for a book by ID or title
    public Book searchBook(String identifier) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(identifier) || book.getTitle().equalsIgnoreCase(identifier)) {
                return book;
            }
        }
        return null;
    }

    // Updates an existing book's details
    public void updateBook(String id, String title, String author, String genre, String availability) {
        Book book = searchBook(id);
        if (book != null) {
            if (title.isEmpty() || author.isEmpty()) {
                System.out.println("Error: Title and Author cannot be empty.");
                return;
            }
            if (!availability.equalsIgnoreCase("Available") && !availability.equalsIgnoreCase("Checked Out")) {
                System.out.println("Error: Availability must be either 'Available' or 'Checked Out'.");
                return;
            }
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setAvailability(availability);
            System.out.println("Book details updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Removes a book from the library
    public void deleteBook(String id) {
        Book book = searchBook(id);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
}

public class LibraryManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            // Display menu options
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Handle user menu selection
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Collects user input and adds a new book to the library
    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Availability (Available/Checked Out): ");
        String availability = scanner.nextLine();
        library.addBook(new Book(id, title, author, genre, availability));
    }

    // Searches for a book by ID or title
    private static void searchBook() {
        System.out.print("Enter Book ID or Title: ");
        String identifier = scanner.nextLine();
        Book book = library.searchBook(identifier);
        System.out.println(book != null ? book : "Book not found.");
    }

    // Collects user input to update an existing book's details
    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter New Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter New Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter New Availability (Available/Checked Out): ");
        String availability = scanner.nextLine();
        library.updateBook(id, title, author, genre, availability);
    }

     // Removes a book from the library
    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String id = scanner.nextLine();
        library.deleteBook(id);
    }
}

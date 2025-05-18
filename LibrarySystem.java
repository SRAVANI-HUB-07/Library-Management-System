import java.util.*;

class Book {
    String id;
    String title;
    String author;
    String genre;
    String publisher;
    int quantity;
    Date issueDate;

    Book(String id, String title, String author, String genre, String publisher, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public boolean matches(String keyword) {
        return id.equalsIgnoreCase(keyword) ||
               title.equalsIgnoreCase(keyword) ||
               author.equalsIgnoreCase(keyword) ||
               genre.equalsIgnoreCase(keyword) ||
               publisher.equalsIgnoreCase(keyword);
    }

    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Genre: " + genre + ", Publisher: " + publisher +
               ", Quantity: " + quantity;
    }
}

public class LibrarySystem {
    static HashMap<String, Book> books = new HashMap<>();
    static HashMap<String, Date> issuedBooks = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Check Book Quantity");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1: addBook(); break;
                case 2: displayBooks(); break;
                case 3: searchBook(); break;
                case 4: updateBook(); break;
                case 5: deleteBook(); break;
                case 6: issueBook(); break;
                case 7: returnBook(); break;
                case 8: checkQuantity(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    static void addBook() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        if (books.containsKey(id)) {
            System.out.print("Book already exists. Enter additional quantity: ");
            int addQty = scanner.nextInt();
            scanner.nextLine();
            books.get(id).quantity += addQty;
            System.out.println("Quantity updated.");
            return;
        }
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        books.put(id, new Book(id, title, author, genre, publisher, quantity));
        System.out.println("Book added successfully.");
    }

    static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    static void searchBook() {
        System.out.print("Enter keyword to search (ID, title, author, genre, publisher): ");
        String keyword = scanner.nextLine();
        boolean found = false;
        for (Book book : books.values()) {
            if (book.matches(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String id = scanner.nextLine();
        if (!books.containsKey(id)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(id);
        boolean continueUpdating = true;

        while (continueUpdating) {
            System.out.println("Which property would you like to update? (title/author/genre/publisher/quantity)");
            String field = scanner.nextLine().toLowerCase();

            switch (field) {
                case "title":
                    System.out.print("Enter new title: ");
                    book.title = scanner.nextLine();
                    break;
                case "author":
                    System.out.print("Enter new author: ");
                    book.author = scanner.nextLine();
                    break;
                case "genre":
                    System.out.print("Enter new genre: ");
                    book.genre = scanner.nextLine();
                    break;
                case "publisher":
                    System.out.print("Enter new publisher: ");
                    book.publisher = scanner.nextLine();
                    break;
                case "quantity":
                    System.out.print("Enter new quantity: ");
                    book.quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    break;
                default:
                    System.out.println("Invalid property. Try again.");
            }

            System.out.print("Do you want to update another property? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                continueUpdating = false;
            }
        }

        System.out.println("Book updated successfully.");
    }

    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String id = scanner.nextLine();
        if (books.remove(id) != null) {
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String id = scanner.nextLine();
        Book book = books.get(id);
        if (book != null && book.quantity > 0) {
            book.quantity--;
            book.issueDate = new Date();
            issuedBooks.put(id, book.issueDate);
            System.out.println("Book issued on: " + book.issueDate);
            if (book.quantity == 0) {
                System.out.println("Book quantity reached 0. Removing from library.");
                books.remove(id);
            }
        } else {
            System.out.println("Book not available.");
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String id = scanner.nextLine();
        Date issueDate = issuedBooks.get(id);
        if (issueDate != null) {
            Date returnDate = new Date();
            long diff = returnDate.getTime() - issueDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            double lateFee = days > 7 ? (days - 7) * 2.0 : 0.0;

            Book book = books.get(id);
            if (book == null) {
                System.out.println("Book previously removed from library (quantity zero). Adding it back.");
                book = new Book(id, "Unknown", "Unknown", "Unknown", "Unknown", 1);
                books.put(id, book);
            } else {
                book.quantity++;
            }

            issuedBooks.remove(id);
            System.out.println("Book returned.");
            System.out.println("Days borrowed: " + days);
            System.out.println("Late fee: $" + lateFee);
        } else {
            System.out.println("This book wasn't issued or already returned.");
        }
    }

    static void checkQuantity() {
        System.out.print("Enter Book ID to check quantity: ");
        String id = scanner.nextLine();
        Book book = books.get(id);
        if (book != null) {
            System.out.println("Quantity of Book ID " + id + ": " + book.quantity);
        } else {
            System.out.println("Book not found.");
        }
    }
}


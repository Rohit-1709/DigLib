package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String availability;

    public Book(String bookId, String title, String author, String genre, String availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailability() {
        return availability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availability;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(String bookId, String title, String author, String genre, String availability) {
        if (books.stream().anyMatch(book -> book.getBookId().equals(bookId))) {
            System.out.println("Error: Book ID must be unique.");
            return;
        }
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Error: Title and Author cannot be empty.");
            return;
        }
        if (!availability.equals("Available") && !availability.equals("Checked Out")) {
            System.out.println("Error: Availability must be 'Available' or 'Checked Out'.");
            return;
        }
        Book newBook = new Book(bookId, title, author, genre, availability);
        books.add(newBook);
        System.out.println("Book added successfully.");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String searchTerm) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookId().equals(searchTerm) || book.getTitle().equalsIgnoreCase(searchTerm)) {
                foundBooks.add(book);
            }
        }
        if (foundBooks.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : foundBooks) {
            System.out.println(book);
        }
    }

    public void updateBook(String bookId, String title, String author, String genre, String availability) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                if (!title.isEmpty()) book.setTitle(title);
                if (!author.isEmpty()) book.setAuthor(author);
                if (!genre.isEmpty()) book.setGenre(genre);
                if (availability.equals("Available") || availability.equals("Checked Out")) {
                    book.setAvailability(availability);
                }
                System.out.println("Book details updated successfully.");
                return;
            }
        }
        System.out.println("Error: Book not found.");
    }

    public void deleteBook(String bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId().equals(bookId)) {
                books.remove(i);
                System.out.println("Book deleted successfully.");
                return;
            }
        }
        System.out.println("Error: Book not found.");
    }
}

public class DigitalLibrary {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book Record");
            System.out.println("6. Exit System");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Availability (Available/Checked Out): ");
                    String availability = scanner.nextLine();
                    library.addBook(bookId, title, author, genre, availability);
                    break;

                case "2":
                    library.viewAllBooks();
                    break;

                case "3":
                    System.out.print("Enter Book ID or Title: ");
                    String searchTerm = scanner.nextLine();
                    library.searchBook(searchTerm);
                    break;

                case "4":
                    System.out.print("Enter Book ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter new Title (leave blank to keep current): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new Author (leave blank to keep current): ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new Genre (leave blank to keep current): ");
                    String newGenre = scanner.nextLine();
                    System.out.print("Enter new Availability (Available/Checked Out, leave blank to keep current): ");
                    String newAvailability = scanner.nextLine();
                    library.updateBook(updateId, newTitle, newAuthor, newGenre, newAvailability);
                    break;

                case "5":
                    System.out.print("Enter Book ID to delete: ");
                    String deleteId = scanner.nextLine();
                    library.deleteBook(deleteId);
                    break;

                case "6":
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
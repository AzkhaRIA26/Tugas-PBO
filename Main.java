import java.util.ArrayList;
import java.util.Scanner;

class User {
    public void displayBooks(Book[] bookList) {
        System.out.println("\nAvailable Books:");
        for (Book book : bookList) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Category: " + book.getCategory());
            System.out.println("ID: " + book.getBookId());
            System.out.println("Stock: " + book.getStock());
            System.out.println("----------------------");
        }
    }
}

class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int stock;
    private int duration;

    public Book(String bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = 0; // Initialize stock to 0
        this.duration = 0; // Initialize duration to 0
    }

    // Getters and setters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

class HistoryBook extends Book {
    public HistoryBook(String bookId, String title, String author, String category) {
        super(bookId, title, author, category);
    }
}

class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, String category) {
        super(bookId, title, author, category);
    }
}

class TextBook extends Book {
    public TextBook(String bookId, String title, String author, String category) {
        super(bookId, title, author, category);
    }
}

class Student extends User {
    private String name;
    private String nim;
    private String faculty;
    private String program;
    private ArrayList<Book> borrowedBooks;

    public Student(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
        this.borrowedBooks = new ArrayList<>();
    }
    public String getNim() {
        return nim;
    }
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Program: " + program);
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Category: " + book.getCategory());
                System.out.println("ID: " + book.getBookId());
                System.out.println("Duration: " + book.getDuration() + " days");
                System.out.println("----------------------");
            }
        }
    }

    public void logout(Book[] bookList) {
        if (!borrowedBooks.isEmpty()) {
            System.out.println("You have borrowed books.");
            System.out.println("1. Cancel borrowing");
            System.out.println("2. Proceed to borrow");
            System.out.print("Choose option: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                borrowedBooks.clear();
            } else if (option == 2) {
                borrowBooks(bookList);
            } else {
                System.out.println("Invalid option. Logging out.");
            }
        } else {
            System.out.println("Logging out from student account.");
        }
    }

    @Override
    public void displayBooks(Book[] bookList) {
        super.displayBooks(bookList);
        borrowBooks(bookList);
    }

    public void borrowBooks(Book[] bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the book you want to borrow: ");
        String bookId = scanner.next();
        System.out.print("Enter duration (in days): ");
        int duration = scanner.nextInt();
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                if (book.getStock() > 0) {
                    book.setStock(book.getStock() - 1);
                    book.setDuration(duration);
                    borrowedBooks.add(book);
                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("Sorry, the book is out of stock.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void returnBooks(Book[] bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the book you want to return: ");
        String bookId = scanner.next();
        for (Book book : borrowedBooks) {
            if (book.getBookId().equals(bookId)) {
                book.setStock(book.getStock() + 1);
                System.out.println("Book returned successfully!");
                borrowedBooks.remove(book);
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found in your borrowed list.");
    }
}

class Admin extends User {
    private ArrayList<Student> studentList;

    public Admin() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input student name: ");
        String name = scanner.nextLine();
        System.out.print("Input student NIM (15 digits): ");
        String nim = scanner.nextLine();
        while (nim.length() != 15) {
            System.out.println("NIM must be 15 digits.");
            System.out.print("Input student NIM (15 digits): ");
            nim = scanner.nextLine();
        }
        System.out.print("Input student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Input student program: ");
        String program = scanner.nextLine();
        Student newStudent = new Student(name, nim, faculty, program);
        studentList.add(newStudent);
        System.out.println("Student added successfully!");
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public void inputBook(Book[] bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select book category:");
        System.out.println("1. History Book");
        System.out.println("2. Story Book");
        System.out.println("3. Text Book");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        String category;
        switch (choice) {
            case 1:
                category = "History";
                break;
            case 2:
                category = "Story";
                break;
            case 3:
                category = "Text";
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                return;
        }
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        // Generate unique book ID
        String bookId = generateId(title, author);
        // Create book object based on category
        switch (choice) {
            case 1:
                HistoryBook historyBook = new HistoryBook(bookId, title, author, category);
                historyBook.setStock(stock);
                bookList[0] = historyBook;
                break;
            case 2:
                StoryBook storyBook = new StoryBook(bookId, title, author, category);
                storyBook.setStock(stock);
                bookList[1] = storyBook;
                break;
            case 3:
                TextBook textBook = new TextBook(bookId, title, author, category);
                textBook.setStock(stock);
                bookList[2] = textBook;
                break;
        }
        System.out.println("Book added successfully!");
    }

    @Override
    public void displayBooks(Book[] bookList) {
        super.displayBooks(bookList);
    }

    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            System.out.println("\nRegistered Students:");
            for (Student student : studentList) {
                student.displayInfo();
                System.out.println("----------------------");
            }
        }
    }

    public boolean isAdmin(String username, String password) {
        // Add your admin credential verification logic here
        return true; // For demonstration, always return true
    }

    private String generateId(String title, String author) {
        // Generate a unique ID based on title and author
        // For simplicity, you can use a combination of title and author
        return title.substring(0, 3) + author.substring(0, 3); // Example ID generation
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Book[] bookList = new Book[3]; // Array to hold different types of books
    private static Admin admin = new Admin();

    public static void main(String[] args) {
        // Initialize book list with default books
        initializeBooks();

        while (true) {
            menu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    studentLogin();
                    break;
                case 3:
                    System.out.println("Thank you. Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void menu() {
        System.out.println("==== Library System ====");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Student");
        System.out.println("3. Exit");
        System.out.print("Choose option (1-3): ");
    }

    private static void adminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (admin.isAdmin(username, password)) {
            adminMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n==== Admin Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Registered Students");
            System.out.println("4. Display Available Books");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    admin.addStudent();
                    break;
                case 2:
                    admin.inputBook(bookList);
                    break;
                case 3:
                    admin.displayStudents();
                    break;
                case 4:
                    admin.displayBooks(bookList);
                    break;
                case 5:
                    System.out.println("Logging out from admin account.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void studentLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student NIM: ");
        String nim = scanner.nextLine();
        for (Student student : admin.getStudentList()) {
            if (student.getNim().equals(nim)) { // Menggunakan getNim() untuk mendapatkan nim
                studentMenu(student);
                return;
            }
        }
        System.out.println("Student with NIM " + nim + " not found.");
    }


    private static void studentMenu(Student student) {
        while (true) {
            System.out.println("\n==== Student Menu ====");
            System.out.println("1. View Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View Borrowed Books");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    student.displayBooks(bookList);
                    break;
                case 2:
                    student.borrowBooks(bookList);
                    break;
                case 3:
                    student.returnBooks(bookList);
                    break;
                case 4:
                    student.showBorrowedBooks();
                    break;
                case 5:
                    student.logout(bookList);
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }


    private static void initializeBooks() {
        // Initialize book list with default books
        bookList[0] = new HistoryBook("H001", "The History Book", "Historian X", "History");
        bookList[0].setStock(10);
        bookList[1] = new StoryBook("S001", "The Story Book", "Author Y", "Story");
        bookList[1].setStock(8);
        bookList[2] = new TextBook("T001", "The Text Book", "Professor Z", "Text");
        bookList[2].setStock(5);
    }
}

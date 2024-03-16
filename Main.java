import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> bookList = new ArrayList<>();

    static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        // Menambahkan beberapa contoh buku ke dalam bookList
        bookList.add("1,Api,Author 1,5");
        bookList.add("2,Cara dapet jodoh,Author 2,1");
        bookList.add("3,Siomay,Author 3,2");

        Menu();
    }
    public static void Menu() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("==== Library System====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. EXIT");
        System.out.print("Choose option (1/3): ");
        int pilihanUser = keyboard.nextInt();

        switch (pilihanUser) {
            case 1:
                System.out.print("Masukkan NIM:");
                String nim = keyboard.nextLine();

                while (nim.length() != 15) {
                    System.out.println("\tNim Harus 15 Digit!!!");
                    System.out.print("Masukkan NIM Mahasiswa: ");
                    nim = keyboard.nextLine();
                }
                menuStudent();
                break;
            case 2:
                System.out.print("Masukkan username: ");
                String username = keyboard.next();
                System.out.print("Masukkan password: ");
                String password = keyboard.next();
                if (username.equals("admin") && password.equals("admin")) {
                    menuAdmin();
                } else {
                    System.out.print("Tidak berhasil login sebagai Admin!");
                    Menu();
                }
                break;
            case 3:
                System.out.println("adios");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak ada. Harap pilih angka 1-3");
                Menu();
        }
    }

    public static void inputNim() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Masukkan nama Mahasiswa: ");
        String name = keyboard.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = keyboard.nextLine();
        while (nim.length() != 15) {
            System.out.println("Nim Harus 15 Digit!!!");
            System.out.print("Masukkan NIM Mahasiswa: ");
            nim = keyboard.nextLine();
        }
        System.out.print("Masukkan Fakultas Mahasiswa: ");
        String faculty = keyboard.nextLine();
        System.out.print("Masukkan Program Studi Mahasiswa: ");
        String programStudi = keyboard.nextLine();
        Admin.addStudent(name, nim, faculty, programStudi);
        menuAdmin();
    }
    public static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Admin Menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. Display Registered Student");
        System.out.println("3. Logout");
        System.out.print("Choose option (1-3): ");
        int pilihanUser = scanner.nextInt();
        switch (pilihanUser) {
            case 1:
                inputNim();
                break;
            case 2:
                Admin.displayStudent();
                menuAdmin();
                break;
            case 3:
                System.out.println("Logging out from admin account");
                Menu();
                break;
            default:
                System.out.println("Mohon untuk memasukkan angka 1-3");
                menuAdmin();
                break;
        }
    }
    public static void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Student Menu ====");
        System.out.println("1. Buku terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Logout");
        System.out.print("Choose option (1-3): ");
        int pilihanUser = scanner.nextInt();
        switch (pilihanUser) {
            case 1:
                Student.displayBooks();
                menuStudent();
                break;
            case 2:
                System.out.println("Buku berhasil dipinjam!");
                menuStudent();
                break;
            case 3:
                System.out.println("Loging out...");
                Menu();
            default:
                System.out.println("Mohon untuk memasukkan angka 1-3");
                break;
        }
    }

}

class Admin {

    public static void addStudent(String name, String nim, String faculty, String programStudi) {
        Student tambah = new Student(name, nim, faculty, programStudi);
        Main.studentList.add(tambah);
        System.out.println("Data  berhasil ditambahkan.");
    }

    public static void displayStudent() {
        System.out.println("Data Mahasiswa:");
        for (Student student : Main.studentList) {
            System.out.println("\nNama\t: " + student.name);
            System.out.println("NIM\t: " + student.nim);
            System.out.println("Jurusan\t: " + student.faculty);
            System.out.println("Prodi\t: " + student.programStudi);
        }
    }
}


public class Student {
    String name, nim, faculty, programStudi;

    public Student(String name, String nim, String faculty, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public static void displayBooks() {
        System.out.println("==================================================================================================");
        System.out.println("|| No.\t||\tid buku\t||Nama buku\t\t||Author\t||Category\t||\tStock\t||");
        System.out.println("==================================================================================================");

        for (String bookInfo : Main.bookList) {
            System.out.println(bookInfo);
        }

        System.out.println("==================================================================================================");
    }

}

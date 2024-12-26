import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        StudentTree tree = new StudentTree();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int studentNumber = Integer.parseInt(parts[0]);
                String name = parts[1] + " " + parts[2];

                Student student = new Student(studentNumber, name);

                for (int i = 3; i < parts.length; i += 2) {
                    String lessonName = parts[i];
                    int grade = Integer.parseInt(parts[i + 1]);
                    student.addLesson(lessonName, grade);
                }

                tree.insert(student);
            }
        } catch (IOException e) {
            System.err.println("Dosyayı okurken hata ile karşılaşıldı: " + e.getMessage());
        }


        int choice;
        do {
            System.out.println("1. Öğrencileri numaraya göre listeleyin");
            System.out.println("2. Bir öğrenci sil");
            System.out.println("3. Yeni bir öğrenci ekle");
            System.out.println("4. Çık");
            System.out.print("Gitmek istediğiniz sekme: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    tree.inOrderTraversal();
                    System.out.println("Toplam öğrenci sayısı: " + tree.getStudentCount());
                    break;
                case 2:
                    System.out.print("Silinecek öğrencinin numarasını giriniz: ");
                    int numberToDelete = scanner.nextInt();
                    tree.delete(numberToDelete);
                    break;
                case 3:
                    System.out.print("Eklemek istediğiniz öğrencinin numarasını giriniz: ");
                    int newNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Öğrencinin ismini giriniz: ");
                    String newName = scanner.nextLine();
                    Student newStudent = new Student(newNumber, newName);

                    System.out.println("Ders bilgilerini giriniz (Ders adı ve not, bitirmek için 'e'): ");
                    while (true) {
                        System.out.print("Ders adı: ");
                        String lessonName = scanner.nextLine();
                        if (lessonName.equalsIgnoreCase("e")) break;
                        System.out.print("Ders notu: ");
                        int grade = scanner.nextInt();
                        scanner.nextLine();
                        newStudent.addLesson(lessonName, grade);
                    }
                    tree.insert(newStudent);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        } while (choice != 4);

        scanner.close();

    }
}


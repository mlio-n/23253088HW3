class Student implements Comparable<Student> {
    int studentNumber;
    String name;
    LessonNode lessons;

    public Student(int studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.lessons = null;
    }

    public void addLesson(String lessonName, int grade) {
        LessonNode newNode = new LessonNode(lessonName, grade);
        if (lessons == null) {
            lessons = newNode;
        } else {
            LessonNode current = lessons;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public double ortalamaHesapla() {
        if (lessons == null) return 0;
        int total = 0, count = 0;
        LessonNode current = lessons;
        while (current != null) {
            total += current.grade;
            count++;
            current = current.next;
        }
        return count > 0 ? (double) total / count : 0;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.studentNumber, other.studentNumber);
    }

    @Override
    public String toString() {
        return "[" + studentNumber + "] " + name + ", Ortalama: " + ortalamaHesapla();
    }
}

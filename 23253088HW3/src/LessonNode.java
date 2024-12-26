class LessonNode {
    String lessonName;
    int grade;
    LessonNode next;

    public LessonNode(String courseName, int grade) {
        this.lessonName = courseName;
        this.grade = grade;
        this.next = null;
    }
}

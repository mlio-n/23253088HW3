class StudentTree {
    private static class TreeNode {
        Student student;
        TreeNode left, right;

        TreeNode(Student student) {
            this.student = student;
        }
    }

    private TreeNode root;
    private int studentCount = 0;

    public void insert(Student student) {
        root = insertRec(root, student);
        studentCount++;
    }

    private TreeNode insertRec(TreeNode root, Student student) {
        if (root == null) return new TreeNode(student);
        if (student.compareTo(root.student) < 0) root.left = insertRec(root.left, student);
        else root.right = insertRec(root.right, student);
        return root;
    }

    public void inOrderTraversal() {
        inOrderTraversalRec(root);
    }

    private void inOrderTraversalRec(TreeNode root) {
        if (root != null) {
            inOrderTraversalRec(root.left);
            System.out.println(root.student);
            inOrderTraversalRec(root.right);
        }
    }

    public Student search(int studentNumber) {
        return searchRec(root, studentNumber);
    }

    private Student searchRec(TreeNode root, int studentNumber) {
        if (root == null) return null;
        if (studentNumber == root.student.studentNumber) return root.student;
        if (studentNumber < root.student.studentNumber) return searchRec(root.left, studentNumber);
        return searchRec(root.right, studentNumber);
    }

    public void delete(int studentNumber) {
        root = deleteRec(root, studentNumber);
        studentCount--;
    }

    private TreeNode deleteRec(TreeNode root, int studentNumber) {
        if (root == null) return null;
        if (studentNumber < root.student.studentNumber) root.left = deleteRec(root.left, studentNumber);
        else if (studentNumber > root.student.studentNumber) root.right = deleteRec(root.right, studentNumber);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            root.student = findMin(root.right);
            root.right = deleteRec(root.right, root.student.studentNumber);
        }
        return root;
    }

    private Student findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        return root.student;
    }

    public int getStudentCount() {
        return studentCount;
    }
}

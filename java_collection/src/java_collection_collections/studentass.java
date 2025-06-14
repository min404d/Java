package java_collection_collections;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

class Student implements Serializable {
    private String id;
    private String name;
    private Map<String, Integer> subjectMarks;

    public Student(String id, String name, Map<String, Integer> subjectMarks) {
        this.id = id;
        this.name = name;
        this.subjectMarks = subjectMarks;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Map<String, Integer> getSubjectMarks() { return subjectMarks; }

    public int getTotalMarks() {
        return subjectMarks.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getAverageMarks() {
        return subjectMarks.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Subjects:\n");
        subjectMarks.forEach((subject, mark) -> sb.append("  ").append(subject).append(": ").append(mark).append("\n"));
        sb.append("Total Marks: ").append(getTotalMarks()).append("\n");
        sb.append(String.format("Average: %.2f", getAverageMarks()));
        return sb.toString();
    }
}

public class studentass {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();
    private static final Set<String> studentIds = new HashSet<>();
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Student\n2. Search Student\n3. Delete Student\n4. Display All\n5. Save\n6. Load\n7. Exit");
            System.out.print("Choose option: ");
            switch (scanner.nextLine()) {
                case "1" -> addStudent();
                case "2" -> searchStudent();
                case "3" -> deleteStudent();
                case "4" -> displayAll();
                case "5" -> saveToFile();
                case "6" -> loadFromFile();
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine().trim();
        if (!studentIds.add(id)) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Map<String, Integer> subjectMarks = new HashMap<>();
        for (String subject : List.of("Math", "Science", "English")) {
            int mark = -1;
            while (mark < 0 || mark > 100) {
                System.out.print("Enter marks for " + subject + " (0–100): ");
                try {
                    mark = Integer.parseInt(scanner.nextLine());
                    if (mark < 0 || mark > 100) {
                        System.out.println("Invalid range. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Try again.");
                }
            }
            subjectMarks.put(subject, mark);
        }

        Student student = new Student(id, name, subjectMarks);
        students.add(student);

        CompletableFuture.runAsync(() -> {
            System.out.println("\n[Async Result Calculation]");
            System.out.println("Total Marks: " + student.getTotalMarks());
            System.out.printf("Average Marks: %.2f\n", student.getAverageMarks());
        });

        System.out.println("Student added successfully.");
    }

    private static void searchStudent() {
        System.out.print("Enter student name to search: ");
        String name = scanner.nextLine().trim().toLowerCase();
        List<Student> results = students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            results.forEach(s -> {
                System.out.println("\n----------------------------");
                System.out.println(s);
                System.out.println("----------------------------");
            });
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine().trim();
        Optional<Student> toDelete = students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (toDelete.isPresent()) {
            students.remove(toDelete.get());
            studentIds.remove(id);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            students.forEach(s -> {
                System.out.println("\n----------------------------");
                System.out.println(s);
                System.out.println("----------------------------");
            });
        }
    }

    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Students saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Student> loaded = (List<Student>) ois.readObject();
            students.clear();
            students.addAll(loaded);
            studentIds.clear();
            loaded.forEach(s -> studentIds.add(s.getId()));
            System.out.println("Students loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}

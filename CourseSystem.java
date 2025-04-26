import java.util.*;

class Course {
    String code;
    String title;
    String desc;
    int capacity;
    String time;
    ArrayList<String> regStudents = new ArrayList<>();

    Course(String c, String t, String d, int cap, String sched) {
        code = c;
        title = t;
        desc = d;
        capacity = cap;
        time = sched;
    }

    boolean isAvailable() {
        return regStudents.size() < capacity;
    }

    void showDetails() {
        System.out.println(code + " - " + title);
        System.out.println("Desc: " + desc);
        System.out.println("Schedule: " + time);
        System.out.println("Available slots: " + (capacity - regStudents.size()));
        System.out.println();
    }
}

class Student {
    String id;
    String name;
    ArrayList<String> registeredCourses = new ArrayList<>();

    Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class CourseSystem {
    static ArrayList<Course> courses = new ArrayList<>();
    static HashMap<String, Student> students = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Sample Data
        courses.add(new Course("CS101", "Intro to CS", "Basics of programming", 3, "Mon 10am"));
        courses.add(new Course("MA201", "Calculus", "Advanced math", 2, "Wed 12pm"));
        courses.add(new Course("PHY111", "Physics I", "Mechanics and motion", 2, "Fri 9am"));

        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine();
        System.out.print("Enter Name: ");
        String sname = sc.nextLine();

        Student stu = new Student(sid, sname);
        students.put(sid, stu);

        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Show Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. My Courses");
            System.out.println("5. Exit");
            System.out.print("Pick: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showCourses();
                    break;
                case 2:
                    registerCourse(stu);
                    break;
                case 3:
                    dropCourse(stu);
                    break;
                case 4:
                    showStudentCourses(stu);
                    break;
            }
        } while (choice != 5);
    }

    static void showCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course c : courses) {
            c.showDetails();
        }
    }

    static void registerCourse(Student stu) {
        System.out.print("Enter Course Code to Register: ");
        String code = sc.nextLine();

        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                if (c.isAvailable() && !stu.registeredCourses.contains(code)) {
                    c.regStudents.add(stu.id);
                    stu.registeredCourses.add(code);
                    System.out.println("Registered successfully.");
                    return;
                } else {
                    System.out.println("Cannot register (full or already registered).");
                    return;
                }
            }
        }
        System.out.println("Course not found.");
    }

    static void dropCourse(Student stu) {
        System.out.print("Enter Course Code to Drop: ");
        String code = sc.nextLine();

        if (stu.registeredCourses.contains(code)) {
            stu.registeredCourses.remove(code);
            for (Course c : courses) {
                if (c.code.equalsIgnoreCase(code)) {
                    c.regStudents.remove(stu.id);
                    break;
                }
            }
            System.out.println("Course dropped.");
        } else {
            System.out.println("You're not registered in that course.");
        }
    }

    static void showStudentCourses(Student stu) {
        System.out.println("\n--- Your Courses ---");
        if (stu.registeredCourses.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            for (String cid : stu.registeredCourses) {
                System.out.println("- " + cid);
            }
        }
    }
}

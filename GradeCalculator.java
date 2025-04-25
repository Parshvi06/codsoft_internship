import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.print("How many subjects? ");
        int subjects = sc.nextInt();

        int total = 0;
        double average = 0.0;
        char grade = ' ';

        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int marks = sc.nextInt();
            total += marks;
        }

        average = (double) total / subjects;

        // simple grading
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("\n------- RESULT -------");
        System.out.println("Total Marks: " + total + "/" + (subjects * 100));
        System.out.println("Average Percentage: " + String.format("%.2f", average) + "%");
        System.out.println("Grade: " + grade);
    }
}

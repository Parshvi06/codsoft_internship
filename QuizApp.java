import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {

    static Scanner sc = new Scanner(System.in);
    static int score = 0;
    static boolean answered = false;

    public static void main(String[] args) {

        String[] questions = {
            "1. What is the capital of France?",
            "2. Which language is mainly used for Android Development?",
            "3. Who wrote 'Romeo and Juliet'?",
            "4. What is the boiling point of water (in Celsius)?"
        };

        String[][] options = {
            {"a) Paris", "b) Berlin", "c) Madrid", "d) Rome"},
            {"a) Java", "b) Python", "c) Swift", "d) C#"},
            {"a) Charles Dickens", "b) William Shakespeare", "c) Mark Twain", "d) J.K. Rowling"},
            {"a) 100", "b) 90", "c) 80", "d) 70"}
        };

        char[] answers = {'a', 'a', 'b', 'a'};

        System.out.println("----- Welcome to the Quiz -----");
        System.out.println("You have 10 seconds for each question.\n");

        for (int i = 0; i < questions.length; i++) {
            askQuestion(questions[i], options[i], answers[i]);
        }

        System.out.println("\n==== Quiz Over ====");
        System.out.println("Your final score: " + score + "/" + questions.length);
    }

    static void askQuestion(String question, String[] choices, char correctAnswer) {
        answered = false;
        
        System.out.println(question);
        for (String option : choices) {
            System.out.println(option);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up! ⏳ Moving to next question.\n");
                    answered = true;
                }
            }
        }, 10000); // 10 seconds

        String userAnswer = "";

        while (!answered) {
            System.out.print("Your answer (a/b/c/d): ");
            if (sc.hasNext()) {
                userAnswer = sc.next().toLowerCase();
                answered = true;
                timer.cancel();

                if (userAnswer.length() > 0 && userAnswer.charAt(0) == correctAnswer) {
                    System.out.println("Correct! 🎯\n");
                    score++;
                } else {
                    System.out.println("Wrong! ❌\n");
                }
            }
        }
    }
}

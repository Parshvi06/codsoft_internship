import java.util.Scanner;
import java.util.Random;

public class GuessGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int totalWins = 0;
        boolean keepPlaying = true;

        System.out.println("Hey! Let's play a number guessing game!");

        while (keepPlaying) {
            int secret = rand.nextInt(100) + 1;
            int tries = 5;
            boolean guessedRight = false;

            System.out.println("\nI've picked a number from 1 to 100.");
            System.out.println("You have " + tries + " chances. Try to guess it!");

            while (tries > 0) {
                System.out.print("Your guess: ");
                int guess;

                if (!input.hasNextInt()) {
                    System.out.println("Hmm... That doesn't look like a number.");
                    input.next();
                    continue;
                }

                guess = input.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println("Uhh... Stick to 1 - 100 please.");
                    continue;
                }

                tries--;

                if (guess == secret) {
                    System.out.println("Whoa! You got it 🎉");
                    guessedRight = true;
                    totalWins++;
                    break;
                } else if (guess < secret) {
                    System.out.println("Nope! Too low 🔻");
                } else {
                    System.out.println("Oops! Too high 🔺");
                }

                System.out.println("Tries left: " + tries);
            }

            if (!guessedRight) {
                System.out.println("Ahh! You ran out of tries. The number was: " + secret);
            }

            System.out.println("Score so far: " + totalWins);

            System.out.print("Wanna play again? (y/n): ");
            String again = input.next();
            if (!again.equalsIgnoreCase("y")) {
                keepPlaying = false;
            }
        }

        System.out.println("\nAlrighty! You won " + totalWins + " time(s). Thanks for playing!");
        input.close();
    }
}

package oasisinfobyte;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineExaminationSystem {
    private static final HashMap<String, String> users = new HashMap<>(); // username, password
    private static final Map<String, String> profile = new HashMap<>(); // username, name
    private static int timer = 60; // Timer set to 60 seconds for demo

    static {
        // Adding a default user for login
        users.put("nithish", "sai17");
        profile.put("user1", "John Doe");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (login(username, password)) {
            System.out.println("Login successful!");
            boolean sessionActive = true;

            while (sessionActive) {
                System.out.println("\n1. Update Profile");
                System.out.println("2. Take MCQ Exam");
                System.out.println("3. Logout");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        updateProfile(scanner, username);
                        break;
                    case 2:
                        takeExam(scanner);
                        break;
                    case 3:
                        sessionActive = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials.");
        }
        scanner.close();
    }

    private static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void updateProfile(Scanner scanner, String username) {
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        profile.put(username, newName);
        users.put(username, newPassword);
        System.out.println("Profile updated successfully.");
    }

    private static void takeExam(Scanner scanner) {
        String[] questions = {
                "1. What is the capital of France?\n(a) Paris\n(b) Rome\n(c) Madrid\n(d) Berlin",
                "2. Which planet is known as the Red Planet?\n(a) Earth\n(b) Mars\n(c) Jupiter\n(d) Venus"
        };
        String[] answers = {"a", "b"};
        int score = 0;

        System.out.println("Exam has started. You have " + timer + " seconds.");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();

            // Check time limit
            if ((System.currentTimeMillis() - startTime) / 1000 > timer) {
                System.out.println("Time's up! Auto-submitting...");
                break;
            }

            if (answer.equalsIgnoreCase(answers[i])) {
                score++;
            }
        }

        System.out.println("Exam completed. Your score: " + score + "/" + questions.length);
    }
}


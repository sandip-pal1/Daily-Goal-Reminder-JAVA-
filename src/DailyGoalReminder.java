import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DailyGoalReminder {
    private static ArrayList<String> goals = new ArrayList<>();
    private static ArrayList<Boolean> completed = new ArrayList<>();

    // Method to add a goal
    public static void addGoal(String goal) {
        goals.add(goal);
        completed.add(false); // initially not completed
        System.out.println("✅ Goal added: " + goal);
    }

    // Method to show all goals
    public static void showGoals() {
        if (goals.isEmpty()) {
            System.out.println("📌 No goals set for today.");
            return;
        }
        System.out.println("\n--- Today's Goals ---");
        for (int i = 0; i < goals.size(); i++) {
            String status = completed.get(i) ? "✓ Completed" : "⏳ Pending";
            System.out.println((i + 1) + ". " + goals.get(i) + " [" + status + "]");
        }
    }

    // Method to mark a goal as completed
    public static void markCompleted(int index) {
        if (index >= 0 && index < goals.size()) {
            completed.set(index, true);
            System.out.println("🎉 Marked as completed: " + goals.get(index));
        } else {
            System.out.println("❌ Invalid goal number.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Show welcome message with date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = LocalDateTime.now().format(formatter);
        System.out.println("👋 Welcome to Daily Goal Reminder!");
        System.out.println("📅 Current Date & Time: " + currentDateTime);

        while (true) {
            // Menu
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add a new goal");
            System.out.println("2. View today’s goals");
            System.out.println("3. Mark a goal as completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter your goal: ");
                    String goal = scanner.nextLine().trim();
                    if (!goal.isEmpty()) {
                        addGoal(goal);
                    } else {
                        System.out.println("⚠️ Goal cannot be empty!");
                    }
                    break;

                case 2:
                    showGoals();
                    break;

                case 3:
                    showGoals();
                    if (!goals.isEmpty()) {
                        System.out.print("Enter goal number to mark as completed: ");
                        try {
                            int goalNum = Integer.parseInt(scanner.nextLine());
                            markCompleted(goalNum - 1);
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Invalid input. Enter a number.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("👋 Goodbye! Stay productive!");
                    scanner.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Please select 1-4.");
            }
        }
    }
}

package com.company.AdminConsole.Managers;

import java.util.Scanner;

public class MainManager {

    UserManager userManager = new UserManager();
    GroupManager groupManager = new GroupManager();
    SolutionManager solutionManager = new SolutionManager();
    ExerciseManager exerciseManager = new ExerciseManager();

    public void mainManagerMenu() {
        System.out.println("This is main manager.");
        System.out.println("Type one of options below:");
        System.out.println("1 - open user manager" + "\n" +
                "2 - open group manager" + "\n" +
                "3 - open solution manager" + "\n" +
                "4 - open exercise manager" + "\n" +
                "quit - quit application");
        Scanner modeIn = new Scanner(System.in);
        String mode = modeIn.nextLine();
        switch (mode) {
            case "1":
                userManager.userManagementMenu();
                break;
            case "2":
                groupManager.groupManagerMenu();
                break;
            case "3":
                solutionManager.solutionManagementMenu();
                break;
            case "4":
                exerciseManager.exerciseManagerMenu();
                break;
            case "quit":
                quit(modeIn);
                break;
            default:
                System.out.println("This option is not available, please try again.");
                mainManagerMenu();
                break;
        }
    }

    private void quit(Scanner scanner) {
        System.out.println("Turning application off...");
        scanner.close();
    }
}

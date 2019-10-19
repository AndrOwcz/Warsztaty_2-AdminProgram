package com.company.UserConsole.UserManager;

import com.company.AdminConsole.BaseClasses.Exercise;
import com.company.AdminConsole.BaseClasses.Solution;
import com.company.AdminConsole.DAO.ExerciseDao;
import com.company.AdminConsole.DAO.SolutionDao;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserMenu {

    private int userId;

    public UserMenu(int userId) {
        this.userId = userId;
    }

    private SolutionDao solutionDao = new SolutionDao();
    private ExerciseDao exerciseDao = new ExerciseDao();

    public void userMenu() {
        System.out.println("This is user console");
        System.out.println("Type one of options below:");
        System.out.println("add - adding new user" + "\n" +
                "view - shows all users" + "\n" +
                "quit - quit user manager");
        Scanner modeIn = new Scanner(System.in);
        String mode = modeIn.nextLine();
        switch (mode) {
            case "add":
                add(modeIn);
                break;
            case "view":
                view();
                break;
            case "quit":
                quit(modeIn);
                break;
            default:
                System.out.println("This option is not available, please try again.");
                userMenu();
                break;
        }
    }

    private void view() {
        System.out.println("All user solutions:");
        Solution[] solutionsTab = solutionDao.findAllByUserId(userId);
        for (Solution solution : solutionsTab) {
            System.out.println(solution);
        }
        System.out.println();
        userMenu();
    }


    private void add(Scanner scanner) {
        System.out.println("Solution addition mode");

        Exercise[] exercises = exerciseDao.findAll();
        for (int i = 0; i < exercises.length; i++) {
            System.out.println(exercises[i]);
        }

        System.out.println("Type exercise id to add its solution: ");
        int exerciseId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Type solution description: ");
        String description = scanner.nextLine();

        Solution solution = new Solution();
        try {
            solution.setCreated(LocalDateTime.now());
            solution.setUpdated(LocalDateTime.now());
            solution.setExercise_id(exerciseId);
            solution.setUsers_id(userId);
            solutionDao.create(solution);
            solution.setDescription(description);
            solutionDao.update(solution);
            System.out.print("User solution created.\n");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        userMenu();
    }

    private void quit(Scanner scanner) {
        System.out.println("Turning user manager off...");
        scanner.close();
    }
}

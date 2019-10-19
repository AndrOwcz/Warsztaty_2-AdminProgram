package com.company.AdminConsole.Managers;

import com.company.AdminConsole.DAO.ExerciseDao;
import com.company.AdminConsole.DAO.SolutionDao;
import com.company.AdminConsole.DAO.UserDao;
import com.company.AdminConsole.BaseClasses.Exercise;
import com.company.AdminConsole.BaseClasses.Solution;
import com.company.AdminConsole.BaseClasses.User;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SolutionManager {

    private SolutionDao solutionDao = new SolutionDao();
    private UserDao userDao = new UserDao();
    private ExerciseDao exerciseDao = new ExerciseDao();

    public void solutionManagementMenu() {
        System.out.println("This is solution manager");
        System.out.println("Type one of options below:");
        System.out.println("show - shows all solutions" + "\n" +
                "view - views all user solutions" + "\n" +
                "add - adding new solution" + "\n" +
                "edit - edition of solution" + "\n" +
                "delete - deleting solution" + "\n" +
                "quit - quit solution manager");
        Scanner modeIn = new Scanner(System.in);
        String mode = modeIn.nextLine();
        switch (mode) {
            case "show":
                show();
                break;
            case "view":
                view(modeIn);
                break;
            case "add":
                add(modeIn);
                break;
            case "edit":
                edit(modeIn);
                break;
            case "delete":
                delete(modeIn);
                break;
            case "quit":
                quit(modeIn);
                break;
            default:
                System.out.println("This option is not available, please try again.");
                solutionManagementMenu();
                break;
        }
    }

    private void show() {
        System.out.println("Type users:");
        Solution[] solutionsTab = solutionDao.findAll();
        for (Solution solution : solutionsTab) {
            System.out.println(solution);
        }
        System.out.println();
        solutionManagementMenu();
    }

    private void view(Scanner scanner) {
        System.out.println("list of users:");
        User[] users = userDao.findAll();
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }
        System.out.println("Type user id:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        Solution[] solutionsTab = solutionDao.findAllByUserId(userId);
        for (Solution solution : solutionsTab) {
            System.out.println(solution);
        }
        System.out.println();
        solutionManagementMenu();
    }

    private void add(Scanner scanner) {
        System.out.println("Solution addition mode");
        System.out.println("list of users: ");
        User[] users = userDao.findAll();
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }
        System.out.println("Type user id: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Exercise[] exercises = exerciseDao.findAll();
        for (int i = 0; i < exercises.length; i++) {
            System.out.println(exercises[i]);
        }

        System.out.println("Type exercise id:");
        int exerciseId = scanner.nextInt();
        scanner.nextLine();

        Solution solution = new Solution();
        try {
            solution.setCreated(LocalDateTime.now());
            solution.setUpdated(LocalDateTime.now());
            solution.setDescription(null);
            solution.setExercise_id(exerciseId);
            solution.setUsers_id(userId);
            solutionDao.create(solution);
            System.out.print("Solution created.\n");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        solutionManagementMenu();
    }

    private void edit(Scanner scanner) {
        System.out.println("Edition mode");
        System.out.println("Type id of solution you want to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Solution solutionToEdit = solutionDao.read(id);
        if (solutionToEdit != null) {
            System.out.println("Type new description:");
            String newDescription = scanner.nextLine();
            System.out.println("Type new exercise id:");
            int newExerciseId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Type new users id:");
            int newUsersId = scanner.nextInt();
            scanner.nextLine();
            solutionToEdit.setUpdated(LocalDateTime.now());
            solutionToEdit.setDescription(newDescription);
            solutionToEdit.setExercise_id(newExerciseId);
            solutionToEdit.setUsers_id(newUsersId);
            solutionDao.update(solutionToEdit);
            System.out.print("Solution edited.\n");
        } else {
            System.out.println("No such solution to edit.");
        }
        solutionManagementMenu();
    }

    private void delete(Scanner scanner) {
        System.out.println("Solution deletion mode");
        System.out.println("Type id of solution you want to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Solution solutionToDelete = solutionDao.read(id);
        if (solutionToDelete != null) {
            solutionDao.delete(id);
            System.out.println("Solution deleted.");
        } else {
            System.out.println("No such solution to delete.");
        }
        solutionManagementMenu();
    }

    private void quit(Scanner scanner) {
        System.out.println("Turning solution manager off...");
        scanner.close();
    }
}

package com.company.AdminConsole.Managers;

import com.company.AdminConsole.DAO.ExerciseDao;
import com.company.AdminConsole.BaseClasses.Exercise;

import java.util.Scanner;

public class ExerciseManager {

    ExerciseDao exerciseDao = new ExerciseDao();

    public void exerciseManagerMenu() {
        System.out.println("This is exercise manager");
        System.out.println("Type one of options below:");
        System.out.println("show - shows all exercises" + "\n" +
                "add - adding new exercise" + "\n" +
                "edit - edition of exercise" + "\n" +
                "delete - deleting exercise" + "\n" +
                "quit - quit exercise manager");
        Scanner modeIn = new Scanner(System.in);
        String mode = modeIn.nextLine();
        switch (mode) {
            case "show":
                show();
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
                exerciseManagerMenu();
                break;
        }
    }

    private void show() {
        System.out.println("All users in database:");
        Exercise[] exerciseTab = exerciseDao.findAll();
        for (Exercise exercise : exerciseTab) {
            System.out.println(exercise);
        }
        System.out.println();
        exerciseManagerMenu();
    }

    private void add(Scanner scanner) {
        System.out.println("Exercise addition mode");
        System.out.println("Type exercise title:");
        String newTitle = scanner.nextLine();
        System.out.println("Type exercise description:");
        String newDescription = scanner.nextLine();
        Exercise exercise = new Exercise();
        exercise.setTitle(newTitle);
        exercise.setDescription(newDescription);
        exerciseDao.create(exercise);
        System.out.print("Exercise created.\n");

        exerciseManagerMenu();
    }

    private void edit(Scanner scanner) {
        System.out.println("Edition mode");
        System.out.println("Type id of exercise you want to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Exercise exerciseToEdit = exerciseDao.read(id);
        if (exerciseToEdit != null) {
            System.out.println("Type new exercise title:");
            String newTitle = scanner.nextLine();
            System.out.println("Type new exercise description:");
            String newDescription = scanner.nextLine();
            exerciseToEdit.setTitle(newTitle);
            exerciseToEdit.setDescription(newDescription);
            exerciseDao.update(exerciseToEdit);
            System.out.print("Exercise edited.\n");
        } else {
            System.out.println("No such exercise to edit.");
        }
        exerciseManagerMenu();
    }

    private void delete(Scanner scanner) {
        System.out.println("Exercise deletion mode");
        System.out.println("Type id of exercise you want to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Exercise exerciseToDelete = exerciseDao.read(id);
        if (exerciseToDelete != null) {
            exerciseDao.delete(id);
            System.out.println("Exercise deleted.");
        } else {
            System.out.println("No such exercise to delete.");
        }
        exerciseManagerMenu();
    }

    private void quit(Scanner scanner) {
        System.out.println("Turning exercise manager off...");
        scanner.close();
    }
}

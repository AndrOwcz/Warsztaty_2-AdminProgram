package com.company.AdminConsole.Managers;

import com.company.AdminConsole.DAO.GroupDao;
import com.company.AdminConsole.DAO.UserDao;
import com.company.AdminConsole.BaseClasses.Group;
import com.company.AdminConsole.BaseClasses.User;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserManager {

    private UserDao userDao = new UserDao();
    private GroupDao groupDao = new GroupDao();

    public void userManagementMenu() {
        System.out.println("This is user manager");
        System.out.println("Type one of options below:");
        System.out.println("show - shows all users" + "\n" +
                "add - adding new user" + "\n" +
                "edit - edition of user data" + "\n" +
                "assign - assign group to user" + "\n" +
                "remove - remove group from user" + "\n" +
                "delete - deleting user" + "\n" +
                "quit - quit user manager");
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
            case "assign":
                assignGroup(modeIn);
                break;
            case "remove":
                removeGroup(modeIn);
                break;
            case "delete":
                delete(modeIn);
                break;
            case "quit":
                quit(modeIn);
                break;
            default:
                System.out.println("This option is not available, please try again.");
                userManagementMenu();
                break;
        }
    }

    private void show() {
        System.out.println("All users in database:");
        User[] userTab = userDao.findAll();
        for (User user : userTab) {
            System.out.println(user);
        }
        System.out.println();
        userManagementMenu();
    }

    private void add(Scanner scanner) {
        System.out.println("User addition mode");
        System.out.println("Type Username:");
        String newUsername = scanner.nextLine();
        System.out.println("Type email:");
        String newEmail = scanner.nextLine();
        System.out.println("Type password:");
        String newPassword = scanner.nextLine();
        System.out.println("Type User group:");
        int newGroupId = scanner.nextInt();
        User user = new User();
        try {
            user.setUserName(newUsername);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            user.setUser_group_id(newGroupId);
            userDao.create(user);
            System.out.print("User created.\n");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        userManagementMenu();
    }

    private void edit(Scanner scanner) {
        System.out.println("Edition mode");
        System.out.println("Type id of User you want to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();
        User userToEdit = userDao.read(id);
        if (userToEdit != null) {
            System.out.println("Type new Username:");
            String newUsername = scanner.nextLine();
            System.out.println("Type new email:");
            String newEmail = scanner.nextLine();
            System.out.println("Type new password:");
            String newPassword = scanner.nextLine();
            System.out.println("Type new User group:");
            int newGroupId = scanner.nextInt();
            userToEdit.setUserName(newUsername);
            userToEdit.setEmail(newEmail);
            userToEdit.setPassword(newPassword);
            userToEdit.setUser_group_id(newGroupId);
            userDao.update(userToEdit);
            System.out.print("User edited.\n");
        } else {
            System.out.println("No such User to edit.");
        }
        userManagementMenu();
    }

    private void assignGroup(Scanner scanner) {
        System.out.println("Type id of User you want to assign group to:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        User user = userDao.read(userId);
        if (user != null) {
            System.out.println("Type group id to be assigned:");
            int groupId = scanner.nextInt();
            Group group = groupDao.read(groupId);
            if (group != null) {
                userDao.assignGroup(user, group);
                System.out.println("Group assigned to user");
            } else {
                System.out.println("There is no such group");
            }
        } else {
            System.out.println("No such User to assign group");
        }
        userManagementMenu();
    }

    private void removeGroup(Scanner scanner) {
        System.out.println("Type id of User you want to remove group from:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        User user = userDao.read(userId);
        if (user != null) {
            userDao.removeGroup(user);
            System.out.println("Group was removed from user");
        } else {
            System.out.println("No such User to remove group from");
        }
        userManagementMenu();
    }


    private void delete(Scanner scanner) {
        System.out.println("User deletion mode");
        System.out.println("Type id of User you want to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        User userToDelete = userDao.read(id);
        if (userToDelete != null) {
            userDao.delete(id);
            System.out.println("User deleted.");
        } else {
            System.out.println("No such User to delete.");
        }
        userManagementMenu();
    }

    private void quit(Scanner scanner) {
        System.out.println("Turning user manager off...");
        scanner.close();
    }
}

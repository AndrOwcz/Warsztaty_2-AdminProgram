package com.company.AdminConsole.Managers;

import com.company.AdminConsole.DAO.GroupDao;
import com.company.AdminConsole.BaseClasses.Group;

import java.util.Scanner;

public class GroupManager {

    GroupDao groupDao = new GroupDao();

    public void groupManagerMenu() {
        System.out.println("This is group manager");
        System.out.println("Type one of options below:");
        System.out.println("show - shows all groups" + "\n" +
                "add - adding new group" + "\n" +
                "edit - edition of group" + "\n" +
                "delete - deleting group" + "\n" +
                "quit - quit group manager");
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
                groupManagerMenu();
                break;
        }
    }

    private void show() {
        System.out.println("All users in database:");
        Group[] groupTab = groupDao.findAll();
        for (Group group : groupTab) {
            System.out.println(group);
        }
        System.out.println();
        groupManagerMenu();
    }

    private void add(Scanner scanner) {
        System.out.println("Group addition mode");
        System.out.println("Type group title:");
        String newName = scanner.nextLine();
        Group group = new Group();
        group.setName(newName);
        groupDao.create(group);
        System.out.print("Group created.\n");

        groupManagerMenu();
    }

    private void edit(Scanner scanner) {
        System.out.println("Edition mode");
        System.out.println("Type id of group you want to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Group groupToEdit = groupDao.read(id);
        if (groupToEdit != null) {
            System.out.println("Type new group name:");
            String newName = scanner.nextLine();
            groupToEdit.setName(newName);
            groupDao.update(groupToEdit);
            System.out.print("Group edited.\n");
        } else {
            System.out.println("No such group to edit.");
        }
        groupManagerMenu();
    }

    private void delete(Scanner scanner) {
        System.out.println("Group deletion mode");
        System.out.println("Type id of group you want to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Group groupToDelete = groupDao.read(id);
        if (groupToDelete != null) {
            groupDao.delete(id);
            System.out.println("Group deleted.");
        } else {
            System.out.println("No such group to delete.");
        }
        groupManagerMenu();
    }

    private void quit(Scanner scanner) {
        System.out.println("Turning group manager off...");
        scanner.close();
    }
}

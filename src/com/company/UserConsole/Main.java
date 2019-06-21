package com.company.UserConsole;

import com.company.UserConsole.UserManager.UserMenu;

public class Main {
    public static void main(String[] args) {

       // java Main.java 1

        if (args.length > 0) {
            try {
                final String userIdAsString = args[0];
                int userId = Integer.parseInt(userIdAsString);
                System.out.println(userId);
                System.out.println("succeed!!!  --- user id chosen = 1");
                UserMenu menu = new UserMenu(userId);
                menu.userMenu();
            } catch (final NumberFormatException e) {
                System.out.println("Niepoprawne id usera");
                System.exit(-1);

            }
        }
    }
}
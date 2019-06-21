package com.company.AdminConsole;

import com.company.AdminConsole.BaseClasses.Exercise;
import com.company.AdminConsole.BaseClasses.User;
import com.company.AdminConsole.DAO.ExerciseDao;
import com.company.AdminConsole.DAO.GroupDao;
import com.company.AdminConsole.DAO.SolutionDao;
import com.company.AdminConsole.DAO.UserDao;
import com.company.AdminConsole.Managers.MainManager;

public class Main {

    public static void main(String[] args) {

        User user = new User("randomUser", "random@gmail.com", "hasło", 1);
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        SolutionDao solutionDao = new SolutionDao();

//        User[] userTab = solutionDao.findAll();
//        System.out.println(Arrays.toString(userTab));

//        User userToUpdate = userDao.read(1);
//        System.out.println(userToUpdate.getId());

        Exercise exercise = new Exercise("Exercise 3", "exercise 3 desc");
//        groupDao.create(exercise);

//        Exercise exerciseChecked = exerciseDao.read(2);
//        System.out.println(exerciseChecked.getId());

//        exerciseChecked.setDescription("exercise 2 description updated");
//        exerciseDao.update(exerciseChecked);

//        System.out.println(solutionDao.read(1));

//        Solution[] solutions = solutionDao.findAll();
//        System.out.println(Arrays.toString(solutions));

//        Solution[] solutions = solutionDao.findAllExerciseId(2);
//        System.out.println(Arrays.toString(solutions));
//
//        User[] users = userDao.findAll();
//        System.out.println(Arrays.toString(users));
//
//        User userToUpdate = userDao.read(13);
//        Group groupToUpdate = groupDao.read(3);
//        System.out.println(userToUpdate);


//        User[] userTab = userDao.findAllUngrouped();
//        System.out.println(Arrays.toString(userTab));

        MainManager mainManager = new MainManager();
        mainManager.mainManagerMenu();


    }
}

package com.dkit.oop.sd2.BusinessObjects;

/** OOP 2021
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses DAOs, Data Transfer Objects (DTOs), and
 * a DaoInterface to define a contract between Business Objects
 * and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here we use one DAO per database table.
 *
 * Use the SQL script included with this project to create the
 * required MySQL user_database and user table
 */



import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.dkit.oop.sd2.Menus.CAOCourseMenu;
import com.dkit.oop.sd2.Menus.MainMenu;
import com.dkit.oop.sd2.Server.MySqlCourseDao;
import com.dkit.oop.sd2.Server.MySqlStudentDao;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


/*

13579113
123ZXC
 */



public class App {
    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new App().start();
    }
    private HashMap<String, Course> courseMap = new HashMap<>();

    private void start() {
        doMainMenuLoop();
    }
    public void doMainMenuLoop()
    {

        try {

            boolean loop = true;
            MainMenu choice;
            int getChoice;
            while (loop) {
                displayMainMenu();
                getChoice = keyboard.nextInt();
                keyboard.nextLine();
                choice = MainMenu.values()[getChoice];
                switch (choice) {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case REGISTER:
                        register();
                        break;
                    case LOGIN:
                        login();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doAdminMenuLoop(MySqlStudentDao loginResult)
    {
        boolean loop = true;
        CAOCourseMenu menuOption;
        int option;
        while(loop)
        {
            printCaoCourseMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = CAOCourseMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case LOGOUT:
                        doMainMenuLoop();
                        break;
//                    case DISPLAY_COURSE:
//                        getStudentDetails(ccMgr);
//                        break;
//                    case DISPLAY_ALL_COURSES:
//                        displayAllCourses();
//                        break;
//                    case DISPLAY_CURRENT_CHOICES:
//                        addStudent(studentManager);
//                        break;
//                    case UPDATE_CURRENT_CHOICES:
//                        removeStudent(studentManager);
//                        break;
                }
            }
            catch (InputMismatchException ime)
            {
                System.out.println("InputMismatchException. The token retrieved does not match the pattern for the expected type. Please enter a valid option");
                keyboard.nextLine();
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("ArrayIndexOutOfBoundsException. The index is either negative or greater than or equal to the size of the array.");
                keyboard.nextLine();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("IllegalArgumentException. A method has been passed an illegal argument. Try again");
                keyboard.nextLine();
            } 

        }

    }


    private void login() throws DaoException {
        System.out.println("Login: ");

        System.out.println("Enter CAO Number:\t");
        int caoNumber = keyboard.nextInt();
        System.out.println("Enter Password:\t");
        String password = keyboard.next();
        MySqlStudentDao login = new MySqlStudentDao();

//        if(login.login(caoNumber, password))
//        {
//            doAdminMenuLoop(login);
//        }
//        String result2 = login.login(caoNumber, password);
//        System.out.println(result2);


    }

    public void register() throws DaoException {
        System.out.println("");
        System.out.println("Enter CAO Number:");
        int caoNumber = keyboard.nextInt();
        System.out.println("Enter Date of Birth:");
        String dateOfBirth = keyboard.next();
        System.out.println("Enter Password:");
        String password = keyboard.next();
        MySqlStudentDao register = new MySqlStudentDao();
        String result1 = register.register(caoNumber, dateOfBirth, password);
        System.out.println(result1);
    }

    private void displayMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < MainMenu.values().length;i++)
        {
            System.out.println("\t"  + i + ". " + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select option (enter 0 to quit):>");
    }

    private void printCaoCourseMenu()
    {
        System.out.println("CAO Course Menu.");
        System.out.println("\n Select One The Following Options: ");
        for(int i = 0; i < CAOCourseMenu.values().length; i++)
        {
            System.out.println("\t" + i + ". " + CAOCourseMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select option (enter 0 to quit):>");
    }

//        StudentManager studentManager = new StudentManager();
//
//        Student s = studentManager.getStudent(12345678);
//
//        int caoNumber = 87654321;// get from user input
//        String dob = "1999-11-26";
//        String pw = "dfghjk";
//        studentManager.addStudent(new Student(courseId, caoNumber, dob, pw));
//        s = studentManager.getStudent(87654321);
//        System.out.println(s);



}